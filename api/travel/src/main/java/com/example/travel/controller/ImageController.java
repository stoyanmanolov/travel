package com.example.travel.controller;

import com.example.travel.dto.UploadImageRequestDto;
import com.example.travel.model.Image;
import com.example.travel.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@ModelAttribute UploadImageRequestDto requestDto) throws IOException {
        Image image = imageService.upload(requestDto.getFile(), requestDto.getType());

        return ResponseEntity.ok(image);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        byte[] bytes = imageService.download(id);

        return ResponseEntity.ok(bytes);
    }

    @GetMapping
    public ResponseEntity<List<Image>> getImages() {
        List<Image> images = imageService.getImages();

        return ResponseEntity.ok(images);
    }
}
