package com.example.japLeaeing.service;

import com.example.japLeaeing.bean.GoodInfoBean;
import com.example.japLeaeing.dto.GoodDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodServiceTest {

    @Resource
    private GoodService goodService;

    @Test
    public void TestSelectByType() {

        System.out.println(goodService.selectByType());
    }

    @Test
    public void TestSelectWithQueryDSL() {

        System.out.println(goodService.selectWithQueryDSL());
    }

    @Test
    public void TestSelectWithStream() {

        System.out.println(goodService.selectWithStream());
    }

    @Test
    public void TestChildLikeSelect() {

        goodService.childLikeSelect();
    }

    @Test
    public void TestFindGoodsCondition() {

        GoodInfoBean goodInfoBean = new GoodInfoBean();
        goodInfoBean.setId(1L);
        goodInfoBean.setTitle("白菜");

        List<GoodDTO> goodsCondition = goodService.findGoodsCondition(goodInfoBean);
        System.out.println(goodInfoBean);
    }


}