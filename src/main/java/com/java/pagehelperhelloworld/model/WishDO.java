package com.java.pagehelperhelloworld.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WishDO {

    private Long id;
    /**
     * 愿望编号
     */
    private String wishNo;
    /**
     * 许愿时间
     */
    private LocalDateTime wishTime;
    /**
     * 愿望内容
     */
    private String wishContent;
    /**
     * 许愿人
     */
    private String user;
}
