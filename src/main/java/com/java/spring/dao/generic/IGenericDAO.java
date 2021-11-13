package com.java.spring.dao.generic;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface IGenericDAO<T> {
    <T> List<T> query(String sql, RowMapper<T> rowMapper, List<Object> list);
    void update (String sql, List<Object> list);
    int insert (String sql, List<Object> list);
    int count(String sql, Object... parameters);

}
