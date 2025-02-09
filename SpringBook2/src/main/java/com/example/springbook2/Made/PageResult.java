package com.example.springbook2.Made;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    private List<T> records;
    private Integer count;
    private PageRequest pageRequest;
}
