package com.java.spring.service;

import com.java.spring.entity.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> listAll(int caseMap);
    void insert(UserModel usr, int caseMap);
    void update(UserModel usr, int caseMap);
    void delete(UserModel usr, int caseMap);
}
