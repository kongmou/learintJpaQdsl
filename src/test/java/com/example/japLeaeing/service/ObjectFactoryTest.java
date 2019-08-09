package com.example.japLeaeing.service;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.GetSupportCity;
import cn.com.webxml.GetSupportCityResponse;
import cn.com.webxml.GetWeatherbyCityName;
import cn.com.webxml.ObjectFactory;
import cn.com.webxml.WeatherWebService;
import cn.com.webxml.WeatherWebServiceSoap;
import com.example.japLeaeing.axis2.MobileClientDoc;
import org.apache.axis2.AxisFault;
import org.junit.Test;

import java.util.List;

/**
 * 〈〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/8/8
 * @since 1.0.0
 */
public class ObjectFactoryTest {

    @Test
    public void test1(){
        WeatherWebService weatherWebService = new WeatherWebService();
        WeatherWebServiceSoap webSop = weatherWebService.getWeatherWebServiceSoap();
        ArrayOfString weatherbyCityName = webSop.getWeatherbyCityName("成都");
        List<String> weaList = weatherbyCityName.getString();
        System.out.println(weaList);


    }

    @Test
    public void testMobileClientDoc() throws  Exception{
        MobileClientDoc mobileClientDoc = new MobileClientDoc();
        mobileClientDoc.Weather();


    }

}