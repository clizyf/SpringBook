package com.example.springbook2.Made;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private BigDecimal price;
    private String publish;
    private Integer status; //1-正常   2-不可借阅
    private String statusCN;
    private Date createTime;
    private Date updateTime;
}
