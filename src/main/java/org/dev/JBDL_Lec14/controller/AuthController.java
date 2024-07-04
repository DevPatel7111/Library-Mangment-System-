package org.dev.JBDL_Lec14.controller;

import org.dev.JBDL_Lec14.model.Author;
import org.dev.JBDL_Lec14.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAuthorData")
    public Author authordata(@RequestParam("authorEmail")String email){
     return    authorService.getauthordata(email);
    }
}
