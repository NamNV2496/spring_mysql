package com.java.spring.controller;

import com.java.spring.model.userModel;
import com.java.spring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DBController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private userModel usr;

    @GetMapping("/test")
    public List<userModel> Test_findAll() {
        return iUserService.findAll();
    }

    @GetMapping("/test_insert")
    public int test_insert() {
        usr.setId(6);
        usr.setName("test_insertaa");
        usr.setPhone("09987894");
        usr.setEmail("test_inset@email.com");
        return iUserService.insert(usr);
    }
    @GetMapping("/test_update")
    public void test_update() {
        usr.setId(1);
        usr.setName("test_update");
        usr.setPhone("09987894");
        usr.setEmail("test_update@email.com");
        iUserService.update(usr);
    }
}
