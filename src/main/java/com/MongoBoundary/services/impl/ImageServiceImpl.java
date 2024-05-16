package com.MongoBoundary.services.impl;

import com.MongoBoundary.models.Image;
import com.MongoBoundary.repositories.ImageRepo;
import com.MongoBoundary.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    final ImageRepo imageRepo;

    @Override
    public void addImage(String name, MultipartFile file, String productId) throws IOException {
        Image image = Image.builder()
                .name(name)
                .imageBytes(file.getBytes())
                .contentType(file.getContentType())
                .size(file.getSize())
                .productId(productId)
                .build();

        imageRepo.insert(image);

    }

    @Override
    public Image getImage(String id) {
        return imageRepo.findById(id).orElse(null);
    }

    @Override
    public void saveImage(MultipartFile file) {


    }
}
