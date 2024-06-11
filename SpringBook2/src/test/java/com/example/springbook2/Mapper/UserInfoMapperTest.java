package com.example.springbook2.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Test
    void selectByName() {
        System.out.println(userInfoMapper.SelectByName("zhangsan"));
    }
}