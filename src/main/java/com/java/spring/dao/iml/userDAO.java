package com.java.spring.dao.iml;

import com.java.spring.dao.IUserDAO;
import com.java.spring.mapper.userMapper;
import com.java.spring.model.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class userDAO implements IUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<userModel> list() {
        String sql = "select * from user";
//        way 1
        List<userModel> customers = new ArrayList<userModel>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            userModel customer = new userModel();
            customer.setId((int)(row.get("id")));
            customer.setName((String)row.get("name"));
            customer.setEmail((String)row.get("email"));
            customer.setPhone((String)row.get("phone"));
            System.out.println(String.format("id = %d, name = %s, email = %s, phone = %s", customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone()));
            customers.add(customer);
        }
        return customers;
//        way 2
//        return jdbcTemplate.query(sql, new userMapper());
    }

    @Override
    public userModel get(int t) {

        String sql = "select  * from user where id=?";
        try {
            userModel usrModle = (userModel) jdbcTemplate.queryForObject(sql, new Object[]{t}, new userMapper());
            return usrModle;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(userModel userModel) {
        String sql = "insert into user (id, name, email, phone) values (?,?,?,?)";
        try {
            System.out.println(String.format("Insert id = %d, name = %s, email = %s, phone = %s", userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()));
            jdbcTemplate.update(sql, new Object[]{userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()});
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void update(userModel userModel) {
        String sql = "update user set name=?, email=?, phone=? where id=?";
        try {
            System.out.println(String.format("Update id = %d, name = %s, email = %s, phone = %s", userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()));
            jdbcTemplate.update(sql, new Object[]{userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()});
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void delete(userModel userModel) {
        String sql = "delete from user where id=?";
        try {
            System.out.println(String.format("Delete id = %d, name = %s, email = %s, phone = %s", userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()));
            jdbcTemplate.update(sql, new Object[]{userModel.getId()});
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
