package com.sihai.dd.aspect;

import com.sihai.dd.datasource.DataSourceType;
import com.sihai.dd.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

//@Aspect
//@Component
// order数值小的优先执行
//@Order(10)
public class GloalDataSourceAspect {

    @Autowired
    HttpSession session;

    @Pointcut("execution(* com.sihai.dd.service.*.*(..))")
    public void pc() {
    }

    @Around("pc()")
    public Object around(ProceedingJoinPoint pjp){
        // 从session中把数据源的名称读出 存到 DynamicDataSourceContextHolder 中
        DynamicDataSourceContextHolder.setDataSourceType((String) session.getAttribute(DataSourceType.DS_SESSION_KEY));
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            // 在命令行打印异常信息在程序中出错的位置及原因。
            throwable.printStackTrace();
        }finally {
            // 清除 DynamicDataSourceContextHolder 中的数据源名称
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
        return null;
    }
}
