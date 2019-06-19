package com.java.pagehelperhelloworld.model;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class Page implements Pageable {

    private Integer pageNum;

    private Integer pageSize;

    private Sort sort;

    public Page(int pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Page() {
        pageNum = 0;
        pageSize = 10;
    }

    @Override
    public int getPageNumber() {
        return pageNum;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return (pageNum - 1) * pageSize;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        int nextPage = pageNum + 1;
        return new Page(nextPage, nextPage);
    }

    @Override
    public Pageable previousOrFirst() {
        int previousPage = pageNum - 1;
        previousPage = previousPage < 0 ? 0 : previousPage;
        return new Page(previousPage, pageSize);
    }

    @Override
    public Pageable first() {
        return new Page(1, pageSize);
    }

    @Override
    public boolean hasPrevious() {
        return pageNum - 1 > 0;
    }
}
