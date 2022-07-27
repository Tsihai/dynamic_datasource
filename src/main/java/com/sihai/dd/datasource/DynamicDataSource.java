package com.sihai.dd.datasource;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {


    public DynamicDataSource(LoadDataSource loadDataSource) {
        // 1.设置所有数据源
        Map<String, DataSourceProxy> allDs = loadDataSource.loadAllDataSource();
        super.setTargetDataSources(new HashMap<>(allDs));
        // 2.设置默认数据源, 对于没有 @DataSource 注解的方法，全部使用默认数据源
        super.setDefaultTargetDataSource(allDs.get(DataSourceType.DEFAULT_DS_NAME));
        // 3.设置当前数据源
        super.afterPropertiesSet();
    }

    /**
     * 用来返回数据源名称，当系统需要获取数据源的时候， 会自动调用该方法获取数据源的名称
     * determineCurrentLookupKey 确定当前查找键
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
