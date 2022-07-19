package com.sihai.dd.controller;

import com.sihai.dd.datasource.DataSourceType;
import com.sihai.dd.model.User;
import com.sihai.dd.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController     // 返回json字符串
public class DataSourceController {

    // 打印日志
    private static final Logger logger = LoggerFactory.getLogger(DataSourceController.class);

    @Autowired
    UserService userService;

    /**
     * 修改数据源
     */
    @PostMapping("/dstype")
    public void setDsType(String dsType, HttpSession session) {
        // 将数据源的信息存到session中
        session.setAttribute(DataSourceType.DS_SESSION_KEY, dsType);
        logger.info("修改数据源为：{}", dsType);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
