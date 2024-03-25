package com.example.backendTravel.api.controller;

import com.example.backendTravel.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageController(ImageService imageService, ResourceLoader resourceLoader) {
        this.imageService = imageService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        try {
            String imagePath = "src/main/resources/images/" + imageName;
            File file = new File(imagePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            FileInputStream inputStream = new FileInputStream(file);
            byte[] imageData = inputStream.readAllBytes();

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/citiesImages/{imageName}")
    public ResponseEntity<byte[]> getCitiesImage(@PathVariable String imageName) {
        try {
            String imagePath = "src/main/resources/images/citiesImages/" + imageName;
            File file = new File(imagePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            FileInputStream inputStream = new FileInputStream(file);
            byte[] imageData = inputStream.readAllBytes();

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/usersImages/{imageName}")
    public ResponseEntity<byte[]> getUsersImage(@PathVariable String imageName) {
        try {
            String imagePath = "src/main/resources/images/usersImages/" + imageName;
            File file = new File(imagePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            FileInputStream inputStream = new FileInputStream(file);
            byte[] imageData = inputStream.readAllBytes();

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/opinionsImages/{imageName}")
    public ResponseEntity<byte[]> getOpinionsImage(@PathVariable String imageName) {
        try {
            String imagePath = "src/main/resources/images/opinionsImages/" + imageName;
            File file = new File(imagePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            FileInputStream inputStream = new FileInputStream(file);
            byte[] imageData = inputStream.readAllBytes();

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping
    public ResponseEntity<String> saveImage(@RequestParam("image") MultipartFile image,
                                            @RequestParam("path") String path) {
        try {
            if (image != null && !image.isEmpty()) {
                byte[] imageData = image.getBytes();
                String imageName = image.getOriginalFilename();
                String imagePath = "src/main/resources/images/"+ path + imageName;

                File directory = new File("src/main/resources/images"+"/" +path);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                try (FileOutputStream fos = new FileOutputStream(imagePath)) {
                    fos.write(imageData);
                }

                return ResponseEntity.status(HttpStatus.CREATED).body("Image saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image data is missing");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save image");
        }
    }
}
