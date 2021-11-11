package com.java.spring.dao.iml;

import com.java.spring.dao.IBookDAO;
import com.java.spring.model.bookModel;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class bookDAO extends JdbcDaoSupport implements IBookDAO {
    @Override
    public List<bookModel> list() {
        return null;
    }

    @Override
    public bookModel get(int t) {
        return null;
    }

    @Override
    public void save(bookModel bookModel) {

    }

    @Override
    public void update(bookModel bookModel) {

    }

    @Override
    public void delete(bookModel bookModel) {

    }
}
