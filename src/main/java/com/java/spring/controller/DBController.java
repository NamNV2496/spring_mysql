package com.java.spring.controller;

//import com.java.spring.entity.UserModel;
import com.java.spring.entity.UserModel;
import com.java.spring.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DBController {

    @Autowired
    UserService userService;
    @GetMapping("/test")
    public List<UserModel> Test_findAll() {
        return userService.listAll(1);
    }

    @PostMapping("/test_insert")
    public void test_insert(@RequestBody UserModel usr) {
        userService.insert(usr, 3);
    }

    @PutMapping(value = "/test_update")
    public void test_update(@RequestBody UserModel usr) {
        userService.update(usr, 3);
    }

    @DeleteMapping("/test_delete")
    public void test_delete(@RequestBody UserModel usr) {
        userService.delete(usr, 3);
    }
}
