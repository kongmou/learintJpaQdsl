package com.example.japLeaeing.axis2;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

/**
 * 〈〉
 *
 * @author chinasoft.jie.zhang
 * @create 2019/8/8
 * @since 1.0.0
 */
public class MobileClientDoc {

    /**
     *
     * @Title: Weather
     * @Description: TODO(document远程调用，成功，没有参数能够成功)
     * @return void    返回类型
     * @throws AxisFault
     */
    public void Weather() throws AxisFault {
        ServiceClient serviceClient = new ServiceClient();
        //创建服务地址WebService的URL,注意不是WSDL的URL
        String url = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx";
        EndpointReference targetEPR = new EndpointReference(url);
        Options options = serviceClient.getOptions();
        options.setTo(targetEPR);
        //确定调用方法（wsdl 命名空间地址 (wsdl文档中的targetNamespace) 和 方法名称 的组合）
        options.setAction("http://WebXml.com.cn/getRegionProvince");

        OMFactory fac = OMAbstractFactory.getOMFactory();
        /*
         * 指定命名空间，参数：
         * uri--即为wsdl文档的targetNamespace，命名空间
         * perfix--可不填
         */
        OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn/", "");
        // 指定方法
        OMElement method = fac.createOMElement("getSupportCityDataset", omNs);
        method.build();

        //远程调用web服务
        OMElement result = serviceClient.sendReceive(method);
        System.out.println(result);
    }

}