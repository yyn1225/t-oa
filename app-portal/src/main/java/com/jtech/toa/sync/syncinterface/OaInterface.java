/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.sync.syncinterface;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

import com.jtech.toa.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.fluent.Request;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
public class OaInterface {

    private final String UserPath="http://service.absen.cn:9092/AbsenWS/salesTool/getPersons";
    private final String customerPath="http://service.absen.cn:9092/AbsenWS/salesTool/getRelOfAccAndEmp";
    private Logger logger=Logger.getLogger(OaInterface.class);

    public JSONArray getUserList(){
        try{
            String json = Request.Get(UserPath).useExpectContinue().execute().returnContent().asString();
            return JSONArray.parseArray(json);
        }catch (IOException e){
            logger.error("获取用户信息接口调用失败："+e.getMessage());
            e.printStackTrace();
        }catch (JSONException e){
            logger.error("JSON转换失败！");
            e.printStackTrace();
        }
        return null;
    }


    public JSONArray getCustomerList(List<String> crmIds) {
        try {
            String json = Request.Get(customerPath + "?crmids=" + StringUtils.join(crmIds, ",")).useExpectContinue().execute().returnContent().asString();
            return JSONArray.parseArray(json);
        } catch (IOException e) {
            logger.error("获取用户信息接口调用失败：" + e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            logger.error("JSON转换失败！");
            e.printStackTrace();
        }
        return null;
    }
}
