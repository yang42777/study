package com.example.ecommerce.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private long total;       // 总记录数
    private List<T> records;  // 当前页记录

    public PageResult() {}

    public PageResult(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    // 静态构造方法（更方便）
    public static <T> PageResult<T> of(long total, List<T> records) {
        return new PageResult<>(total, records);
    }

    // 静态构造方法（更方便）
    public static <T> PageResult<T> of(PageInfo<T> pageInfo) {
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
