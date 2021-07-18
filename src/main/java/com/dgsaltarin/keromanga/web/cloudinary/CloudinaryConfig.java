package com.dgsaltarin.keromanga.web.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryConfig {

  public Cloudinary cloudinary =
      new Cloudinary(
          ObjectUtils.asMap(
              "cloud_name", System.getenv().get("CLOUD_NAME"),
              "api_key", System.getenv().get("CLOUDINARY_KEY"),
              "api_secret", System.getenv().get("CLOUDINARY_SECRET"),
              "secure", true));

  public Cloudinary getCloudinary() {
    return cloudinary;
  }

  public void setCloudinary(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }
}
