package com.java.spring.dao.impl;

import com.java.spring.dao.IUserDAO;
import com.java.spring.mapper.userMapper;
import com.java.spring.model.userModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class userDAO extends abstractDAO<userModel> implements IUserDAO {

    @Override
    public List<userModel> findAll() {
        String sql = "SELECT * FROM user";
        return query(sql, new userMapper());
    }

    public int insert(userModel usr) {
        String sql = "insert into user (id, name, email, phone) values (?, ?, ?, ?)";
        return insert(sql, new Object[]{usr.getId()}, new Object[]{usr.getName()}, new Object[]{usr.getEmail()}, new Object[]{usr.getPhone()});
    }

    @Override
    public void update(userModel usr) {
        String sql = "update user set name=?, email=?, phone=? where id=?";
        update(sql, new Object[]{usr.getName()}, new Object[]{usr.getEmail()}, new Object[]{usr.getPhone(), new Object[]{usr.getId()}});
    }
}
