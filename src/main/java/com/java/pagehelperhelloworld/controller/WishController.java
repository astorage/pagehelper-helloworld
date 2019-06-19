package com.java.pagehelperhelloworld.controller;

import com.java.pagehelperhelloworld.model.*;
import com.java.pagehelperhelloworld.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/creative/life/auth/wish")
public class WishController {

    @Autowired
    private WishService wishService;

    @GetMapping("/wish_well")
    public SingleResponse<List<WishDTO>> queryWishWell() {
        log.info("/creative/wish/wish_well 开始");
        SingleResponse<List<WishDTO>> singleResponse = wishService.queryWishWell();
        log.info("/creative/wish/wish_well 成功");
        return singleResponse;
    }


    @GetMapping("/my_wishes")
    public SingleResponse<List<WishDTO>> queryMyWishes() {
        log.info("/creative/wish/my_wishes 开始");
        SingleResponse<List<WishDTO>> singleResponse = wishService.queryMyWishes();
        log.info("/creative/wish/my_wishes 成功");
        return singleResponse;
    }

    @PostMapping("/wishing")
    public Response wishing(@RequestBody @Validated WishingCommand wishingCommand) {
        log.info("/creative/wish/wishing 开始");
        Response response = wishService.wishing(wishingCommand);
        log.info("/creative/wish/wishing 成功");
        return response;
    }

    @GetMapping("/testPage")
    public SingleResponse<PageInfo<WishDTO>> testPage(@RequestBody Page page) {
        log.info("/creative/zodiac/testPage");
        SingleResponse<PageInfo<WishDTO>> singleResponse = wishService.pageQueryWishWell(page);
        log.info("/creative/zodiac/testPage 完成!");
        return singleResponse;
    }

}
