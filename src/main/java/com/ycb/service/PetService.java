package com.ycb.service;

import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.response.PetAndBulVO;

import java.util.List;

public interface PetService {
    /**
     * 获取所有宠物
     *
     * @return 宠物列表
     */
    List<PetAndBulVO> getAll();

    /**
     * 根据宠物类型获取所有宠物
     *
     * @param type 宠物类型
     * @return 宠物列表
     */
    List<Pet> getAllByType(String type);


    /**
     * 发布宠物领养信息
     *
     * @param vo 领养信息
     * @return 错误信息
     */
    String publishBulletin(PublishBulletinVO vo);
}
