package com.java.pagehelperhelloworld.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 愿望DTO
 */
@Data
public class WishDTO implements Serializable {
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
