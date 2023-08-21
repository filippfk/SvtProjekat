package com.example.projekatSvt.dto;

import com.example.projekatSvt.entity.Image;

import javax.validation.constraints.NotNull;

public class ImageDto {

    @NotNull
    private String path;

    public ImageDto (Image image) {

        this.path = image.getPath();
    }
}