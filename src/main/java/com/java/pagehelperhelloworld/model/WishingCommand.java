package com.java.pagehelperhelloworld.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class WishingCommand{

    @NotNull(message = "愿望内容不能为空")
    @Length(min = 1 , max = 30, message = "愿望内容必须在1-30个字符")
    private String wishContent;

}
