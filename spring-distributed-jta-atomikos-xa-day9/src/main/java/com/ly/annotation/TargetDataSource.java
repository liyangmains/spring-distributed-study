package com.ly.annotation;

import java.lang.annotation.*;

/**
 * @Author Mr.Lz
 * @Date 2018-07-29
 * @Description 作用于类、接口或者方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}
