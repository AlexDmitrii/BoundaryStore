package com.MongoBoundary.models;

import com.MongoBoundary.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Constant.PRODUCT_DB_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{

    @Id
    private String productId;
    private String name;
    private String description;
    private Integer quantity;
    private Integer price;
    private Boolean active;
    public byte[] imageBytes;

}
