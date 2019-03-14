/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.sync.syncinterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.jtech.toa.model.sap.SapHeader;
import com.jtech.toa.model.sap.SapOrders;
import com.jtech.toa.model.sap.SapRate;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;

import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
public class SapInterface {
    private final JCoDestination destination;
    private final JCoRepository repository;

    public SapInterface() {
        try {
            destination = JCoDestinationManager.getDestination("Sap");
            repository = destination.getRepository();
        } catch (JCoException e) {
            e.printStackTrace();
            throw new RuntimeException("RFC接口初始化失败！");
        }
    }

    /**
     * 获取订单
     * @return
     */
    public Map<String,Object> getOrderList(){
        try {
            JCoFunction fm = repository.getFunction("Z_SOLIST_SAP_TO_SDTOOL");
            JCoParameterList fields = fm.getImportParameterList();
            fields.setValue("I_VBELN_L","");
            fm.execute(destination);
            JCoTable headList = fm.getTableParameterList().getTable("OUT_SOHEAD");//发票抬头信息
            JCoTable itemList = fm.getTableParameterList().getTable("OUT_SOITEM");//订单明细信息
            Map<String, Object> map = Maps.newHashMap();
            List<SapOrders> orderList = Lists.newArrayList();
            List<SapHeader> headerList = Lists.newArrayList();
            for (int i = 0; i < itemList.getNumRows(); i++) {
                itemList.setRow(i); //设置读取返回列表中的某一行
                headList.setRow(i);
                SapHeader header = new SapHeader();
                SapOrders order = new SapOrders();
                header.setVbeln(itemList.getString("VBELN"));
                header.setVkorg(itemList.getString("VKORG"));
                header.setVtext(itemList.getString("VTEXT"));
                header.setVkbur(itemList.getString("VKBUR"));
                header.setBezei(itemList.getString("BEZEI"));
                header.setErdat(itemList.getString("ERDAT"));
                header.setVdatu(itemList.getString("VDATU"));
                header.setKunnr(itemList.getString("KUNNR"));
                header.setName1(itemList.getString("NAME1"));
                header.setName2(itemList.getString("NAME2"));
                header.setLand1(itemList.getString("LAND1"));
                header.setLandx(itemList.getString("LANDX"));
                header.setAdrnr(itemList.getString("ADRNR"));
                header.setStreet(itemList.getString("STREET"));
                header.setCname(itemList.getString("CNAME"));
                header.setKunnr2(itemList.getString("KUNNR2"));
                header.setSname(itemList.getString("SNAME"));
                header.setNetwr(itemList.getString("NETWR"));
                header.setWaerk(itemList.getInt("WAERK"));
                header.setZzwarranty(itemList.getBigDecimal("ZZWARRANTY"));
                header.setZzarea(itemList.getBigDecimal("ZZAREA"));
                header.setZzproportion(itemList.getBigDecimal("ZZPROPORTION"));
                header.setZzintt(itemList.getString("ZZINTT"));
                header.setCredit(itemList.getString("CREDIT"));
                header.setZztransport(itemList.getString("ZZTRANSPORT"));
                header.setZzpayment(itemList.getString("ZZPAYMENT"));
                header.setZzproductname(itemList.getString("ZZPRODUCTNAME"));
                header.setOrdermarketseg(itemList.getString("ORDERMARKETSEG"));

                order.setVbeln(itemList.getString("VBELN"));
                order.setPosnr(itemList.getInt("POSNR"));
                order.setMatnr(itemList.getString("MATNR"));
                order.setMaktx(itemList.getString("MAKTX"));
                order.setKwmeng(itemList.getInt("KWMENG"));
                order.setMeins(itemList.getString("MEINS"));
                order.setAbgru(itemList.getString("ABGRU"));
                order.setPrctr(itemList.getString("PRCTR"));
                order.setEdatu(itemList.getString("EDATU"));
                order.setWaerk(itemList.getInt("WAERK"));
                order.setZkwi1(itemList.getInt("ZKWIL"));

                orderList.add(order);
                headerList.add(header);
            }
            map.put("header",headerList);
            map.put("order",orderList);
            return map;
        } catch (JCoException e) {
            e.printStackTrace();
            return Maps.newHashMap();
        }
    }

    /**
     * 获取利率信息
     * @return
     */
    public List<SapRate> getRateInfo(){
        try {
            JCoFunction fm = repository.getFunction("ZFI_EXRATE_SAP2ETL");
//            JCoParameterList fields = fm.getImportParameterList();
//            if(!StringUtils.isEmpty(formateDate)){
//                fields.setValue("I_DATE",formateDate);
//            }
            fm.execute(destination);
            JCoTable resultList = fm.getTableParameterList().getTable("O_TAB_ZTCURR");//汇率信息
            List<SapRate> results = Lists.newArrayList();
            for (int i = 0; i < resultList.getNumRows(); i++) {
                resultList.setRow(i);
                SapRate rate = new SapRate();
                rate.setMandt(resultList.getString("MANDT"));
                rate.setKurst(resultList.getString("KURST"));
                rate.setFcurr(resultList.getString("FCURR"));
                rate.setTcurr(resultList.getString("TCURR"));
                rate.setGdatu(resultList.getString("GDATU"));
                rate.setUkurs(resultList.getBigDecimal("UKURS"));
                rate.setFfact(resultList.getBigDecimal("FFACT"));
                rate.setTfact(resultList.getBigDecimal("TFACT"));
                rate.setEdate(resultList.getString("EDATE"));
                results.add(rate);
            }
            return results;
        } catch (JCoException e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }
    }

    /**
     * 获取备件信息
     */
    public Map<String,String> getSpareInfos(String scnNo){
        Map<String,String> map = Maps.newHashMap();
        try{
            JCoFunction fm = repository.getFunction("Z_MAT_SAP_TO_SDTOOL");
            JCoParameterList fields = fm.getImportParameterList();
            fields.setValue("I_MATNR_L",scnNo);
            fields.setValue("I_LANGU","EN");
            fm.execute(destination);
            JCoTable rows = fm.getTableParameterList().getTable("OUT_MATLIST");
            if(rows.getNumRows()>0){
                rows.setRow(0);
                map.put("MATNR",rows.getString("MATNR"));
                map.put("MAKTX",rows.getString("MAKTX"));
                map.put("MEINS",rows.getString("MEINS"));
            }else{
                return null;
            }
        }catch (JCoException e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取产品描述信息
     */
    public Map<String,String> getProductInfos(String scnNo, String lang){
        Map<String,String> map = Maps.newHashMap();
        try{
            JCoFunction fm = repository.getFunction("Z_MAT_SAP_TO_SDTOOL");
            JCoParameterList fields = fm.getImportParameterList();
            fields.setValue("I_MATNR_L",scnNo);
            fields.setValue("I_LANGU",lang);
            fm.execute(destination);
            JCoTable rows = fm.getTableParameterList().getTable("OUT_MATLIST");
            if(rows.getNumRows()>0){
                rows.setRow(0);
                map.put("MATNR",rows.getString("MATNR"));
                map.put("MAKTX",rows.getString("MAKTX"));
            }else{
                return null;
            }
        }catch (JCoException e){
            e.printStackTrace();
        }
        return map;
    }
}
