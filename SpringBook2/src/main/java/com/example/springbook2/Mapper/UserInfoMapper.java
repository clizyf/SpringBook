package com.example.springbook2.Mapper;

import com.example.springbook2.Made.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    UserInfo SelectByName(String username);
    @Insert("INSERT into user_info (user_name,`password`)VALUES(#{userName},#{password})")
    Integer insertUsername(UserInfo userInfo);
}
