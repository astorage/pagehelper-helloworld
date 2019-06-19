package com.java.pagehelperhelloworld.util;

import org.springframework.data.domain.Sort;

import java.util.Iterator;

public class PageHelperUtils {

    public static String getOrderBy(Sort sort) {
        if(sort.isUnsorted()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator ite = sort.iterator();
        while (ite.hasNext()) {
            Sort.Order order = (Sort.Order) ite.next();
            sb.append(" " + order.getProperty() + " " + order.getDirection().name() + ",");
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
