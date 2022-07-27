package com.sihai.dd.datasource;

/**
 * 储存当前线程所使用的数据源名称
 */
public class DynamicDataSourceContextHolder {
    private static ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    // 设置数据源类型
    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.set(dsType);
    }

    // 获取数据源类型
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    // 清除数据源类型
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
