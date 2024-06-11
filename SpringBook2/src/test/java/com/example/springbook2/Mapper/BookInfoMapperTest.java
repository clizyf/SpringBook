package com.example.springbook2.Mapper;

import com.example.springbook2.Made.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookInfoMapperTest {
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Test
    void insertBook() {
        BookInfo bookInfo=new BookInfo();
        bookInfo.setBookName("zyf");
        bookInfo.setPrice(new BigDecimal(520));
        bookInfo.setCount(10);
        bookInfo.setPublish("大爱出版社");
        bookInfo.setAuthor("cl");
        bookInfo.setStatus(1);
        bookInfoMapper.insertBook(bookInfo);

    }
}