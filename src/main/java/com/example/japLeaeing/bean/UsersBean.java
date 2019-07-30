package com.example.japLeaeing.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈用于聚合查询〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/7/30
 * @since 1.0.0
 */
@Entity
@Table(name = "users")
@Data
public class UsersBean
{
    @Id
    @GeneratedValue
    @Column(name = "u_id")
    private Long id;
    @Column(name = "u_username")
    private String name;
    @Column(name = "u_age")
    private int age;
    @Column(name = "u_score")
    private double socre;
}