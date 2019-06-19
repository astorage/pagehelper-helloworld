package com.java.pagehelperhelloworld.convertor;

import com.java.pagehelperhelloworld.model.WishDO;
import com.java.pagehelperhelloworld.model.WishDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishConertor {

    WishDTO convertDO2DTO(WishDO wishDO);

    default List<WishDTO> convertDOList2DTOList(List<WishDO> wishDOList) {
        return wishDOList.stream().map(wishDO -> convertDO2DTO(wishDO)).collect(Collectors.toList());
    }
}
