package com.java.spring.dao.impl;

import com.java.spring.dao.IBookDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class bookDAO extends JdbcDaoSupport implements IBookDAO {
    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {

    }

    @Override
    public int insert(String sql, Object... parameters) {
        return 0;
    }

    @Override
    public int count(String sql, Object... parameters) {
        return 0;
    }
}
