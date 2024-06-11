package com.example.springbook2.Service;

import com.example.springbook2.Made.UserInfo;
import com.example.springbook2.Made.sign_inUser;
import com.example.springbook2.Mapper.UserInfoMapper;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

@Service
public class UserService {
    public HashMap<String,String> hashMap=new HashMap<>();
    @Autowired
    UserInfoMapper userInfoMapper;
    public UserInfo login(UserInfo userInfo){
      UserInfo userInfo1=userInfoMapper.SelectByName(userInfo.getUserName());
      return userInfo1;
    }
    public  String Random_number(){
        // 种子数都设为6
        Random numList = new Random();
        StringBuilder random=new StringBuilder();
// 随机生成4个随机数并且将其拼接为字符串
        for(int i=0; i<4; i++) {
            //生成一个[0,10)之间的随机数
            int num =  numList.nextInt(10) ;
            random.append(String.valueOf(num));
        }
        return new String(random);
    }
    public  boolean sendemail(sign_inUser signInUser) throws EmailException {//发送邮箱验证码
        String rootemail="3531786966@qq.com";
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName("smtp.qq.com");
        htmlEmail.setSSLOnConnect(true);
        htmlEmail.setCharset("utf-8");//设置发送的字符类型
        htmlEmail.addTo(signInUser.email);//设置目标邮箱
        htmlEmail.setFrom(rootemail,"zyf的项目网站验证码");
        htmlEmail.setAuthentication(rootemail,"avwutqmshvoycjbi");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
        htmlEmail.setSubject("zyf发给"+signInUser.username+"的邮件希望您能接收到并且不要忽视");//设置发送主题
        String random=Random_number();//获取随机验证码
        htmlEmail.setMsg(random);//设置发送内容
        System.out.println("发送过程可能会有些缓慢请耐心等待");
        htmlEmail.send();//进行发送
        hashMap.put(signInUser.email,random);
        return true;
    }
    public boolean sign_Inuser(sign_inUser signInUser)  {
        String random=hashMap.get(signInUser.email);
        if(random==null||!random.equals(signInUser.captcha)){
            return false;
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(signInUser.username);
        userInfo.setPassword(signInUser.getPassword());
        Integer n= userInfoMapper.insertUsername(userInfo);
        if(n<=0){
            return false;
        }
        return true;
    }
}
