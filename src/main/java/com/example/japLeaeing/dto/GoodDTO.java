package com.example.japLeaeing.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 〈自定义实体〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/7/30
 * @since 1.0.0
 */
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class GoodDTO implements Serializable {

    private static final long serialVersionUID = 7149750034470495179L;

    //主键
    private Long id;

    //标题
    private String title;

    //单位
    private String unit;

    //价格
    private double price;

    //类型名称
    private String typeName;

    //类型编号
    private Long typeId;
}