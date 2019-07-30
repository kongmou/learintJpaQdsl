package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.GoodInfoBean;
import com.example.japLeaeing.bean.QGoodInfoBean;
import com.example.japLeaeing.bean.QGoodTypeBean;
import com.example.japLeaeing.dto.GoodDTO;
import com.querydsl.codegen.ProjectionSerializer;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈商品服务类〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/7/30
 * @since 1.0.0
 */
@Service
public class GoodService {

    @Resource
    private JPAQueryFactory queryFactory;

    /**
     * 多表查询
     *
     * @return
     */
    public List<GoodInfoBean> selectByType() {
        QGoodInfoBean goodInfoBean = QGoodInfoBean.goodInfoBean;
        QGoodTypeBean goodTypeBean = QGoodTypeBean.goodTypeBean;
        return queryFactory.select(goodInfoBean)
                .from(goodInfoBean, goodTypeBean)
                .where(goodInfoBean.typeId.eq(goodTypeBean.id))
                .fetch();
    }

    /**
     * 自定义返回结果集
     * QueryDSL & Projections
     *
     * @return
     */
    public GoodDTO selectWithQueryDSL() {
        //商品基本信息
        QGoodInfoBean _Q_good = QGoodInfoBean.goodInfoBean;
        //商品类型
        QGoodTypeBean _Q_good_type = QGoodTypeBean.goodTypeBean;
        return queryFactory.select(Projections.bean(
                GoodDTO.class,//返回自定义实体的类型
                _Q_good.id,
                _Q_good.price,
                _Q_good.title,
                _Q_good.unit,
                //使用别名对应dto内的typeName
                _Q_good_type.name.as("typeName"),
                //使用别名对应dto内的typeId
                _Q_good_type.id.as("typeId")))
                .from(_Q_good, _Q_good_type)
                .where(_Q_good.typeId.eq(_Q_good_type.id))
                .fetchOne();
    }

    /**
     * 自定义返回结果集
     * QueryDSL & Collection
     *
     * @return
     */
    public List<GoodDTO> selectWithStream() {
        //商品基本信息
        QGoodInfoBean _Q_good = QGoodInfoBean.goodInfoBean;
        //商品类型
        QGoodTypeBean _Q_good_type = QGoodTypeBean.goodTypeBean;
        return queryFactory.select(
                _Q_good.id,
                _Q_good.price,
                _Q_good.title,
                _Q_good.unit,
                _Q_good_type.name,
                _Q_good_type.id
        )
                .from(_Q_good, _Q_good_type)//构建两表笛卡尔集
                .where(_Q_good.typeId.eq(_Q_good_type.id))//关联两表
                .orderBy(_Q_good.order.desc())//倒序
                .fetch()
                .stream()
                .map(tuple -> {
                    //创建商品dto
                    GoodDTO dto = new GoodDTO();
                    //设置商品编号
                    dto.setId(tuple.get(_Q_good.id));
                    //设置商品价格
                    dto.setPrice(tuple.get(_Q_good.price));
                    //设置商品标题
                    dto.setTitle(tuple.get(_Q_good.title));
                    //设置单位
                    dto.setUnit(tuple.get(_Q_good.unit));
                    //设置类型编号
                    dto.setTypeId(tuple.get(_Q_good_type.id));
                    //设置类型名称
                    dto.setTypeName(tuple.get(_Q_good_type.name));
                    //返回本次构建的dto
                    return dto;
                })
                .collect(Collectors.toList());

    }

    /**
     * 模糊子查询
     */
    public  void childLikeSelect(){
        QGoodInfoBean qGoodInfoBean = QGoodInfoBean.goodInfoBean;
        QGoodTypeBean qGoodTypeBean = QGoodTypeBean.goodTypeBean;

        List<GoodInfoBean> goodInfoBeans = queryFactory.select(qGoodInfoBean)
                .from(qGoodInfoBean)
                .where(qGoodInfoBean.typeId.in(
                        JPAExpressions.select(qGoodTypeBean.id)
                                .from(qGoodTypeBean)
                                .where(qGoodTypeBean.name.like("蔬菜%"))

                ))
                .offset(1)
                .limit(1)
                .fetch();
        System.out.println(goodInfoBeans);
    }

}