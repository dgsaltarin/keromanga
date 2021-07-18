package com.dgsaltarin.keromanga.web.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryConfig {

  private Dotenv dotenv = Dotenv.load();

  public Cloudinary cloudinary =
      new Cloudinary(
          ObjectUtils.asMap(
              "cloud_name", dotenv.get("CLOUD_NAME"),
              "api_key", dotenv.get("CLOUDINARY_KEY"),
              "api_secret", dotenv.get("CLOUDINARY_SECRET"),
              "secure", true));

  public Cloudinary getCloudinary() {
    return cloudinary;
  }

  public void setCloudinary(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }
}
