package com.jtech.toa.model.query;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jtech.marble.StringPool;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class OfferQuery {

    /**
     * 单号
     */
    private String num;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 报价人
     */
    private Integer userId;
    /**
     * 屏体
     */
    private List<Integer> series;
    /**
     * 审批状态
     */
    private Integer status;

    /**
     * 审批人
     */
    private Integer optUser;

    /**
     * 审批状态
     */
    private Integer examine;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 付款方式
     */
    private String payment;
    /**
     * 备注
     */
    private String remark;
    /**
     * 喜好标记
     */
    private Boolean preferenceFlag;

    /**
     * 区域集合
     */
    private List<Integer> areaList;

    /**
     * 销售
     */
    private String sales;

    public String getCustomerNameLike() {
        if (this.customerName == null) {
            this.customerName = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(customerName, SqlLike.DEFAULT);
    }

    public Date getParseStartTime() {
        if (StringUtils.isNotEmpty(this.startTime)) {
            return DateTime.parse(this.startTime).toDate();
        }
        return null;
    }

    public Date getParseEndTime() {
        if (StringUtils.isNotEmpty(this.endTime)) {
            Date date = DateTime.parse(this.endTime).toDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 23);
            calendar.add(Calendar.MINUTE, 59);
            calendar.add(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null;
    }

    public String getPaymentLike() {
        if (this.payment == null) {
            this.payment = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(payment, SqlLike.DEFAULT);
    }

    public String getRemarkLike(){
        if (this.remark == null) {
            this.remark = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(remark, SqlLike.DEFAULT);
    }

    public String getNumLike(){
        if (this.num == null) {
            this.num = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(num, SqlLike.DEFAULT);
    }

    public String getSalesLike() {
        if (this.sales == null) {
            this.sales = StringPool.EMPTY;
        }
        return SqlUtils.concatLike(sales, SqlLike.DEFAULT);
    }
}
