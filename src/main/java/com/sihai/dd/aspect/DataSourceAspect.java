package com.sihai.dd.aspect;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.sihai.dd.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import com.sihai.dd.annotation.DataSource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * aspect: 切面
 * DataSourceAspect: 数据源切面
 *
 * 自定义切面，解析@DataSource注解，当如果某一个方法或者类上面有 @DataSource 注解，将注释所标记的数据源存入到ThreadLocal中
 */

/**
 * 实现bean的注入
 * 把当前类标识为一个切面
 */
@Component
@Aspect
@Order(11)
public class DataSourceAspect {

    /**
     * @Pointcut: 切入点
     * @annotation(com.sihai.dd.annotation.DataSource) 方法上有 @DataSource 注解就将方法拦截下来
     * @within(com.sihai.dd.annotation.DataSource) 类上有 @DataSource 注解就将类中的方法拦截下来
     */
    @Pointcut("@annotation(com.sihai.dd.annotation.DataSource) || @within(com.sihai.dd.annotation.DataSource)")
    public void pc() {

    }

    /**
     *  @Around: 环绕通知   属性：value 切入点表达式
     * @param pjp
     * @return
     */
    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp) {
        // 获取方法上面的有效注解
        DataSource dataSource = getDataSource(pjp);
        if (dataSource != null) {
            // 获取注解上的数据源名称
            String value = dataSource.value();
            // 设置当前线程的数据源
            DynamicDataSourceContextHolder.setDataSourceType(value);
        }
        try {
            // 执行方法
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            // 清除数据源
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return null;
    }

    /**
     * 获取数据源
     * @param pjp
     * @return
     */
    private DataSource getDataSource(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        // 查找方法上的注解
        DataSource aunotation = AnnotationUtils.findAnnotation(signature.getMethod(), DataSource.class);
        // 判断方法上面是否有 @DataSource 注解
        if (aunotation != null) {
            return aunotation;
        }
        // 返回获取所在类上面的 @DataSource 注解
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), DataSource.class);
    }

}
