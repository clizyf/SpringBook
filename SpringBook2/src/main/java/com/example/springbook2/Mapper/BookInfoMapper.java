package com.example.springbook2.Mapper;

import com.example.springbook2.Made.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookInfoMapper {
    Integer insertBook( BookInfo bookInfo);

    Integer count();
    @Select("select * from book_info where id=#{bookId}")
    BookInfo queryBookById(Integer bookId);
    @Select("select  * from book_info where status!=0 order by id asc limit #{offset},#{limit}")
    List<BookInfo>queryBookByPage(Integer offset,Integer limit);
    @Select("select * from book_info where id=#{bookid}")
    BookInfo queryBookinfoById(Integer bookid);

    Integer updateBook(BookInfo bookInfo);
    @Insert("insert into book_info (book_name,author,count,price,publish,`status`)values(#{bookName},#{author},#{count},#{price},#{publish},#{status})")
    Integer addBook(BookInfo bookInfo);

    Integer deleteBook(Integer bookId);
    Integer batchDeleteBook(List<Integer>ids);
}
