package com.ycb.service;

import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.vo.AllPetBulletinVO;

import java.util.List;

/**
 * 收藏宠物和布告的服务接口
 */
public interface AccCollectBulService {

    /**
     * 收藏/取消收藏宠物
     *
     * @param vo 用户id和宠物id
     */
    void collectPB(AccIdPetIdDTO vo);

    /**
     * 获取用户收藏的宠物和布告
     * @param id 用户id
     * @return 用户收藏的宠物和布告
     */
    List<AllPetBulletinVO> getCollectPBById(Integer id);

    /**
     * 根据宠物id、宠物id判断用户是否收藏该宠物
     * @param vo 用户id、宠物id
     * @return 是否收藏
     */
    Boolean isCollect(AccIdPetIdDTO vo);
}
