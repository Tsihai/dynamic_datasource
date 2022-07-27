package com.sihai.dd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SlaveMapper {

    @Update("update user set age=#{age} WHERE username=#{username}")
    int updateUserAge(@Param("username") String username, @Param("age") Integer age);

}
