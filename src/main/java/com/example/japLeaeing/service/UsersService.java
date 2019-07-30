package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.QUserBean;
import com.example.japLeaeing.bean.QUsersBean;
import com.example.japLeaeing.bean.UserBean;
import com.example.japLeaeing.bean.UsersBean;
import com.example.japLeaeing.jpa.UserJPA;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UsersService {

    @Resource
    private UserJPA userJPA;

    //JPA查询工厂
    @Resource
    private JPAQueryFactory queryFactory;

    /**
     * 聚合函数使用
     */
    public void predefinedFunction(){
        QUsersBean qUsersBean = QUsersBean.usersBean;
        Long countResult = queryFactory.select(qUsersBean.count())
                .from(qUsersBean)
                .fetchOne();
        System.out.println("count: " + countResult);

        Double sumResult = queryFactory.select(qUsersBean.socre.sum())
                .from(qUsersBean)
                .fetchOne();
        System.out.println("sum : " + sumResult);

        Double avgResult = queryFactory.select(qUsersBean.socre.avg())
                .from(qUsersBean)
                .fetchOne();
        System.out.println("avg" + avgResult);

        Double maxResult = queryFactory.select(qUsersBean.socre.max())
                .from(qUsersBean)
                .fetchOne();
        System.out.println("max" + maxResult);
        List<UsersBean> beanList = queryFactory.select(qUsersBean)
                .from(qUsersBean)
                .groupBy(qUsersBean.age)
                .having(qUsersBean.age.lt(22))
                .fetch();
        System.out.println("groupBy" + beanList);

    }

}
