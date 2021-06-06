package com.rental.service.model;

public class MovieModel {
    private Long id;
    private String name;
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
