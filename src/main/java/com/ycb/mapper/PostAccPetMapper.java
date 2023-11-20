package com.ycb.mapper;

import com.ycb.entity.vo.response.AllPetAndBulVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户发布宠物和布告相关操作
 */
@Mapper
public interface PostAccPetMapper {

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    List<AllPetAndBulVO> getPostPBById(Integer id);

    /**
     * 根据宠物id删除用户发布的宠物
     *
     * @param petId 宠物id
     */
    int updatePostPIsDeleteByPetId(Integer petId);

    /**
     * 根据布告id获取宠物id
     *
     * @param bulletinId 布告id
     * @return 宠物id
     */
    int getBulIdByPetId(Integer bulletinId);

    /**
     * 根据布告id删除用户发布的布告
     *
     * @param bId 布告id
     */
    int updatePostBIsDeleteByBulId(Integer bId);

    /**
     * 根据宠物id和用户id获取宠物
     *
     * @param bulletinId 布告id
     * @param accId      用户id
     * @return 宠物
     */
    int getBulByBulIdAndAccId(Integer bulletinId, Integer accId);
}
