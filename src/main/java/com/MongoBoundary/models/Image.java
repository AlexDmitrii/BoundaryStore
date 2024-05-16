package com.MongoBoundary.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Image {

    @Id
    private String id;
    private String name;
    private Long size;
    private String contentType;
//    private Byte[] bytes;

    private String productId;

    private byte[] imageBytes;

    private LocalDateTime dateOfCreated;

    public Image(String name){
        this.name = name;
    }

    public Image(){
        dateOfCreated = LocalDateTime.now();
    }

}
