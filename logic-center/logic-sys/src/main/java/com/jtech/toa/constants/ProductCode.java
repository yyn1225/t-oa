package com.jtech.toa.constants;

import com.jtech.marble.error.ErrorCode;

/**
 * <p>产品错误码</p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
public enum ProductCode implements ErrorCode {
    /**
     * 成功码
     */
    SUCCESS_CODE(20000),
    /**
     * 物料号重复
     */
    CODE_IS_EXIST(20001),
    /**
     * 系统错误
     */
    SYSTEM_INSIDE_ERROR(20002),
    /**
     *  没有权限
     */
    NO_PERMISSION(20003),
    /**
     *  资源被占用,无法删除
     */
    RESOURCE_REPEAT(20004),
    /**
     *  没有上传文件
     */
    NO_UPLOAD_EXCEL(20005),
    /**
     *  文件不存在
     */
    NOT_FOUND_EXCEL(20006),
    /**
     *  物料号为空
     */
    CODE_IS_NULL(20007),
    /**
     *  物料号无效
     */
    CODE_IS_INVALID(20008),
    /**
     *  数据格式错误
     */
    DATA_IN_WRONG_FORMAT(20009),
    /**
     *  物料号重复
     */
    SCNO_REPEAT(20010),
    /**
     *  产品系列名称无效
     */
    SERIES_IS_INVALID(20011),
    /**
     *  箱体物料号无效
     */
    BOX_IS_INVALID(20012),
    /**
     *  产品系列为空
     */
    SERIES_IS_NULL(20013),
    /**
     *  备件物料号无效
     */
    SPARE_IS_INVALID(20014),
    /**
     *  excel格式有误
     */
    EXCEL_IS_INVALID(20015),
    /**
     *  excel不存在
     */
    EXCEL_IS_NULL(20016),
    /**
     *  模组物料号为空
     */
    MODULE_IS_NULL(20017),
    /**
     *  物料备件已存在
     */
    MATERIAL_SPARES_IS_ALREADY(20018),
    /**
     *  产品物料号无效
     */
    PRODUCT_IS_INVALID(20019);
    private final int code;

    /**
     * 错误码
     *
     * @param code code
     */
    ProductCode(final int code) {
        this.code = code;
    }


    @Override
    public int getCode() {
        return this.code;
    }
}
