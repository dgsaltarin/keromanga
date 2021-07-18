package com.dgsaltarin.keromanga.web.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CloudinaryConfig {

  @Value("${CLOUD_NAME}")
  private String cloudName;

  @Value("${CLOUDINARY_SECRET}")
  private String cloudSecret;

  @Value("${CLOUDINARY_KEY}")
  private String cloudKey;

  public Cloudinary cloudinary =
      new Cloudinary(
          ObjectUtils.asMap(
              "cloud_name", cloudName,
              "api_key", cloudKey,
              "api_secret", cloudSecret,
              "secure", true));

  public Cloudinary getCloudinary() {
    return cloudinary;
  }

  public void setCloudinary(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }
}
