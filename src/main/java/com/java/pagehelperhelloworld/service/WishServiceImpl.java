package com.java.pagehelperhelloworld.service;

import com.github.pagehelper.PageHelper;
import com.java.pagehelperhelloworld.convertor.WishConertor;
import com.java.pagehelperhelloworld.mapper.WishMapper;
import com.java.pagehelperhelloworld.model.*;
import com.java.pagehelperhelloworld.util.PageHelperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishServiceImpl implements WishService {

    private static final String WISH_NO_PREFIX = "wn";
    @Autowired
    private WishMapper wishMapper;

    @Value("${wish_well_num}")
    private Integer wishWellNum;
    @Autowired
    private WishConertor wishConertor;

    @Override
    public SingleResponse<List<WishDTO>> queryWishWell() {
        List<WishDO> wishDOList = wishMapper.queryLatestWish(wishWellNum);

        List<WishDTO> wishDTOList = wishConertor.convertDOList2DTOList(wishDOList);
        SingleResponse singleResponse = SingleResponse.of(wishDTOList);
        return singleResponse;
    }

    @Override
    public SingleResponse<PageInfo<WishDTO>> pageQueryWishWell(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        if (page.getSort() != null) {
            PageHelper.orderBy(PageHelperUtils.getOrderBy(page.getSort()));
        }
        List<WishDO> wishDOList = wishMapper.pageQueryWish();
        PageInfo<WishDO> doPageInfo = new PageInfo<>(wishDOList);
        PageInfo<WishDTO> dtoPage = convertDTOPageInfo(doPageInfo);
        return SingleResponse.of(dtoPage);
    }

    private PageInfo<WishDTO> convertDTOPageInfo(PageInfo<WishDO> doPageInfo) {
        PageInfo<WishDTO> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(doPageInfo, pageInfo);
        List<WishDTO> wishDTOList = doPageInfo.getList().stream().map(wishDO -> wishConertor.convertDO2DTO(wishDO))
                .collect(Collectors.toList());
        pageInfo.setList(wishDTOList);
        return pageInfo;
    }

    @Override
    public SingleResponse<List<WishDTO>> queryMyWishes() {
        String currentUser = "currentUser";
        List<WishDO> wishDOList = wishMapper.queryWishByUser(currentUser);
        List<WishDTO> wishDTOList = wishConertor.convertDOList2DTOList(wishDOList);
        SingleResponse singleResponse = SingleResponse.of(wishDTOList);
        return singleResponse;
    }

    @Override
    public Response wishing(WishingCommand wishingCommand) {
        WishDO wishDO = new WishDO();
        String currentUser = "currentUser";
        wishDO.setUser(currentUser);
        wishDO.setWishContent(wishingCommand.getWishContent());
        wishDO.setWishTime(LocalDateTime.now());
        String wishNo = WISH_NO_PREFIX + "sdfdfdfdfdfsfdsf";
        wishDO.setWishNo(wishNo);
        int num = wishMapper.addWish(wishDO);
        if (num != 1){
            Response.buildFailure("errorCode", "errMessage");
        }
        return Response.buildSuccess();
    }


}
