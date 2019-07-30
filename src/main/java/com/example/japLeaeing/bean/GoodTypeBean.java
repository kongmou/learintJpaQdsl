package com.example.japLeaeing.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 〈类别〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/7/30
 * @since 1.0.0
 */
@Entity
@Table(name = "good_types")
@Data
public class GoodTypeBean implements Serializable {
    private static final long serialVersionUID = 1577784740076131580L;
    //主键
    @Id
    @GeneratedValue
    @Column(name = "tgt_id")
    private Long id;
    //类型名称
    @Column(name = "tgt_name")
    private String name;
    //是否显示
    @Column(name = "tgt_is_show")
    private int isShow;
    //排序
    @Column(name = "tgt_order")
    private int order;
}