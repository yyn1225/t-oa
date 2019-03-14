/*
 * Copyright © 2015-2016, AnHui Mobiao technology co. LTD Inc. All Rights Reserved.
 */

package com.jtech.toa.auth.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在Controller的方法参数中使用此注解，该方法在映射时会注入当前登录的用户模型
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestUser {
}
