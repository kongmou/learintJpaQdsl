package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.GoodInfoBean;
import com.example.japLeaeing.bean.QGoodInfoBean;
import com.example.japLeaeing.bean.QGoodTypeBean;
import com.example.japLeaeing.dto.GoodDTO;
import com.querydsl.codegen.ProjectionSerializer;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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
                .fetch().stream().map(tuple ->
                        GoodDTO.builder()
                                .id(tuple.get(_Q_good.id))
                                .price(tuple.get(_Q_good.price))
                                .title(tuple.get(_Q_good.title))
                                .unit(tuple.get(_Q_good.unit))
                                .typeName(tuple.get(_Q_good_type.name))
                                .typeId(tuple.get(_Q_good_type.id)).build()
                ).collect(Collectors.toList());

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

    public List<GoodDTO> findGoodsCondition(GoodInfoBean goodInfoBean){
        QGoodTypeBean qGoodTypeBean = QGoodTypeBean.goodTypeBean;
        QGoodInfoBean qGoodInfoBean = QGoodInfoBean.goodInfoBean;

        Predicate predicate = qGoodInfoBean.id.isNotNull().or(qGoodInfoBean.id.isNull());
        predicate = goodInfoBean.getTitle() == null ? predicate : ExpressionUtils.and(predicate, qGoodInfoBean.title.eq(goodInfoBean.getTitle()));

        return queryFactory.select(
                qGoodInfoBean.id,
                qGoodInfoBean.price,
                qGoodInfoBean.title,
                qGoodInfoBean.unit,
                qGoodTypeBean.name,
                qGoodTypeBean.id
        ).from(qGoodInfoBean, qGoodTypeBean)
         .where(qGoodInfoBean.typeId.eq(qGoodTypeBean.id).and(predicate))
         .fetch().stream().map(tuple ->
                GoodDTO.builder()
                .id(tuple.get(qGoodInfoBean.id))
                .price(tuple.get(qGoodInfoBean.price))
                .title(tuple.get(qGoodInfoBean.title))
                .unit(tuple.get(qGoodInfoBean.unit))
                .typeName(tuple.get(qGoodTypeBean.name))
                .typeId(tuple.get(qGoodTypeBean.id)).build()
        ).collect(Collectors.toList());

    }

}