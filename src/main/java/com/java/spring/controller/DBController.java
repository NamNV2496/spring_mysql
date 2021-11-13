package com.java.spring.controller;

import com.java.spring.model.UserModel;
import com.java.spring.repository.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DBController {

    @Autowired
    private IUserRepository userRepository;

//    @Autowired
//    private UserModel usr;

    @GetMapping("/test")
    public List<UserModel> Test_findAll() {
        return userRepository.findAll();
    }

    @PostMapping("/test_insert")
    public void test_insert(@RequestBody UserModel usr) {
        userRepository.save(new UserModel(usr.getId(), usr.getName(), usr.getEmail(), usr.getPhone()));
    }

    @PutMapping(value = "/test_update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void test_update(@RequestBody UserModel usr) {
        Optional<UserModel> userData = userRepository.findById(usr.getId());

        UserModel userModel = userData.get();
        userModel.setEmail(usr.getName());
        userModel.setName(usr.getName());
        userModel.setPhone(usr.getPhone());
        userRepository.save(new UserModel(userModel.getId(), userModel.getName(), userModel.getEmail(), userModel.getPhone()));
    }

    @DeleteMapping("/test_delete")
    public void test_delete(@RequestBody UserModel usr) {
        userRepository.deleteById(usr.getId());
    }
}
