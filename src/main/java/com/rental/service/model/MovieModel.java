package com.rental.service.model;

import io.swagger.annotations.ApiModelProperty;

public class MovieModel {
    @ApiModelProperty(notes = "this is movie id", required = true, value = "movie id", example = "1")
    private Long id;
    @ApiModelProperty(notes = "this is movie name", required = true, value = "movie name", example = "Movie Title")
    private String name;
    @ApiModelProperty(notes = "this is movie category", required = true, value = "movie category", example = "HORROR")
    private EnCategory category;

    private boolean isAvailable;

    public MovieModel(Long id, String name, EnCategory category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public MovieModel() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EnCategory getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(EnCategory category) {
        this.category = category;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
