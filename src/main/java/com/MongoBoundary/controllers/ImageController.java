package com.MongoBoundary.controllers;

import com.MongoBoundary.models.Image;
import com.MongoBoundary.services.ImageService;
import com.MongoBoundary.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@CrossOrigin
public class ImageController {

    final ImageService imageService;

    @PostMapping("/addImage/{productId}")
    public void addImage(@RequestParam MultipartFile file, @PathVariable String productId) throws IOException {
        imageService.addImage(file.getName(), file, productId);
    }

    @GetMapping("/image/{imageId}")
    public Image getImage(@PathVariable String imageId){
        return imageService.getImage(imageId);
    }

    @GetMapping("/image/base64/{imageId}")
    public String getBase64FromImageId(@PathVariable String imageId){
        Image image = imageService.getImage(imageId);

        return Util.getBase64FromArrayBytes(image.getImageBytes());
    }
}
