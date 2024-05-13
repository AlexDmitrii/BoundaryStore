package com.MongoBoundary.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SoapUtil {

    public static String getDeliveryHistory(String raketaId, String prId, String urlPR, String urlRaketa, String loginPR, String passwordPR) {
        List<Map<String, String>> statuses = new ArrayList<>();
        statuses.addAll(getHistoryFromRaketa(raketaId, urlRaketa));
        statuses.addAll(getHistoryFromPR(prId, urlPR, loginPR, passwordPR));

        JSONArray jsonArray = new JSONArray();
        for (Map<String, String> map: statuses) {
            JSONObject item = new JSONObject();
            item.put("title", map.get("status"));
            item.put("description", map.get("date"));
            jsonArray.put(item);
        }

        return jsonArray.toString();
    }

    public static List<Map<String, String>> getHistoryFromRaketa(String idRaketa, String urlRaketa) {

        List<Map<String, String>> datesAndStatuses = new ArrayList<>();

        try {

            Document doc = Jsoup.connect(urlRaketa + idRaketa)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements steps = doc.getElementsByClass("delivery-step");
            for (Element step : steps) {
                Map<String, String> dateAndStatus = new HashMap<>();
                dateAndStatus.put("status", step.getElementsByClass("status-text").text());
                dateAndStatus.put("date", step.getElementsByClass("date-text").text());
                datesAndStatuses.add(dateAndStatus);
            }
        } catch (IOException e) {
            System.out.println("Ошибка получения ответа от Ракеты");
            throw new RuntimeException(e);
        }

        return datesAndStatuses;
    }


    public static List<Map<String, String>> getHistoryFromPR(String idPR, String urlPR, String loginPR, String passwordPR) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");

        String responseBody = getResponseFromPR(idPR, urlPR, loginPR, passwordPR);

        List<Map<String, String>> dates = new ArrayList<>();


        if (responseBody != null) {
            InputSource inputSource = new InputSource(new StringReader(responseBody));

            org.w3c.dom.Document doc;
            try {
                doc = dbf.newDocumentBuilder().parse(inputSource);
            } catch (SAXException | ParserConfigurationException | IOException e) {
                System.out.println("Ошибка при создании документа");
                throw new RuntimeException(e);
            }
            Node envelope = doc.getFirstChild();
            Node body = envelope.getFirstChild();
            Node opHisResp = body.getFirstChild();
            Node operationHistoryData = opHisResp.getFirstChild();

            NodeList historyRecords = operationHistoryData.getChildNodes();

            for (int a = 0; a < historyRecords.getLength(); a++) {
                if (historyRecords.item(a).getNodeType() != Node.ELEMENT_NODE)
                    continue;

                NodeList inHistoryRecord = historyRecords.item(a).getChildNodes();

                Map<String, String> dateAndStatus = new HashMap<>();
                String name = "";
                String formattedDate;
                String index = "";

                for (int b = 0; b < inHistoryRecord.getLength(); b++) {
                    if (inHistoryRecord.item(b).getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    if (inHistoryRecord.item(b).getNodeName().equals("ns3:AddressParameters")) {
                        Node adressParams = inHistoryRecord.item(b);
                        NodeList inAdressParam = adressParams.getChildNodes();
                        for (int i = 0; i < inAdressParam.getLength(); i++) {
                            if (inAdressParam.item(i).getNodeType() != Node.ELEMENT_NODE)
                                continue;

                            if (inAdressParam.item(i).getNodeName().equals("ns3:OperationAddress")) {
                                index = inAdressParam.item(i).getFirstChild().getTextContent();
                                if (index != null && !index.isEmpty()) {
                                    dateAndStatus.put("status", index);
                                }
                            }
                        }
                    }
                    if (inHistoryRecord.item(b).getNodeName().equals("ns3:OperationParameters")) {
                        NodeList inOperationParameters = inHistoryRecord.item(b).getChildNodes();

                        for (int c = 0; c < inOperationParameters.getLength(); c++) {
                            if (inOperationParameters.item(c).getNodeType() != Node.ELEMENT_NODE)
                                continue;
                            if (inOperationParameters.item(c).getNodeName().equals("ns3:OperAttr")) {
                                name = inOperationParameters.item(c).getLastChild().getTextContent();
                                if (!name.isEmpty())
                                    dateAndStatus.put("status", name.replace("Единичный", "Принято") + ", " + index);
                            }
                        }

                        String operDate = inHistoryRecord.item(b).getLastChild().getTextContent();
                        Date date;
                        try {
                            date = inputFormat.parse(operDate.substring(0,18));
                        } catch (ParseException e) {
                            System.out.println("Ошибка парсинга даты");
                            throw new RuntimeException(e);
                        }
                        formattedDate = Util.capitalizeWords(outputFormat.format(date));

                        if (!formattedDate.isEmpty() && !name.isEmpty() && !name.equals("0")) {
                            dateAndStatus.put("date", formattedDate);
                            dates.add(dateAndStatus);
                        }
                    }
                }
            }
        }

        return dates;
    }

    private static String getResponseFromPR(String idPR, String urlPR, String loginPR, String passwordPR) {
        String soapRequest = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:oper=\"http://russianpost.org/operationhistory\" xmlns:data=\"http://russianpost.org/operationhistory/data\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "   <soap:Header/>\n" +
                "   <soap:Body>\n" +
                "      <oper:getOperationHistory>\n" +
                "         <!--Optional:-->\n" +
                "         <data:OperationHistoryRequest>\n" +
                "            <data:Barcode>" + idPR + "</data:Barcode>\n" +
                "            <data:MessageType>0</data:MessageType>\n" +
                "            <!--Optional:-->\n" +
                "            <data:Language>RUS</data:Language>\n" +
                "         </data:OperationHistoryRequest>\n" +
                "         <!--Optional:-->\n" +
                "         <data:AuthorizationHeader ns1:mustUnderstand=\"?\">\n" +
                "            <data:login>" + loginPR + "</data:login>\n" +
                "            <data:password>" + passwordPR + "</data:password>\n" +
                "         </data:AuthorizationHeader>\n" +
                "      </oper:getOperationHistory>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/soap+xml"));

        HttpEntity<String> request = new HttpEntity<>(soapRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(urlPR, HttpMethod.POST, request, String.class);

        return response.getBody();
    }
}
