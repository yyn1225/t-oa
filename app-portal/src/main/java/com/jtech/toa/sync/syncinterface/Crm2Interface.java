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

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import com.zoho.crm.library.api.response.BulkAPIResponse;
import com.zoho.crm.library.crud.ZCRMModule;
import com.zoho.crm.library.crud.ZCRMRecord;
import com.zoho.crm.library.exception.ZCRMException;
import com.zoho.crm.library.setup.restclient.ZCRMRestClient;
import com.zoho.crm.library.setup.users.ZCRMUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.jtech.toa.auth.model.RequestSubject;
import com.jtech.toa.entity.basic.Dict;
import com.jtech.toa.entity.offer.Offer;
import com.jtech.toa.entity.offer.OfferPanels;
import com.jtech.toa.entity.offer.OfferTransfer;
import com.jtech.toa.entity.product.Product;
import com.jtech.toa.entity.product.Series;
import com.jtech.toa.model.vo.OfferVo;
import com.jtech.toa.model.vo.PanelVo;
import com.jtech.toa.service.basic.IDictService;
import com.jtech.toa.service.product.IProductService;
import com.jtech.toa.service.product.ISeriesService;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> zoho crm api 2.0 接口 </p>
 *
 * @author EE
 * @version 1.0
 * @since JDK 1.7
 */

public class Crm2Interface {
    private static final String MOD = "My_Quotes";
    private static final String CUSTOMER_MOD = "Accounts";
    private static final String USER_MOD = "users";
    private static final Logger LOGGER = LoggerFactory.getLogger(Crm2Interface.class);

