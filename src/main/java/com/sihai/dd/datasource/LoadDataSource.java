package com.sihai.dd.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 注册到spring容器中
@Component
@EnableConfigurationProperties(DruidProperties.class)
public class LoadDataSource {

    @Autowired
    DruidProperties druidProperties;

    public Map<String, DataSourceProxy> loadAllDataSource() {
        Map<String, DataSourceProxy> map = new HashMap<>();
        Map<String, Map<String, String>> ds = druidProperties.getDs();
        try {
            // 将Map中所有的键存入到set集合 keySet 中
            Set<String> keySet = ds.keySet();
            for (String key : keySet) {
                // 获取map集合中的 key , 创建数据源
                DataSource dataSource = druidProperties.dataSource((DruidDataSource) DruidDataSourceFactory.createDataSource(ds.get(key)));
                map.put(key, new DataSourceProxy(dataSource));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
