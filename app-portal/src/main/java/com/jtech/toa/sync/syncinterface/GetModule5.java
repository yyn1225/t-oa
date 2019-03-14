/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2018
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.sync.syncinterface;

import com.zoho.crm.library.api.response.BulkAPIResponse;
import com.zoho.crm.library.crud.ZCRMModule;
import com.zoho.crm.library.crud.ZCRMRecord;
import com.zoho.crm.library.exception.ZCRMException;
import com.zoho.crm.library.setup.restclient.ZCRMRestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetModule5
{

    private static final String MOD = "My_Quotes";
    private static final String CUSTOMER_MOD = "Accounts";
    private static final String USER_MOD = "users";

    public static void main(String[] args) throws Exception
    {
        ZCRMRestClient.initialize();
        ZCRMModule module = ZCRMModule.getInstance(USER_MOD); // module apiname
        //查询
        query(module);

        //queryUser(module);

//        String condition = "((Account_Name:equals:HXC GRAND BALLROOM(PUDU)SDN BHD))";
//        BulkAPIResponse response =module.searchByCriteria(condition);
//        List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
//        System.out.println("dddddddd="+eles.get(0).getEntityId());

        //新增
//        create(module);
        
        //修改
//        update(module);
        //删除
//        delete(module);
        
    }

    private static void delete(ZCRMModule module) throws ZCRMException
    {
        List<Long> l = new ArrayList<Long>();
        l.add(1451898000029903013L);
        BulkAPIResponse rsp = module.deleteRecords(l);
        System.out.println(rsp.getResponseJSON());
        
    }

    private static void update(ZCRMModule module) throws ZCRMException
    {
        List<ZCRMRecord> records = new ArrayList<ZCRMRecord>();
        ZCRMRecord el = new ZCRMRecord(MOD);
        el.setFieldValue("Name", "testProject3");
        el.setFieldValue("Sub_Total", "2344323");
        records.add(el);
        BulkAPIResponse rsp = module.upsertRecords(records);
        System.out.println(rsp.getResponseJSON());
    }

    /**
     * 
     * @param module
     * @throws ZCRMException
     */
    private static void create(ZCRMModule module) throws ZCRMException
    {
        ZCRMRecord el = new ZCRMRecord(MOD);
        el.setFieldValue("Name", "testProject3");
//        ZCRMUser owner = ZCRMUser.getInstance(1451898000000083057L);
//        el.setOwner(owner);
//        el.setFieldValue("Account_Name", ZCRMRecord.getInstance("Accounts", 1451898000016367007L));
        
        List<ZCRMRecord> records = new ArrayList<ZCRMRecord>();
        records.add(el);
        BulkAPIResponse rsp = module.createRecords(records);
        System.out.println(rsp.getResponseJSON());
    }

    private static void query(ZCRMModule module) throws ZCRMException
    {
        BulkAPIResponse response = module.getRecords();
        List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
        System.out.println("lllllllllll="+eles.size());
        HashMap<String, Object> d = eles.get(0).getData();
        //所有字段信息
        System.out.println("输出所有的属性,新增的时候会用到");
        for(String key : d.keySet())
        {
            System.out.println(key + " = " + d.get(key));
        }
        //所有元素
        System.out.println("输出所有的数据及其ID值,删除的时候会用到该ID");
        for(ZCRMRecord r : eles)
        {
            System.out.println(r.getData().get("Name") + " = " + r.getEntityId());
        }
        
    }

    private static void queryUser(ZCRMModule module) throws Exception
    {
        BulkAPIResponse response = module.getRecords(null,1,5,null);
        org.json.JSONObject js = response.getResponseJSON();
        System.out.println("dfsdjf="+js);

    }
}
