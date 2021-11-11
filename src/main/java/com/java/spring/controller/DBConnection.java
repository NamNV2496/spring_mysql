package com.java.spring.controller;

import com.java.spring.dao.iml.userDAO;
import com.java.spring.model.userModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DBConnection {
    @Autowired
    private userDAO userDAO;

    @Autowired
    private userModel updateModel;

//    @Override
//    public void run(String... args) throws Exception {
////        test_get_function();
//        System.out.println("run auto???");
//    }

    @GetMapping("/test")
    public String test_get_function() {
        userDAO.list();
        userModel ret = userDAO.get(1);
        System.out.println ("get(1) done");
        updateModel.setId(5);
        updateModel.setName("NAM");
        updateModel.setEmail("nam@gmail.com");
        updateModel.setPhone("1234567890");
        userDAO.save(updateModel);
//        userDAO.update(updateModel);
        userDAO.list();
        userDAO.delete(updateModel);
        userDAO.list();

//        System.out.println(String.format("id = %d, name = %s, email = %s, phone = %s", ret.getId(), ret.getName(), ret.getEmail(), ret.getPhone()));
        return String.format("id = %d, name = %s, email = %s, phone = %s", ret.getId(), ret.getName(), ret.getEmail(), ret.getPhone());
    }
}
