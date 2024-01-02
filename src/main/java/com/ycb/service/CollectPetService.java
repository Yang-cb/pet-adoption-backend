package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PageCollectPetDTO;

/**
 * 收藏宠物和布告的服务接口
 */
public interface CollectPetService {

    /**
     * 收藏/取消收藏宠物
     *
     * @param vo 用户id和宠物id
     */
    void collectPB(AccIdPetIdDTO vo);

    /**
     * 获取用户收藏的宠物和布告
     *
     * @param dto 分页信息
     * @return 用户收藏的宠物和布告
     */
    PageResult getAllCollect(PageCollectPetDTO dto);

    /**
     * 根据宠物id、宠物id判断用户是否收藏该宠物
     *
     * @param vo 用户id、宠物id
     * @return 是否收藏
     */
    Boolean isCollect(AccIdPetIdDTO vo);
}
