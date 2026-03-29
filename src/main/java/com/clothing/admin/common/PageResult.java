package com.clothing.admin.common;

import lombok.Data;

@Data
public class PageResult<T> {
    private Long total;
    private Long pages;
    private Long current;
    private Long size;
    private T records;

    public static <T> PageResult<T> of(Long total, Long pages, Long current, Long size, T records) {
        PageResult<T> result = new PageResult<>();
        result.setTotal(total);
        result.setPages(pages);
        result.setCurrent(current);
        result.setSize(size);
        result.setRecords(records);
        return result;
    }
}
