package com.java.spring.dao.impl;

import com.java.spring.dao.IBookDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class bookDAO extends JdbcDaoSupport implements IBookDAO {
    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, List<Object> list) {
        return null;
    }

    @Override
    public void update(String sql, List<Object> list) {

    }

    @Override
    public int insert(String sql, List<Object> list) {
        return 0;
    }


    @Override
    public int count(String sql, Object... parameters) {
        return 0;
    }
}
