package com.yichuangzhihui.robotvrp.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @ClassName 自定义权限注解  使用此注解验证token
 * @Author qbz
 * @Date 2020/4/30
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
    int value() default 0;
}
