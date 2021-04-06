package com.dgsaltarin.keromanga.web.cloudinary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CloudinaryIntegration {

    private CloudinaryService cloudinaryService;

    @Autowired
    public CloudinaryIntegration(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    public String uploadFile(MultipartFile file) {
        String url = cloudinaryService.uploadFile(file);
        return url;
    }
}
