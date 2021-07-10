package com.dgsaltarin.keromanga.web.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryConfig {

    public Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "my_cloud_name",
            "api_key", "my_api_key",
            "api_secret", "my_api_secret",
            "secure", true));

    public Cloudinary getCloudinary() {
        return cloudinary;
    }

    public void setCloudinary(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
}
