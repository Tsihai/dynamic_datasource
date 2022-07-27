package com.sihai.dd.service;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.mapper.SlaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaveService {

    @Autowired
    SlaveMapper slaveMapper;

    @DataSource("slave")
    public void updateUserAge(String username, Integer age) {
        slaveMapper.updateUserAge(username, age);
    }
}
