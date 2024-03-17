package com.example.backendTravel.api.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Service
public class ImageService {

    public byte[] getImage(String imagePath) throws IOException {
        File file = new File(imagePath);
        return Files.readAllBytes(file.toPath());
    }

    public void saveImage(byte[] imageData, String imagePath) throws IOException {
        Files.write(new File(imagePath).toPath(), imageData);
    }
}
