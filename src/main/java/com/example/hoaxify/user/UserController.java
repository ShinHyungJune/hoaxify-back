package com.example.hoaxify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //이 클래스가 http 리퀘스트를 다룰거라고 스프링에 알려줌
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    void store()
    {
    }
}