    /**
     *  zoho crm2.0 同步创建报价单
     * @param offer 报价单对象
     */
    public static void createOffer(Offer offer, OfferVo vo, IDictService dictService,
                                   IUserService userService,RequestSubject user,
                                   IProductService productService,ISeriesService seriesService){
        LOGGER.info("zoho crm 2.0 create offer start,num:"+offer.getNum());
        try {
            ZCRMRestClient.initialize();
            ZCRMModule module = ZCRMModule.getInstance(MOD);
            ZCRMRecord el = new ZCRMRecord(MOD);

            BigDecimal screenSeqHeight = new BigDecimal("0");
            BigDecimal screenSeqWidth = new BigDecimal("0");
            BigDecimal screenSeq = new BigDecimal("0");
            String description = "";
            String partNo = "";
            Integer panelQtyHeight = 0;
            Integer panelQtyWidth = 0;
            Integer panelQty = 0;
            String configuration = "";
            List<PanelVo> panelVoList = vo.getPanelList();
            Integer panelId = 0;
            boolean splitScreen = false;
            if(!panelVoList.isEmpty()){
                for(PanelVo panelVo:panelVoList){
                    OfferPanels panels = panelVo.getPanels();
                    List<OfferPanels> childPanels = panels.getChildPanels();
                    if(childPanels.size()>1){
                        splitScreen = true;
                    }

                    if(!childPanels.isEmpty()){
                        for(int i=0;i<childPanels.size();i++){
                            panelQtyHeight = childPanels.get(i).getLcount();
                            panelQtyWidth = childPanels.get(i).getWcount();
                            panelQty = panelQty + (panelQtyHeight*panelQtyWidth);
                            partNo += childPanels.get(i).getPartNo()+",";
                            screenSeqHeight = childPanels.get(i).getLongitudinal();
                            screenSeqWidth = childPanels.get(i).getHorizontal();
                            screenSeq = screenSeq.add(screenSeqHeight.multiply(screenSeqWidth));
                            panelId = childPanels.get(i).getPanel();
                            //找出配置信息
                            Product product = productService.selectByIdAndLang(panelId,user.getLanguage());
                            configuration += product.getState() + ",";
                        }

                    }
                    break;
                }
            }
            //是否拼屏
            LOGGER.info("splitScreen:"+splitScreen);
            el.setFieldValue("Splicing",splitScreen);
            //料号
            if(!Strings.isNullOrEmpty(partNo)){
                partNo = partNo.substring(0,partNo.length()-1);
            }
            LOGGER.debug("partNo:"+partNo);
            el.setFieldValue("PartNO",partNo);
            //配置
            if(!Strings.isNullOrEmpty(configuration)){
                configuration = configuration.substring(0,configuration.length()-1);
            }
            LOGGER.debug("configuration:"+configuration);
            el.setFieldValue("Configuration",configuration);
            //屏体面积高
            if(splitScreen) {
                el.setFieldValue("Screen_Seq_Height", "");
            }else{
                el.setFieldValue("Screen_Seq_Height", screenSeqHeight.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            //屏体面积宽
            if(splitScreen) {
                el.setFieldValue("Screen_Seq_Width", "");
            }else{
                el.setFieldValue("Screen_Seq_Width", screenSeqWidth.setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            //屏体面积
            el.setFieldValue("Screen_Seq",screenSeq.setScale(2,BigDecimal.ROUND_HALF_UP));
            //箱体数量高
            if(splitScreen) {
                el.setFieldValue("Panel_Qty_Height", "");
            }else{
                el.setFieldValue("Panel_Qty_Height", panelQtyHeight);
            }
            //箱体数量宽
            if(splitScreen) {
                el.setFieldValue("Panel_Qty_Width", "");
            }else{
                el.setFieldValue("Panel_Qty_Width", panelQtyWidth);
            }
            //箱体数量
            el.setFieldValue("Panel_Qty",panelQty);
            Dict dict = dictService.selectDictByCode("trade_company",offer.getTrader(),user.getLanguage());
            String company = dict == null ?"":dict.getName();
            //贸易公司
            el.setFieldValue("Company",company);
            //折扣率
            el.setFieldValue("Discount",offer.getTotalDiscount());
            //备注
            el.setFieldValue("Remark",description);
            //产品(根据panelId 找产品（系列名称）)
            String product = "";
            List<Series> seriesList = seriesService.getByProductId(panelId);
            if(!seriesList.isEmpty()){
                product = seriesList.get(0).getName();
            }
            LOGGER.debug("product:"+product);
            el.setFieldValue("Product",product);
            //项目名称
            el.setFieldValue("Project_Name",offer.getProjectName());
            el.setFieldValue("Name",offer.getNum());
            //el.setFieldValue("Last_Activity_Time","");
            //付款方式
            el.setFieldValue("Payment_terms",offer.getPayment());
            //el.setFieldValue("Record_Image","");
            //发货地址
            el.setFieldValue("Shipping_Address",offer.getShipping());
            //货币
            el.setFieldValue("Currency",offer.getMoneyUnit());
            long customerId = queryCustomer(offer.getCustomerName());
            if(customerId > 0){
                //客户名
                el.setFieldValue("Account_Name",queryCustomer(offer.getCustomerName()));
            }else{
                //客户名
                el.setFieldValue("Account_Name2",offer.getCustomerName());
            }
            //实际总价
            BigDecimal percentValue = new BigDecimal("100");
            BigDecimal yourPrice;
            if(offer.getSpecialPrice() == null){
                yourPrice = offer.getTotalPrice();
                el.setFieldValue("Your_Price",yourPrice);
            }else{
                yourPrice = offer.getSpecialPrice();
                el.setFieldValue("Your_Price",yourPrice);
            }
            LOGGER.debug("yourPrice:"+yourPrice);
            //状态
            el.setFieldValue("Quote_Stage",getOfferStatus(offer.getStatus(),"en"));
            //总价格()
            BigDecimal totalPrice = offer.getTotalPrice().divide(offer.getTotalDiscount()
                    .divide(percentValue,4,BigDecimal.ROUND_HALF_UP),2,BigDecimal.ROUND_HALF_UP);
            el.setFieldValue("Sub_Total",totalPrice);
            //尺寸单位
            el.setFieldValue("Dimension",offer.getSizeUnit()==1?"m":"ft");
            //交期
            el.setFieldValue("Delivery",offer.getWaitingDate());
            String termsOfTrade = "";
            OfferTransfer transfer = vo.getTransfer();
            if(transfer != null){
                termsOfTrade =  transfer.getTrade();
            }
            //贸易条款
            el.setFieldValue("Terms_of_trade",termsOfTrade);
            //SS访问链接
            //el.setFieldValue("SS_Link","");

            //创建时间(格式化时间为12小时制)
            SimpleDateFormat df_12=new SimpleDateFormat
                    ("yyyy-MM-dd KK:mm aa", Locale.ENGLISH);
            String createTime = df_12.format(offer.getCreateTime());
            LOGGER.debug("createTime:"+createTime);
            el.setFieldValue("Quoted_Date",createTime);

            Optional<User> userOptional = userService.findByUserId(user.getId());
            String crmUserId = "";
            if(userOptional.isPresent()){
                User queryUser = userOptional.get();
                crmUserId = queryUser.getCrmid();
            }
            LOGGER.info("crmUserId="+crmUserId);
            if(!Strings.isNullOrEmpty(crmUserId)) {
                ZCRMUser owner = ZCRMUser.getInstance(Long.parseLong(crmUserId));
                el.setOwner(owner);
                el.setCreatedBy(owner);
            }

            List<ZCRMRecord> records = new ArrayList<ZCRMRecord>();
            records.add(el);
            BulkAPIResponse rsp = module.createRecords(records);
            LOGGER.info("zoho crm 2.0 create offer end,num:"+offer.getNum()+"," +
                    "responseJson:"+rsp.getResponseJSON());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("zoho crm 2.0 create offer error:"+e.getMessage());
        }
    }

    /**
     * 同步删除crm报价单
     * @param quoteNum 报价单号
     */
    public static void deleteOffer(String quoteNum)
    {
        try {
            LOGGER.info("zoho crm 2.0 delete offer start,quoteNum:"+quoteNum);
            ZCRMRestClient.initialize();
            ZCRMModule module = ZCRMModule.getInstance(MOD);
            long id  = queryQuote(quoteNum);
            List<Long> l = new ArrayList<Long>();
            l.add(id);
            BulkAPIResponse rsp = module.deleteRecords(l);
            LOGGER.info("zoho crm 2.0 delete offer end,responseJSON:"+rsp.getResponseJSON());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("zoho crm 2.0 delete offer error:"+e.getMessage());
        }

    }

    /**
     * 调用crm接口查询客户id
     * @param customerName 客户名称
     * @return 客户id
     */
    private static long queryCustomer(String customerName) throws ZCRMException {
        long customerId = 0;
        LOGGER.info("query customer start,customerName:"+customerName);
        //ZCRMRestClient.initialize();
        ZCRMModule module = ZCRMModule.getInstance(CUSTOMER_MOD);
        String convertCustomerName = coventCustomerName(customerName);
        LOGGER.info("convert customerName:"+convertCustomerName);

        String condition = "((Account_Name:equals:"+convertCustomerName+"))";
//        String condition = "((Account_Name:equals:"+customerName+"))";
        BulkAPIResponse response =module.searchByCriteria(condition);
        List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
        if(!eles.isEmpty()) {
            customerId = eles.get(0).getEntityId();
        }
        LOGGER.info("query customer end,customerId:"+customerId);

        return customerId;
    }

    /**
     * 查询用户id
     * @param email 用户邮箱
     * @return 用户crm id
     */
    private static long queryUser(String email) throws ZCRMException {
        long crmUserId = 0;

        LOGGER.info("query user start,email:"+email);
        //ZCRMRestClient.initialize();
        ZCRMModule module = ZCRMModule.getInstance(USER_MOD);

        String condition = "((email:equals:"+email+"))";
        BulkAPIResponse response =module.searchByCriteria(condition);
        List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
        if(!eles.isEmpty()) {
            crmUserId = eles.get(0).getEntityId();
        }
        LOGGER.info("query user end,crmUserId:"+crmUserId);

        return crmUserId;
    }


    /**
     * 查询报价单id
     * @param quoteNum 报价单号
     * @return 报价单id
     */
    private static long queryQuote(String quoteNum) throws ZCRMException {
        long quoteId = 0;

        LOGGER.info("query quote start,quoteNum:"+quoteNum);
        //ZCRMRestClient.initialize();
        ZCRMModule module = ZCRMModule.getInstance(MOD);

        String condition = "((Name:equals:"+quoteNum+"))";
        BulkAPIResponse response =module.searchByCriteria(condition);
        List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
        if(!eles.isEmpty()) {
            quoteId = eles.get(0).getEntityId();
        }
        LOGGER.info("query quote end,quoteId:"+quoteId);

        return quoteId;
    }

    /**
     * 得到报价单状态描述
     * @param status 状态id
     * @return 状态描述
     */
    private static String getOfferStatus(Integer status,String language){
        String statusDesc = "";
        String chinese = "zh";
        String englist = "en";
        if(status == 0){
            if(language.equals(chinese)){
                statusDesc = "草稿";
            }
            if(language.equals(englist)){
                statusDesc = "Draft";
            }
        }
        if(status == 1){
            if(language.equals(chinese)){
                statusDesc = "审批中";
            }
            if(language.equals(englist)){
                statusDesc = "In approval";
            }
        }
        if(status == 2){
            if(language.equals(chinese)){
                statusDesc = "通过";
            }
            if(language.equals(englist)){
                statusDesc = "Adopt";
            }
        }
        if(status == 3){
            if(language.equals(chinese)){
                statusDesc = "不通过";
            }
            if(language.equals(englist)){
                statusDesc = "Refuse";
            }
        }
        if(status == 4){
            if(language.equals(chinese)){
                statusDesc = "已撤回";
            }
            if(language.equals(englist)){
                statusDesc = "Retract";
            }
        }
        if(status == 5){
            if(language.equals(chinese)){
                statusDesc = "已完成";
            }
            if(language.equals(englist)){
                statusDesc = "Finished";
            }
        }
        return statusDesc;
    }


    /**
     * 将字符串转成unicode
     * @param str 待转字符串
     * @return unicode字符串
     */
    private static String convertUnicode(String str)
    {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>>8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);

        }
        return (new String(sb));
    }

    /**
     * 将字符串转成utf-8
     * @param str 待转字符串
     * @return utf-8字符串
     */
    private static String convertUTF8(String str){
        String result = "";
        try {
            if(!Strings.isNullOrEmpty(str)) {
                result = new String(str.getBytes(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 转换客户名称，将客户名称中带"()"的转换为"\\(\\)"
     * 否则zoho crm不支持查询
     * @param customerName 客户名称
     * @return
     */
    public static String coventCustomerName(String customerName){
        if(Strings.isNullOrEmpty(customerName)){
            return "";
        }
        String reStr = customerName;
        if(customerName.indexOf("(") != -1){
            String s1 = reStr.substring(0,reStr.indexOf("("));
            String one = s1 + "\\(";
            String s2 = reStr.substring(reStr.indexOf("(")+1);
            reStr = one + s2;
        }
        if(customerName.indexOf(")") != -1){
            String s1 = reStr.substring(0,reStr.indexOf(")"));
            String one = s1 + "\\)";
            String s2 = reStr.substring(reStr.indexOf(")")+1);
            reStr = one + s2;
        }
        return reStr;
    }

    public static void main(String [] args){
//        try {
//            ZCRMRestClient.initialize();
//            long customerId = 0;
//            ZCRMModule module = ZCRMModule.getInstance(CUSTOMER_MOD);
//            String customerName = "Creative Technology (Asia Pacific) Co., Ltd.";
//            String queryCustomer = coventCustomerName(customerName);
//            String condition = "((Account_Name:equals:"+queryCustomer+"))";
////        String condition = "((Account_Name:equals:"+customerName+"))";
//            BulkAPIResponse response =module.searchByCriteria(condition);
//            List<ZCRMRecord> eles = (List<ZCRMRecord>) response.getData();
//            if(!eles.isEmpty()) {
//                customerId = eles.get(0).getEntityId();
//            }
//            System.out.println("customerId="+customerId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            ZCRMRestClient.initialize();
            ZCRMRestClient client = ZCRMRestClient.getInstance();

            BulkAPIResponse response = client.getOrganizationInstance().getAllUsers();

            List<ZCRMUser> users = (List<ZCRMUser>) response.getData();

            System.out.println("lllllllllll="+users.size());
            HashMap<String, Object> d = users.get(0).getData();
            //所有字段信息
            System.out.println("输出所有的属性,新增的时候会用到");
            for(String key : d.keySet())
            {
                System.out.println(key + " = " + d.get(key));
            }
            //所有元素
            System.out.println("输出所有的数据及其ID值,删除的时候会用到该ID");
            for(ZCRMUser r : users)
            {
                System.out.println(r.getData().get("Name") + " = " + r.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
