package com.sihai.dd.service;

import com.sihai.dd.annotation.DataSource;
import com.sihai.dd.mapper.UserMapper;
import com.sihai.dd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
