package com.ycb.service;

import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.response.AllPetAndBulVO;

import java.util.List;

/**
 * 用户收藏宠物相关操作
 */
public interface Acc2PicService {

    /**
     * 收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     * @return 收藏结果
     */
    String collectPB(CollectAccPet collectAccPet);

    /**
     * 取消收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     * @return 取消收藏结果
     */

    String cancelCollectPB(CollectAccPet collectAccPet);

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    List<AllPetAndBulVO> getPostPBById(Integer id);

    /**
     * 根据宠物id删除用户发布的宠物和布告
     * @param petId 宠物id
     * @return 删除结果
     */
    String updatePostPBIsDeleteByPetId(Integer petId);

    /**
     * 获取用户收藏的宠物和布告
     * @param id 用户id
     * @return 用户收藏的宠物和布告
     */
    List<AllPetAndBulVO> getCollectPBById(Integer id);
}
