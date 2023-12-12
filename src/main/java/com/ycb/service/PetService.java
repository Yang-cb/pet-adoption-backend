package com.ycb.service;

import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.pojo.vo.OnePetBulletinVO;

import java.util.List;

/**
 * 获取宠物布告相关操作的服务接口
 */
public interface PetService {
    /**
     * 获取所有宠物
     *
     * @return 宠物列表
     */
    List<AllPetBulletinVO> getAll();

    /**
     * 根据宠物类型获取所有宠物
     *
     * @param type 宠物类型
     * @return 宠物列表
     */
    List<Pet> getAllByType(String type);

    /**
     * 根据宠物id获取宠物信息
     * @param petId 宠物id
     * @return 宠物信息
     */
    OnePetBulletinVO getPBByPetId(Integer petId);
}
