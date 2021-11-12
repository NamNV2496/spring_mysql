package com.java.spring.mapper;

import com.java.spring.model.userModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userMapper implements RowMapper<userModel> {

    @Override
    public userModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            userModel usrModel = new userModel();
            usrModel.setId(rs.getInt("id"));
            usrModel.setName(rs.getString("name"));
            usrModel.setEmail(rs.getString("email"));
            usrModel.setPhone(rs.getString("phone"));
            return usrModel;
        }catch (SQLException e) {
            System.out.println("Mapper fail");
            return null;
        }
    }
}
