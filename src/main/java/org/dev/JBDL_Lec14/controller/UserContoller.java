package org.dev.JBDL_Lec14.controller;

import jakarta.validation.Valid;
import org.dev.JBDL_Lec14.dto.UserRequest;
import org.dev.JBDL_Lec14.model.Book;
import org.dev.JBDL_Lec14.model.User;
import org.dev.JBDL_Lec14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserContoller {
    @Autowired
     private UserService useService;
    @Autowired
    private UserService userService;

    @PostMapping("/addstudent")
    public User addstudent(@RequestBody @Valid UserRequest userRequest) {
       return   useService.addStudent(userRequest);
    }



    @PostMapping("/addAdmin")
    public User addAdmin(@RequestBody @Valid  UserRequest userRequest) {
        return null;
    }
    @GetMapping("/filter")
    public List<User> filter(@RequestParam("filterBy")String filterBy,
                             @RequestParam("operator") String opertor,
                             @RequestParam("value") String value){
      return userService.filter(filterBy,opertor,value);
    }



}

