package com.jtech.toa.sync.manage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import com.jtech.toa.config.context.SpringContextHolder;
import com.jtech.toa.entity.system.SysExchange;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.service.system.ISysExchangeService;
import com.jtech.toa.sync.syncinterface.SapInterface;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Component
public class SapRateJob {
    @Value("${oa.is-synchronization-data}")
    private boolean isSynchronizationData;

    private Logger logger=Logger.getLogger(SapRateJob.class);

    /**
     * 每月1日晚上9点同步汇率
     */
    @Scheduled(cron = "* 0 21 1 * ?")
    public void sapRateInfo() {
        logger.info("begin sync sap rate info,isSynchronizationData:"+isSynchronizationData);
        if(isSynchronizationData) {
            logger.info("--------自动同步SAP汇率信息接口开始----------");
            try {
                SapInterface sapInterface = new SapInterface();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                List<SapRate> sapRateList = sapInterface.getRateInfo();
                ISysExchangeService sysExchangeService = SpringContextHolder.getBean(ISysExchangeService.class);

                for (SapRate sapRate : sapRateList) {
                    SysExchange exchange = sysExchangeService.selectOne(new EntityWrapper<SysExchange>().
                            eq("valid_date", formatDateStr(sapRate.getEdate())).eq("code", sapRate.getFcurr()));
                    if (exchange == null) {
                        exchange = new SysExchange();
                        exchange.setValidDate(formatDateStr(sapRate.getEdate()));
                    }
                    exchange.setCode(sapRate.getFcurr());
                    exchange.setRmb(sapRate.getUkurs());
                    exchange.setSyncTime(new Date());
                    exchange.setCurrency(sapRate.getRateKey(sapRate.getFcurr()));
                    boolean isOk = sysExchangeService.insertOrUpdate(exchange);
                    if (!isOk) {
                        logger.error("保存失败！");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("--------自动同步SAP汇率信息接口结束----------");
        }
    }

    /**
     * 将SAP同步的valid_date  01.02.2017这种字符串替换成 2017-01-02
     */
    private String formatDateStr(String dateStr) {
        if (StringUtils.isNotEmpty(dateStr)) {
            // 小数点(.)是特殊符号,需要添加双\\
            String[] dateStrArr = dateStr.split("\\.");
            return dateStrArr[2] + "-" + dateStrArr[1] + "-" + dateStrArr[0];
        }
        return null;
    }
}

