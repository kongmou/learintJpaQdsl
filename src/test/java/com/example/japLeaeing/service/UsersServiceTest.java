package com.example.japLeaeing.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

    @Resource
    private UsersService usersService;

    @Test
    public void predefinedFunction() {
        usersService.predefinedFunction();
    }
}