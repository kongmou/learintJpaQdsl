package com.example.japLeaeing.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class UserBean implements Serializable {

    private static final long serialVersionUID = -2407841143289326866L;

    @Id
    @Column(name = "t_id")
    private Long id;
    @Column(name = "t_name")
    private String name;
    @Column(name = "t_age")
    private int age;
    @Column(name = "t_address")
    private String address;
    @Column(name = "t_pwd")
    private String pwd;
}
