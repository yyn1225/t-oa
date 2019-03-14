/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.sync.syncinterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
public class CrmInterface {
    private final String customerUrl;
    private final String userUrl;
    private final String deleteCustomerUrl;

    public CrmInterface(){
        customerUrl="https://crm.zoho.com/crm/private/xml/Accounts/getRecords";
        userUrl = "https://crm.zoho.com/crm/private/xml/Users/getUsers";
        deleteCustomerUrl="https://crm.zoho.com/crm/private/xml/Accounts/getDeletedRecordIds";
    }

    /**
     * 获取相关的客户信息列表
     * @param index
     * @return
     */
    public List<Map<String,String>> getMapString(int index, String token, String columns){
        List<Map<String,String>> mapList = Lists.newArrayList();
        try{
            int start = index * 200 + 1;
            int end = index * 200 + 200;
            StringBuilder params = new StringBuilder();
            params.append("?fromIndex=").append(String.valueOf(start));
            params.append("&toIndex=").append(String.valueOf(end));
            params.append("&authtoken=").append(token);
            params.append("&scope=").append("crmapi");
            params.append("&selectColumns=").append(URLEncoder.encode(columns));
            InputStream xmlInput = Request.Get(customerUrl + params.toString()).useExpectContinue().execute().returnContent().asStream();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xmlInput);
            List<Element> rows = document.getRootElement().element("result").element("Accounts").elements("row");

            if (null != rows && rows.size() != 0) {
                for (Element element : rows) {
                    Map<String, String> map = Maps.newHashMap();
                    List<Element> nodeList = element.elements("FL");
                    for (Element node : nodeList) {
                        map.put(node.attributeValue("val"), node.getStringValue());
                    }
                    mapList.add(map);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            return mapList;
        }
    }

    /**
     * 获取crm用户信息列表
     */
    public List<Map<String,String>> getMapCrmUser(String token) {
        List<Map<String,String>> mapList = Lists.newArrayList();
        try{
            String columns = "users(id,email)";
            StringBuilder params = new StringBuilder();
            params.append("?authtoken=").append(token);
            params.append("&scope=").append("crmapi");
            params.append("&type=").append("AllUsers");
            params.append("&selectColumns=").append(URLEncoder.encode(columns));
            InputStream xmlInput = Request.Get(userUrl + params.toString()).useExpectContinue().execute().returnContent().asStream();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xmlInput);
            List<Element> rows = document.getRootElement().elements("user");

            if (null != rows && rows.size() != 0) {
                for (Element element : rows) {
                    Map<String, String> map = Maps.newHashMap();
                    List<DefaultAttribute> nodeList = element.attributes();
                    for (DefaultAttribute node : nodeList) {
                        map.put(node.getQName().getName(), node.getStringValue());
                    }
                    mapList.add(map);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            return mapList;
        }
    }

    /**
     * 获取相关的客户信息列表
     * @return
     */
    public List<String> getMapDeleteId(String token, String columns){
        List<String> idList = Lists.newArrayList();
        try{
            StringBuilder params = new StringBuilder();
            params.append("?authtoken=").append(token);
            params.append("&scope=").append("crmapi");
            InputStream xmlInput = Request.Get(deleteCustomerUrl + params.toString()).useExpectContinue().execute().returnContent().asStream();

            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(xmlInput);
            //获取已删除的客户id集合 ,逗号分隔
            String ids = document.getRootElement().element("result").element("DeletedIDs").getText();
            if (StringUtils.isNotEmpty(ids)) {
                idList = Arrays.asList(StringUtils.split(ids,","));
                return idList;
            }
        }catch (IOException e){
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            return idList;
        }
    }
}
