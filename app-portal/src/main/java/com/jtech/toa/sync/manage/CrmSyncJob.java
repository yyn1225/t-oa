package com.jtech.toa.sync.manage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.entity.customer.Customer;
import com.jtech.toa.entity.customer.CustomerUser;
import com.jtech.toa.service.customer.ICustomerService;
import com.jtech.toa.service.customer.ICustomerUserService;
import com.jtech.toa.sync.syncinterface.CrmInterface;
import com.jtech.toa.user.entity.User;
import com.jtech.toa.user.service.IUserService;


/**
 * <p>
 * 同步Crm客户接口
 * </p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class CrmSyncJob {

    @Value("${oa.is-synchronization-data}")
    private boolean isSynchronizationData;

    //private final String foreignToken = "673c768de2f681c0862ba6c9f307336c";
    private final String foreignToken = "70588900021c68d3f2da1360a8b3090c";
    private final String chinaToken = "8909809036b44552ee135fa767611ff0";
    private final String foreignColumns = "Accounts(Website,ACCOUNTID,Email,Phone,area,Account Number,Account Type,industry(客户行业),Account Name,Website,Account Owner,SMOWNERID)";
    private final String chinaColumns = "Accounts(ACCOUNTID,邮箱,Phone,销售组织,Account Number,Account Type,客户所在行业,Account Name,Account Owner,SMOWNERID)";
    private final String deleteColumns = "ACCOUNTID";
    private Logger logger = Logger.getLogger(CrmSyncJob.class);

    /**
     * 同步国际客户
     */
    @Scheduled(cron = "0 0 5,12 * * ?")
    public void crmSyncInfo() {
        logger.info("begin crm sync info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync crm data......");
            try {
                CrmInterface crmInterface = new CrmInterface();
                ICustomerService customerService = SpringContextHolder.getBean(ICustomerService.class);
                int i = 0;
                boolean doLoop = true;
                while (doLoop) {
                    List<Map<String, String>> mapList = crmInterface.getMapString(i, foreignToken, foreignColumns);
                    for (Map<String, String> map : mapList) {
                        Customer crm = customerService.selectOne(new EntityWrapper<Customer>().eq("crm_id", map.get("ACCOUNTID")));
                        if (crm != null) {
                            Customer customer = new Customer(); //todo 部门字段有待商榷
                            customer.setName(map.get("Account Name"));
                            customer.setWebsite(map.get("Website"));
                            customer.setOwnership(map.get("Account Owner"));
                            customer.setType(map.get("industry(客户行业)"));
                            customer.setAccountType(map.get("Account Type"));
                            customer.setAccountNumber(map.get("Account Number"));
                            customer.setLocation(map.get("area"));
                            customer.setPhone(map.get("Phone"));
                            customer.setEmail(map.get("Email"));
                            customer.setSmownerId(map.get("SMOWNERID"));
                            customer.setId(crm.getId());
                            boolean isOk = customerService.updateById(customer);
                            try {
                                if (!isOk) {
                                    logger.info(map.get("ACCOUNTID") + "更新失败！");
                                } else {
                                    logger.info(map.get("ACCOUNTID") + "更新成功！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Customer customer = new Customer();
                            customer.setName(map.get("Account Name"));
                            customer.setWebsite(map.get("Website"));
                            customer.setOwnership(map.get("Account Owner"));
                            customer.setType(map.get("industry(客户行业)"));
                            customer.setAccountType(map.get("Account Type"));
                            customer.setAccountNumber(map.get("Account Number"));
                            customer.setLocation(map.get("area"));
                            customer.setPhone(map.get("Phone"));
                            customer.setEmail(map.get("Email"));
                            customer.setStatus(Customer.Status.EFFECTIVE);
                            customer.setDeleteAble(Customer.DeleteAble.NO);
                            customer.setCrmId(map.get("ACCOUNTID"));
                            customer.setSmownerId(map.get("SMOWNERID"));
                            boolean isOk = customerService.insert(customer);
                            try {
                                if (!isOk) {
                                    logger.info(map.get("ACCOUNTID") + "保存失败！");
                                } else {
                                    logger.info(map.get("ACCOUNTID") + "保存成功！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (mapList.size() == 0) {
                        doLoop = false;
                    } else {
                        i++;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 国际用户客户关系表同步
     */
    @Scheduled(cron = "0 10 5,12 * * ?")
    public void crmUserInfo() {
        //try...catch防止同步停止
        logger.info("begin crm user info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync crm user data......");
            try {
                syncRelation(foreignToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步国内客户
     */
    @Scheduled(cron = "0 20 5,12 * * ?")
    public void crmChinaInfo() {
        logger.info("begin crm china info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync crm china data......");
            try {
                CrmInterface crmInterface = new CrmInterface();
                ICustomerService customerService = SpringContextHolder.getBean(ICustomerService.class);
                int i = 0;
                boolean doLoop = true;
                while (doLoop) {
                    List<Map<String, String>> mapList = crmInterface.getMapString(i, chinaToken, chinaColumns);
                    for (Map<String, String> map : mapList) {
                        Customer crm = customerService.selectOne(new EntityWrapper<Customer>().eq("crm_id", map.get("ACCOUNTID")));
                        Customer customer = new Customer();
                        customer.setName(map.get("Account Name"));
                        customer.setOwnership(map.get("Account Owner"));
                        customer.setType(map.get("客户所在行业"));
                        customer.setAccountType(map.get("Account Type"));
                        customer.setAccountNumber(map.get("Account Number"));
                        customer.setLocation(map.get("销售组织"));
                        customer.setPhone(map.get("Phone"));
                        customer.setEmail(map.get("邮箱"));
                        if (crm != null) {
                            customer.setId(crm.getId());
                            boolean isOk = customerService.updateById(customer);
                            try {
                                if (!isOk) {
                                    logger.info(map.get("ACCOUNTID") + "更新失败！");
                                } else {
                                    logger.info(map.get("ACCOUNTID") + "更新成功！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            customer.setStatus(Customer.Status.EFFECTIVE);
                            customer.setDeleteAble(Customer.DeleteAble.NO);
                            customer.setCrmId(map.get("ACCOUNTID"));
                            boolean isOk = customerService.insert(customer);
                            try {
                                if (!isOk) {
                                    logger.info(map.get("ACCOUNTID") + "保存失败！");
                                } else {
                                    logger.info(map.get("ACCOUNTID") + "保存成功！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (mapList.size() == 0) {
                        doLoop = false;
                    } else {
                        i++;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 国内用户客户关系表同步
     */
    @Scheduled(cron = "0 30 5,12 * * ?") //每天夜里22点同步数据
    public void crmChinaUser() {
        logger.info("begin crm china user,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin sync crm china user data......");
            //try...catch防止同步停止
            try {
                syncRelation(chinaToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除国际客户job
     * 每2小时执行一次
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void deleteForeignSync() {
        logger.info("begin delete foreign sync,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin delete foreign sync data......");
            try {
                deleteCustomer(foreignToken);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除国内客户job
     * 每2小时执行一次
     */
    @Scheduled(cron = "0 10 0/2 * * ?")
    public void deleteChinaSync() {
        logger.info("begin delete china sync,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("begin delete china sync data......");
            try {
                deleteCustomer(chinaToken);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 同步用户客户关系公共方法
     */
    private void syncRelation(String token) {
        IUserService userService = SpringContextHolder.getBean(IUserService.class);
        ICustomerService customerService = SpringContextHolder.getBean(ICustomerService.class);
        ICustomerUserService customerUserService = SpringContextHolder.getBean(ICustomerUserService.class);
        CrmInterface crmInterface = new CrmInterface();
        List<Map<String, String>> mapList = crmInterface.getMapCrmUser(token);
        for (Map<String, String> map : mapList) {
            User user = userService.selectOne(new EntityWrapper<User>().eq("email", map.get("email")));
            List<Customer> customer = customerService.selectList(new EntityWrapper<Customer>().eq("smowner_id", map.get("id")));
            if (null != user && CollectionUtils.isNotEmpty(customer)) {
                for (Customer c : customer) {
                    CustomerUser customerUser = customerUserService.selectOne(new EntityWrapper<CustomerUser>().eq("customer", c.getId()));
                    if (null != customerUser) {
                        if (!user.getId().equals(customerUser.getUsers())) {
                            customerUser.setUsers(user.getId());
                            customerUser.updateById();
                        }
                    } else {
                        CustomerUser cu = new CustomerUser();
                        cu.setCustomer(c.getId());
                        cu.setUsers(user.getId());
                        cu.setAssigner(0);
                        cu.setAssignTime(new Date());
                        cu.insert();
                    }
                }
            }
        }
    }

    /**
     * 删除客户公共方法
     */
    private void deleteCustomer(String token) {
        ICustomerService customerService = SpringContextHolder.getBean(ICustomerService.class);
        ICustomerUserService customerUserService = SpringContextHolder.getBean(ICustomerUserService.class);
        CrmInterface crmInterface = new CrmInterface();
        List<String> idList = crmInterface.getMapDeleteId(token, "ACCOUNTID");
        for (String s : idList) {
            Customer customer = customerService.selectOne(new EntityWrapper<Customer>().eq("crm_id", s));
            if (customer != null) {
                CustomerUser customerUser = customerUserService.selectOne(new EntityWrapper<CustomerUser>().eq("customer", customer.getId()));
                customer.deleteById();
                if (customerUser != null) {
                    customerUser.deleteById();
                }
            }
        }
    }
}

