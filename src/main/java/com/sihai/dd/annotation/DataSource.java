package com.sihai.dd.annotation;

import com.sihai.dd.datasource.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation : 注解接口
 */


// 在运行时去动态获取注解信息
@Retention(RetentionPolicy.RUNTIME)
// 注解的作用范围: 取值： 类和方法，通过 value 属性来指定类或者方法应该使用哪个数据源
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DataSource {

    // 如果方法加上 @DataSource 注解， 但未指定数据源名称， 则使用默认数据源master
    String value() default DataSourceType.DEFAULT_DS_NAME;
}
