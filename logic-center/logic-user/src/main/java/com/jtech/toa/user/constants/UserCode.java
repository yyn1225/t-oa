/*
 * The Hefei JingTong RDC(Research and Development Centre) Group.
 * __________________
 *
 *    Copyright 2015-2017
 *    All Rights Reserved.
 *
 *    NOTICE:  All information contained herein is, and remains
 *    the property of JingTong Company and its suppliers,
 *    if any.
 */

package com.jtech.toa.user.constants;


import com.jtech.marble.error.ErrorCode;

/**
 * <p>用户错误码.</p>
 *
 * @author tao.ding
 * @version 1.0
 * @since JDK 1.7
 */

public enum UserCode implements ErrorCode {
    /**
     * 登录请求参数用户名称不能为空
     */
    LOGIN_PARAM_USER_NAME(10001),
    /**
     * 登录请求参数用户密码不能为空
     */
    LOGIN_PARAM_PASSWORD(10002),
    /**
     * 用户无法找到
     */
    USER_NOT_FOUND(10003),
    /**
     * 新建用户时用户名已存在
     */
    USER_ALREADY_EXIST(10005),
    /**
     * 用户更新失败
     */
    USER_UPDATE_ERROR(10006),
    /**
     * 用户保存失败
     */
    USER_INSERT_ERROR(10007),
    /**
     * 删除用户参数id不能为空或0
     */
    REMOVE_EMPLOYEE_ID(10008),
    /**
     * 删除用户失败
     */
    REMOVE_EMPLOYEE_ERROR(10009),
    /**
     * 员工在部门中无法删除
     */
    EMPLOYEE_IN_ORGANIZATION(10010),
    /**
     * 移动部门失败
     */
    MOVE_EMPLOYEE_ERROR(10011),
    /**
     * 增加职位失败
     */
    ADD_POSITION_ERROR(10012),
    /**
     * 更新职位
     */
    UPDATE_POSITION_ERROR(10013),
    /**
     * 职位id参数校验不通过
     */
    PARAM_POSITION_ID_EMPTY(10014),
    /**
     * 通过id获取到职位信息为空
     */
    FIND_POSITION_ERROR(10015),
    /**
     * 部门职位id参数校验不通过
     */
    PARAM_ORGANIZATION_POSITION_ID_EMPTY(10016),
    /**
     * 删除部门职位失败
     */
    REMOVE_ORGANIZATION_POSITION_ERROR(10017),
    /**
     * 部门id参数校验不通过
     */
    PARAM_ORGANIZATION_ID_EMPTY(10018),
    /**
     * 部门职位编制人员不能为空
     */
    PARAM_ORGANIZATION_POSITION_BOOK_SIZE_EMPTY(10019),
    /**
     * 添加职位信息失败
     */
    ADD_ORGANIZATION_POSITION_ERROR(10020),
    /**
     * 更新职位信息失败
     */
    UPDATE_ORGANIZATION_POSITION_ERROR(10021),
    /**
     * 职位编号已存在
     */
    POSITION_CODE_IS_EXIST(10022),
    /**
     * 职位编号已存在
     */
    USE_POSITION_ERROR(10023),
    /**
     * 职位编号已存在
     */
    STOP_POSITION_ERROR(10024),
    /**
     * 职位编号已存在
     */
    DELETE_POSITION_ERROR(10025),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(10026),
    /**
     * 用户不存在
     */
    USER_NOT_EXITS(10027),
    /**
     * 用户不能为空
     */
    USER_NAME_IS_SPACE(10028),
    /**
     * 密码不能为空
     */
    PWD_IS_SPACE(10029),
    /**
     * 未登录
     */
    UNAUTHORIZED(10030),
    /**
     * 验证码不能为空
     */
    CAPTCHA_NOT_BLACK(10032),
    /**
     * 两次密码不正确
     */
    REPWD_ERROR(10033),
    /**
     * 验证码不正确
     */
    CAPTCHA_ERROR(10034),
    /**
     * 找回密码失败
     */
    REST_ERROR(10035),
    /**
     * 新增组织失败
     */
    ADD_FAIL(10036),
    /**
     * 更新组织失败
     */
    UPDATE_FAIL(10037),
    /**
     * 存在子组织无法删除
     */
    EXIT_CHRILDREN_CAN_NOT_DEL(10038),
    /**
     * 组织存在员工无法删除
     */
    EXIT_EMPLOYEE_CAN_NOT_DEL(10039),
    /**
     * 组织删除失败
     */
    DELETE_ORGANIZATION_ERROR(10040),
    /**
     * 组织添加员工失败
     */
    ADD_EMPLOYEE_FAIL(10041),
    /**
     * 组织移除员工失败
     */
    REMOVE_EMPLOYEE_FAIL(10042),
    /**
     * 组织移除员工失败
     */
    JSON_ERROR(10043),
    /**
     * 不包含员工
     */
    NO_EXIT_EMPLOYEE(10044),
    /**
     * 组织id不存在
     */
    CHECK_ORGNAZITION_ID(10045),
    /**
     * 修改组织状态失败
     */
    UPDATE_STATUS_ERROR(10046),
    /**
     * 组织编码已存在
     */
    CODE_IS_EXIST(10047),
    /**
     * 员工已在其他组织中
     */
    EMPLOYEE_IN_OTHERORG(10048),
    /**
     * 未找到部门
     */
    ORGAZITION_LIKE_NAME_IS_EMPTY(10049),
    /**
     * 未找到职位
     */
    POSITION_LIKE_NAME_IS_EMPTY(10050),
    /**
     * 工号已存在
     */
    EMPLOYEE_WORKNO_ALREADY_EXIST(10051),
    /**
     * 部门名已存在
     */
    ORGAZITION_ALREADY_EXIST(10052),
    /**
     * 职位名称已存在
     */
    POSITION_ALREADY_EXIST(10053),
    /**
     * 员工手机号已存在
     */
    EMPLOYEE_MOBILE_ALREADY_EXIST(10054),
    /**
     * 员工身份证已存在
     */
    EMPLOYEE_IDCARD_ALREADY_EXIST(10055),
    /**
     * 员工账号已存在
     */
    EMPLOYEE_ACCCOUNT_ALREADY_EXIST(10056),
    /**
     * 职级信息参数id不能为空或0
     */
    CHECK_EMPLOYEE_RANK_ID(10057),
    /**
     * 删除职级信息失败
     */
    REMOVE_EMPLOYEE_RANK_ERROR(10058),
    /**
     * 职级名称已存在
     */
    EMPLOYEE_RANK_ALREADY_EXIST(10059),
    /**
     * 职级新增失败
     */
    EMPLOYEE_RANK_INSERT_ERROR(10060),
    /**
     * 职级更新失败
     */
    EMPLOYEE_RANK_UPDATE_ERROR(10061),
    /**
     * 职位被使用，无法删除
     */
    POSITION_IS_USED_CAN_NOT_DELETE(10062),
    /**
     * 添加职位信息失败,部门已存在该职位
     */
    ORGANIZATION_POSITION_IS_EXISTS(10063),
    /**
     * 停用部门失败,存在未停用的子部门
     */
    ORGANIZATION_HAS_USING_CHILDREN(10064);

    private final int code;

    /**
     * 错误码
     *
     * @param code code
     */
    UserCode(final int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
