package com.java.spring.service.impl;

import com.java.spring.dao.IUserDAO;
import com.java.spring.model.userModel;
import com.java.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService implements IUserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<userModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public int insert(userModel usr) {
        return userDAO.insert(usr);
    }

    @Override
    public void update(userModel usr) {

    }
}
