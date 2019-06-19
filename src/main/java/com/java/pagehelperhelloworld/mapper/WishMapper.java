package com.java.pagehelperhelloworld.mapper;

import com.java.pagehelperhelloworld.model.WishDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishMapper {
    /**
     * 查询最新的num条愿望
     * @param num 数量
     * @return
     */
    List<WishDO> queryLatestWish(@Param("num") Integer num);

    /**
     * 查询最新的num条愿望
     * @return
     */
    List<WishDO> pageQueryWish();

    /**
     * 根据许愿人查询愿望
     * @param user 许愿人
     * @return
     */
    List<WishDO> queryWishByUser(@Param("user") String user);

    /**
     * 新增愿望
     * @param wishDO 愿望对象
     * @return
     */
    int addWish(WishDO wishDO);

}
