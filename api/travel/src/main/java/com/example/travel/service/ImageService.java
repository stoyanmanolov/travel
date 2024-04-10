package com.example.travel.service;

import com.example.travel.model.Image;
import com.example.travel.model.ImageType;
import com.example.travel.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public Image upload(MultipartFile file, ImageType type) throws IOException {
        Image image = new Image(file.getOriginalFilename(), type, file.getContentType(), file.getBytes());
        return imageRepository.save(image);
    }

    public byte[] download(Long id) {
        return imageRepository
                .findById(id)
                .orElseThrow()
                .getImageData();
    }
}
