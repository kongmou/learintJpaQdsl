package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.QUserBean;
import com.example.japLeaeing.bean.UserBean;
import com.example.japLeaeing.jpa.UserJPA;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

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

    /**
     * 使用Jpa更新会员信息
     * @param userBean
     */
    public String updateWithJpa(UserBean userBean)
    {
        //保存会员信息相当于Hibernate内的SaveOrUpdate
        userJPA.save(userBean);
        return "SUCCESS";
    }

}
