package com.java.spring.dao;

import com.java.spring.dao.generic.IGenericDAO;
import com.java.spring.model.userModel;

import java.util.List;

public interface IUserDAO extends IGenericDAO<userModel> {
    List<userModel> findAll();
    int insert(userModel usr);
    void update(userModel usr);
}
