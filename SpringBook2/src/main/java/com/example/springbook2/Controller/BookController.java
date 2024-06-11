package com.example.springbook2.Controller;

import com.example.springbook2.Enums.BookStatus;
import com.example.springbook2.Enums.ResultStatus;
import com.example.springbook2.Made.*;
import com.example.springbook2.Mapper.BookInfoMapper;
import com.example.springbook2.Service.BookService;
import com.example.springbook2.constant.Constants;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Autowired
    private BookService bookService;
    @RequestMapping("/getBookListByPage")
    public Result<PageResult<BookInfo>> getBookListByPage(PageRequest pageRequest, HttpSession session){
        PageResult<BookInfo>bookList=bookService.getBookList(pageRequest);
        return Result.success(bookList);
    }
    @RequestMapping("/queryBookById")
    public Result<BookInfo> queryBookById(Integer bookId){
        log.info("根据ID查询图书信息, id:"+bookId);
        long start = System.currentTimeMillis();
        BookInfo bookInfo = bookService.queryBookinfoById(bookId);
        log.info("queryBookById 耗时: "+ (System.currentTimeMillis()-start) + "ms");
        Result<BookInfo>bookInfoResult=new Result<>();
        bookInfoResult.setData(bookInfo);
        bookInfoResult.setCode(ResultStatus.SUCCESS);
        return bookInfoResult;
    }
    /**
     * 更新图书
     */
    @RequestMapping(value = "/updateBook", produces = "application/json")
    public Result<String> updateBook(BookInfo bookInfo,HttpSession session){
        Result<String>stringResult=new Result<>();
        log.info("更新图书, bookInfo: {}", bookInfo);
        UserInfo userInfo=(UserInfo) session.getAttribute(Constants.USER_SESSION_KEY);
        if(userInfo==null){
            String ans= "未登录";
            stringResult.setCode(ResultStatus.FAIL);
            stringResult.setData(ans);
        }
        try{
            Integer result = bookService.updateBook(bookInfo);
            if (result>0){
                String ans="";
                stringResult.setCode(ResultStatus.SUCCESS);
                stringResult.setData(ans);
                return stringResult;
            }
            String ans= "内部错误, 请联系管理员";
            stringResult.setCode(ResultStatus.FAIL);
            stringResult.setData(ans);
        }catch (Exception e){
            log.error("更新图书失败, e:", e);
            String ans= "内部错误, 请联系管理员";
            stringResult.setCode(ResultStatus.FAIL);
            stringResult.setData(ans);
        }
        return stringResult;
    }
    @RequestMapping(value = "/addBook",produces = "application/json")
    public Result<String> addBook(BookInfo bookInfo){
        Result<String>ans=new Result<>();
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount()==null
                || bookInfo.getPrice()==null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus()==null){
            ans.setData("输入错误");
            ans.setCode(ResultStatus.FAIL);
            return ans;
        }
        try{
            Integer result = bookService.addBood(bookInfo);
            if (result >0){
                ans.setData("");
                ans.setCode(ResultStatus.SUCCESS);
                return ans;
            }
        }catch (Exception e){
            log.error("添加图书异常, e: ", e);
        }
        ans.setData("添加失败");
        ans.setCode(ResultStatus.FAIL);
        return ans;
    }
    @RequestMapping("/deleteBook")
    public Result<Boolean> deleteBook(Integer bookId){
        Result<Boolean>ansResult=new Result<>();
        boolean ans=false;
        Integer n=bookService.deleteBook(bookId);
        if(n>0){
            ans=true;
            ansResult.setCode(ResultStatus.SUCCESS);
            ansResult.setData(ans);
            return ansResult;
        }
        else{
            ansResult.setCode(ResultStatus.FAIL);
            ansResult.setData(ans);
            return ansResult;
        }
    }
    @RequestMapping("batchDeleteBook")
    public Result<String>batchDeleteBook(@RequestParam List<Integer>ids){
        Integer n=bookService.batchDeleteBook(ids);
        Result<String> ans=new Result<>();
        if(n==ids.size()){
            String ansdata="";
            ans.setData(ansdata);
            ans.setCode(ResultStatus.SUCCESS);
            return ans;
        }else if(n<ids.size()){
            String ansdata="删除错误未能全部删除";
            ans.setData(ansdata);
            ans.setCode(ResultStatus.FAIL);
            return ans;
        }
        ans.setData("删除错误");
        ans.setCode(ResultStatus.FAIL);
        return ans;
    }
}
