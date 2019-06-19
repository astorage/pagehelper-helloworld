package com.java.pagehelperhelloworld.model;

import lombok.Data;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = 8656597559014685635L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 第几页
     */
    private int pageNum;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
     */
    private int size;



    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            total = page.getTotal();
            this.list = page.getResult();
            pageNum = page.getPageNum();
            pageSize = page.getPageSize();
            pages = page.getPages();
            size = page.size();
        }
    }

    public PageInfo() {
    }
}
