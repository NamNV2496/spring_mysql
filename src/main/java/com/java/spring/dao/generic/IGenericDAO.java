package com.java.spring.dao.generic;

import java.util.List;

public interface IGenericDAO<T> {
    public List<T> list();
    public T get(int t);

    public void save(T t);

    public void update(T t);

    public void delete(T t);

}
