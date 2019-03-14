/*
 * Copyright © 2015-2017, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.sync.manage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.sync.syncinterface.OaInterface;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;

/**
 * <p> </p>
 *
 * @author mdzhang
 * @version 1.0
 * @since JDK 1.6
 */
@Component
public class OaSyncJob {
    @Value("${oa.is-synchronization-data}")
    private boolean isSynchronizationData;
    private Logger logger=Logger.getLogger(OaSyncJob.class);

    @Scheduled(cron = "0 0 2 * * *") //每天夜里两点同步数据
    public void syncUserInfo(){
        logger.info("begin sync user info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync user data......");
            try { //这里捕获异常，只是为了不会因为异常导致JOB停止
                OaInterface oaInterface = new OaInterface();
                JSONArray array = oaInterface.getUserList();
                Date date = new Date();
                IUserService userService = SpringContextHolder.getBean(IUserService.class);
                if (null != array && array.size() > 0) {
                    for (Object obj : array) {
                        JSONObject userObj = JSONObject.parseObject(obj.toString());
                        User exist = userService.selectOne(new EntityWrapper<User>().eq("work_no", userObj.getString("PERSON_NUMBER")));
                        if (null == exist) {
                            User user = new User();
                            user.setCreateTime(date);
                            user.setPassword("2c3c6f2348783a578225df3bcbd489fc6a0c508b");
                            user.setSalt("1ec6129fdee90a83");
                            user.setLanguage("zh");
                            user.setLanguageDefault("zh");
                            user.setArea(0);
                            user.setDeleteAble(0);
                            user.setDeleteFlag(0);
                            user.setStatus(1);
                            user.setEmail(userObj.getString("email"));
                            user.setWorkNo(userObj.getString("PERSON_NUMBER"));
                            user.setPhone(userObj.getString("mobile"));
                            user.setDeleteFlag("1".equals(userObj.getString("status")) ? 0 : 1);
                            user.setName(userObj.getString("FULL_NAME"));
                            user.setLoginName(userObj.getString("PERSON_NUMBER"));
                            try {
                                if (!userService.insert(user)) {
                                    logger.error(userObj.getString("PERSON_NUMBER") + "保存失败！");
                                } else {
                                    logger.info(user.getLoginName() + "保存成功");
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                                logger.error(JSONObject.toJSONString(user));
                                continue;
                            }
                        } else {
                            exist.setEmail(userObj.getString("email"));
                            exist.setWorkNo(userObj.getString("PERSON_NUMBER"));
                            exist.setPhone(userObj.getString("mobile"));
                            exist.setDeleteFlag("1".equals(userObj.getString("status")) ? 0 : 1);
                            exist.setName(userObj.getString("FULL_NAME"));
                            exist.setLoginName(userObj.getString("PERSON_NUMBER"));
                            try {
                                if (!userService.updateById(exist)) {
                                    logger.error(userObj.getString("PERSON_NUMBER") + "保存失败！");
                                } else {
                                    logger.info(exist.getLoginName() + "保存成功");
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                                logger.error(JSONObject.toJSONString(exist));
                                continue;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    @Scheduled(cron = "0 0 4 * * *") //每天夜里4点同步数据
//    public void syncCustomerOwner(){
//        OaInterface oaInterface = new OaInterface();
//        List<Customer> customerList = customerService.findUnsignedList();
//        List<User> userList = userService.selectList(new EntityWrapper<User>());
//        Map<String,Integer> userMap = Maps.newHashMap();
//        for(User user:userList){
//            userMap.put(user.getWorkNo(),user.getId());
//        }
//        int index=0;
//        while(index<customerList.size()){
//            List<String> params = Lists.newArrayList();
//            Map<String,Integer> customerMap = Maps.newHashMap();
//            for(int i=index;i<Math.min(index+20,customerList.size());i++){
//                Customer customer = customerList.get(i);
//                params.add(customer.getCrmId());
//                customerMap.put(customer.getCrmId(),customer.getId());
//            }
//            JSONArray array = oaInterface.getCustomerList(params);
//            for(Object obj:array){
//                JSONObject crm = JSONObject.parseObject(obj.toString());
//                String workNo = crm.getString("lid");
//                String customer = crm.getString("cid");
//                if(!StringUtils.isEmpty(workNo) && !StringUtils.isEmpty(customer) && null!=userMap.get(workNo)){
//                    try{
//                        CustomerUser customerUser = new CustomerUser();
//                        customerUser.setAssigner(0);
//                        customerUser.setAssignTime(DateTime.now());
//                        customerUser.setCustomer(customerMap.get(customer));
//                        customerUser.setUsers(userMap.get(workNo));
//                        customerUser.insert();
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }finally {
//                        continue;
//                    }
//                }
//            }
//            index+=20;
//        }
//    }
}
