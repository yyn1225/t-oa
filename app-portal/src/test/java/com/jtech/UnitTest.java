/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech;

import com.google.common.collect.Lists;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.client.fluent.Request;
import org.junit.Test;
import org.springframework.boot.test.context.TestComponent;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtech.toa.sync.manage.SapMatnrManage;
import com.jtech.toa.sync.syncinterface.CrmInterface;
import com.jtech.toa.sync.syncinterface.OaInterface;


@TestComponent
public class UnitTest {
    @Test
    public  void getTest(){
        CrmInterface crmInterface = new CrmInterface();
        Object obj = crmInterface.getMapString(7, "", "");
        System.out.println(JSONObject.toJSONString(obj));
    }

    @Test
    public void manageSpares(){
        SapMatnrManage matnrManage = new SapMatnrManage();
        matnrManage.manageSapreList();
    }

    @Test
    public void testCustomer(){
        List<String> obj = Lists.newArrayList("1451898000015914010","1451898000015887001","1451898000015881013");
        OaInterface oaInterface = new OaInterface();
        JSONArray arr=oaInterface.getCustomerList(obj);
        System.out.println(arr.toJSONString());
    }


    /**
     * 生成二维码测试方法
     *
     * @throws WriterException
     * @throws IOException
     */
    @Test
    public void testEncode() throws WriterException, IOException {
        String filePath = "D://";
        String fileName = "zxing.png";
        String content = "www.baidu.com";
        int width = 300; // 图像宽度
        int height = 300; // 图像高度
        String format = "png";// 图像类型
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
        Path path = FileSystems.getDefault().getPath(filePath, fileName);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
        System.out.println("输出成功.");
    }

    @Test
    public void testNewOA(){
        String oaUrl = "http://113.108.97.125:8081/iam-extends/api/ss/vi/user/list";
        try {
            String json = Request.Get(oaUrl).useExpectContinue().
                    execute().returnContent().asString();
            System.out.println("json="+json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
