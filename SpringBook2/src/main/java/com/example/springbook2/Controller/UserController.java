package com.example.springbook2.Controller;

import com.example.springbook2.Enums.ResultStatus;
import com.example.springbook2.Made.Result;
import com.example.springbook2.Made.UserInfo;
import com.example.springbook2.Made.sign_inUser;
import com.example.springbook2.Made.verify;
import com.example.springbook2.Service.UserService;
import com.example.springbook2.constant.Constants;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login",produces = "application/json")
    public Result login(String userName, String password, HttpSession session){
        if(!StringUtils.hasLength(userName)||!StringUtils.hasLength(password)){
            return Result.Fail("用户密码或者账号为空");
        }
        UserInfo userInfo1=new UserInfo();
        userInfo1.setUserName(userName);
        userInfo1.setPassword(password);
        UserInfo userInfo=userService.login(userInfo1);
        if(userInfo==null){
            return Result.Fail("用户账号或者密码不正确");
        }
        if(!userInfo.getPassword().equals(password)){
            return Result.Fail("用户名或者密码不正确");
        }
        session.setAttribute(Constants.USER_SESSION_KEY,userInfo);
        return Result.success("");
    }
    @RequestMapping("/verify")
    public Result<String> verify(@RequestBody verify verify1) throws EmailException {
            sign_inUser sign=new sign_inUser();
            sign.setUsername(verify1.getUsername());
            sign.setEmail(verify1.getEmail());
            sign.setPassword(verify1.getPassword());
            userService.sendemail(sign);
            Result<String> result=new Result<>();
            result.setCode(ResultStatus.SUCCESS);
            result.setData("");
            return result;
    }
    @RequestMapping("/sign_in")
    public Result<String> sign_in(@RequestBody sign_inUser signInUser) throws EmailException {
        boolean flag=userService.sign_Inuser(signInUser);
        Result<String>ans=new Result<>();
        if(signInUser.captcha==null){
            userService.sendemail(signInUser);
            ans.setCode(ResultStatus.SUCCESS);
            return ans;
        }
        if(flag==true){
            ans.setData("");
            ans.setCode(ResultStatus.SUCCESS);
            return ans;
        }else {
            ans.setData("");
            ans.setCode(ResultStatus.FAIL);
            return ans;
        }
    }
}
