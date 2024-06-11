package com.example.springbook2.Made;

import lombok.Data;

@Data
public class PageRequest {
    private Integer pageNum=1;
    private Integer pageSize=10;
    private Integer offSet;
    public Integer getOffset() {
        return (pageNum-1) * pageSize;
    }
}
