package com.java.spring.model;

import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

public class bookModel implements Serializable {
    private int id;
    private String name;
    private int library_id;
    public int getId(int id) {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLibId() {
        return library_id;
    }
    public void setLibId(int lib_id) {
        this.library_id = lib_id;
    }

}
