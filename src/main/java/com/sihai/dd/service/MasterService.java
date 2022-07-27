package com.sihai.dd.service;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.mapper.MasterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

    @Autowired
    MasterMapper masterMapper;

    @DataSource("master")
    public void updateUserAge(String username, Integer age) {
        masterMapper.updateUserAge(username, age);
    }


}
