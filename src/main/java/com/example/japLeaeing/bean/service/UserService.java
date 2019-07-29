package com.example.japLeaeing.bean.service;

import com.example.japLeaeing.bean.QUserBean;
import com.example.japLeaeing.bean.UserBean;
import com.example.japLeaeing.jpa.UserJPA;
import com.google.common.annotations.VisibleForTesting;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserJPA userJPA;

    @Resource
    private EntityManager entityManager;

    //JPA查询工厂
    @Resource
    private JPAQueryFactory queryFactory;



    /**
     * 查询全部数据并根据id倒序
     * @return
     */
    @RequestMapping(value = "/queryAll")
    public List<UserBean> queryAll()
    {
        //使用querydsl查询
        QUserBean _Q_user = QUserBean.userBean;
        //查询并返回结果集
        return queryFactory
                .selectFrom(_Q_user)//查询源
                .orderBy(_Q_user.id.desc())//根据id倒序
                .fetch();//执行查询并获取结果集
    }

}
