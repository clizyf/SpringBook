package com.example.springbook2.Made;

import com.example.springbook2.Enums.ResultStatus;
import lombok.Data;

@Data
public class Result <T>{
    private ResultStatus code;
    private String errMsg;
    private T data;
    public static <T>Result success(T data){
        Result result=new Result<>();
        result.setCode(ResultStatus.SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result Fail(ResultStatus resultStatus,String msg){
            Result result=new Result();
            result.setErrMsg(msg);
            result.setCode(resultStatus);
            return result;
    }
    public static  Result Fail(String msg){
        Result result=new Result<>();
        result.setCode(ResultStatus.FAIL);
        result.setErrMsg(msg);
        return result;
    }
    public static Result nologin(){
        Result result=new Result<>();
        result.setErrMsg("用户未登录");
        result.setCode(ResultStatus.NOLOGIN);
        return result;
    }
}
