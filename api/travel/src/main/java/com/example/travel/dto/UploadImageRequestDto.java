package com.example.travel.dto;

import com.example.travel.model.ImageType;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageRequestDto {
    private final MultipartFile file;
    private final ImageType type;

    public UploadImageRequestDto(MultipartFile file, ImageType type) {
        this.file = file;
        this.type = type;
    }

    public MultipartFile getFile() {
        return file;
    }

    public ImageType getType() {
        return type;
    }
}
