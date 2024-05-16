package com.MongoBoundary.services;

import com.MongoBoundary.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void addImage(String name, MultipartFile file, String productId) throws IOException;
    Image getImage(String id);

    void saveImage(MultipartFile file);
}
