package com.java.spring.dao.impl;

import com.java.spring.dao.IUserDAO;
import com.java.spring.mapper.userMapper;
import com.java.spring.model.userModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class userDAO extends abstractDAO<userModel> implements IUserDAO {

    @Override
    public List<userModel> findAll() {
        String sql = "SELECT * FROM user";
        List<Object> list = new ArrayList<>();
        return query(sql, new userMapper(), list);
    }

    public int insert(userModel usr) {
        String sql = "insert into user (id, name, email, phone) values (?, ?, ?, ?)";
        List<Object> list = new ArrayList<>();
        list.add(usr.getId());
        list.add(usr.getName());
        list.add(usr.getEmail());
        list.add(usr.getPhone());
        return insert(sql, list);
    }

    @Override
    public void update(userModel usr) {
        String sql = "update user set name=?, email=?, phone=? where id=?";
        List<Object> list = new ArrayList<>();
        list.add(usr.getName());
        list.add(usr.getEmail());
        list.add(usr.getPhone());
        list.add(usr.getId());
        update(sql, list);
    }
}
