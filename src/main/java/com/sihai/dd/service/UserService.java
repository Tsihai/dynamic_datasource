package com.sihai.dd.service;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.mapper.UserMapper;
import com.sihai.dd.model.User;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    MasterService masterService;

    @Autowired
    SlaveService slaveService;

    // 全局事務
    @GlobalTransactional(name = "dynamic-datasourece")
    // @Transactional
    public void test(){
        masterService.updateUserAge("sihaitest09", 100);
        slaveService.updateUserAge("sihai", 102);
    }

    /**
     * 查询所有用户
     *
     * @Transactional 事务
     * @return
     */
    //@Transactional

    // 全局事務
    // @GlobalTransactional
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
