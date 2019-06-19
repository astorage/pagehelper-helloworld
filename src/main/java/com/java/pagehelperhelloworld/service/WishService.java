package com.java.pagehelperhelloworld.service;

import com.java.pagehelperhelloworld.model.*;

import java.util.List;

/**
 * 愿望服务
 */
public interface WishService {
    /**
     * 获取许愿池
     *  查询最新的n条愿望数据
     * @return
     */
    SingleResponse<List<WishDTO>> queryWishWell();

    /**
     *  分页获取许愿池
     *  查询最新的n条愿望数据
     * @return
     */
    SingleResponse<PageInfo<WishDTO>> pageQueryWishWell(Page page);

    /**
     * 查询我的所有愿望
     * @return
     */
    SingleResponse<List<WishDTO>> queryMyWishes();

    /**
     * 许愿
     * @param wishingCommand 愿望内容
     * @return
     */
    Response wishing(WishingCommand wishingCommand);

}
