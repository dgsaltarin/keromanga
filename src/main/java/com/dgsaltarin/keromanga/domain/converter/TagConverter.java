package com.dgsaltarin.keromanga.domain.converter;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.data.TagRequest;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {

    public Tag convertTagRequestToTag(TagRequest tagRequest) {
        Tag tag = new Tag();
        tag.setName(tagRequest.getName());
        return tag;
    }
}
