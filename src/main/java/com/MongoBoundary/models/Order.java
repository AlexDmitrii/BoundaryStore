package com.MongoBoundary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String orderId;

    private String statusInRaketa;
    //TODO: make User
    private String userID;
    private String nameProduct;
    private String linkOnProduct;
    private String description;
    private Integer quantity;
    private String raketaId;
    private String prId;
    private String dateCreate;
    private Double amount;
    private Double priceDelivery;
    private Double commission;
    private Double amountItems;
    private String status;

}
