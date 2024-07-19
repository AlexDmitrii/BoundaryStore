package com.MongoBoundary.orderservice.model;

//import com.MongoBoundary.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "order")
@Data
@AllArgsConstructor
public class Order {

    @Id
    private String orderId;

    private String statusInRaketa;
    //TODO: make User
    private String userID;
    private String nameProduct;
//    private List<Product> products;
    private String description;
    private String raketaId;
    private String prId;
    private String dateCreate;
    private Double amount;
    private Double priceDelivery;
    private Double commission;
    private Double amountItems;
    private String status;

}
