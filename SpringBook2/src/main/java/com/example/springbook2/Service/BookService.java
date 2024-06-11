package com.example.springbook2.Service;

import com.example.springbook2.Enums.BookStatus;
import com.example.springbook2.Made.BookInfo;
import com.example.springbook2.Made.PageRequest;
import com.example.springbook2.Made.PageResult;
import com.example.springbook2.Mapper.BookInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookInfoMapper bookInfoMapper;
    public PageResult<BookInfo> getBookList(PageRequest pageRequest){
        //1. 获取总记录数
        Integer count = bookInfoMapper.count();
        //2. 获取当前页的记录
        List<BookInfo>bookInfos=bookInfoMapper.queryBookByPage(pageRequest.getOffset(),pageRequest.getPageSize());
        for (BookInfo bookinfo:bookInfos) {
            bookinfo.setStatusCN(BookStatus.getDescBycode(bookinfo.getStatus()).getDesc());
        }
        return new PageResult<>(bookInfos,count,pageRequest);
    }
    public BookInfo queryBookinfoById(Integer bookId){
        BookInfo bookInfo=  bookInfoMapper.queryBookById(bookId);
        bookInfo.setStatusCN(BookStatus.getDescBycode(bookInfo.getStatus()).getDesc());
        return bookInfo;
    }
    public Integer updateBook(BookInfo bookInfo){
        Integer n=bookInfoMapper.updateBook(bookInfo);
        return n;
    }

    public Integer addBood(BookInfo bookInfo){
        return bookInfoMapper.addBook(bookInfo);
    }
    public Integer deleteBook(Integer bookId){
        BookInfo bookInfo=new BookInfo();
        bookInfo.setId(bookId);
        bookInfo.setStatus(0);
        return  bookInfoMapper.updateBook(bookInfo);
    }
    public Integer batchDeleteBook(List<Integer>ids){
        return bookInfoMapper.batchDeleteBook(ids);
    }
}
