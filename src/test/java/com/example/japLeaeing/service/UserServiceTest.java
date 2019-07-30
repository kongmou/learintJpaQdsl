package com.example.japLeaeing.service;

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

        //UserBean userBean = new UserBean(2L, "updateTest2", 22, "Upfat2e", "ped2");
        UserBean userBean = new UserBean(5L, "updateTest5", 55, "Upfate5", "ped5");
        userService.updateWithJpa(userBean);
        System.out.println(userService.queryAll());
    }

    @Test
    public void testUpdateWithQdsl(){
        UserBean userBean = new UserBean(5L, "updateTest5", 55, "Upfate5", "ped5");
        userService.updateWithQdsl(userBean);
        //System.out.println(userService.queryAll());
    }

    @Test
    public void testDeleteWithQdsl(){
        UserBean userBean = new UserBean(5L, "updateTest5", 55, "Upfate5", "ped5");
       userService.deleteWithQdsl(userBean);
        //System.out.println(userService.queryAll());
    }




}