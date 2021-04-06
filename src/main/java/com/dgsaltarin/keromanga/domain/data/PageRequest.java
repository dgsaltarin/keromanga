package com.dgsaltarin.keromanga.domain.data;

import org.springframework.web.multipart.MultipartFile;

public class PageRequest {

    public MultipartFile page;

    public MultipartFile getPage() {
        return page;
    }

    public void setPage(MultipartFile page) {
        this.page = page;
    }
}
