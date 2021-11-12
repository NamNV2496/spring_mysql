package com.java.spring.service;

import com.java.spring.model.userModel;

import java.util.List;

public interface IUserService {
    List<userModel> findAll();
    int insert(userModel usr);
    void update(userModel usr);
}
