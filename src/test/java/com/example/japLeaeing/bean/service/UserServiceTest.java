package com.example.japLeaeing.bean.service;

import com.example.japLeaeing.bean.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public  void testQueryAll(){
        List<UserBean> userBeans = userService.queryAll();
        System.out.println(userBeans);
    }

    @Test
    public  void tesUpdateWithJpa(){

        UserBean userBean = new UserBean(1L, "updateTest", 22, "Upfate", "ped");
        String resultStr = userService.updateWithJpa(userBean);
        System.out.println(userService.queryAll());
    }

}