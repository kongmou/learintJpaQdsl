package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.QUserBean;
import com.example.japLeaeing.bean.UserBean;
import com.example.japLeaeing.jpa.UserJPA;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserJPA userJPA;

    //JPA查询工厂
    @Resource
    private JPAQueryFactory queryFactory;


    /**
     * 查询全部数据并根据id倒序
     *
     * @return
     */
    public List<UserBean> queryAll() {
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
     *
     * @param userBean
     */
    public void updateWithJpa(UserBean userBean) {
        //保存会员信息相当于Hibernate内的SaveOrUpdate
        UserBean saveResult = userJPA.save(userBean);
        System.out.println(saveResult);
    }

    @Transactional
    public void updateWithQdsl(UserBean userBean) {
        QUserBean userBean1 = QUserBean.userBean;

        queryFactory.update(userBean1)//更新对象
                //更新字段列表
                //.set(userBean1.id, userBean.getId())
                .set(userBean1.address, userBean.getAddress())
                .set(userBean1.age, userBean.getAge())
                .set(userBean1.pwd, userBean.getPwd())
                //更新条件
                .where(userBean1.id.eq(userBean.getId()))
                //执行更新
                .execute();
    }


    /**
     * Jpa删除
     * @param userId
     */
    public void deleteWithJpa(Long userId){
        userJPA.deleteById(userId);
    }

    @Transactional
    public void deleteWithQdsl(UserBean userBean){
        QUserBean qUserBean = QUserBean.userBean;
        queryFactory.delete(qUserBean)
                .where(qUserBean.id.eq(userBean.getId()).and(qUserBean.age.gt(30)))
                .execute();
    }

}
