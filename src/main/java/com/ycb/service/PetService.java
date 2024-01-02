package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.PagePetDTO;
import com.ycb.pojo.vo.OnePetBulletinVO;

/**
 * 获取宠物布告相关操作的服务接口
 */
public interface PetService {

    /**
     * 获取全部宠物信息
     *
     * @param dto 分页参数
     * @return 宠物列表
     */
    PageResult page(PagePetDTO dto);

    /**
     * 根据宠物id获取宠物信息
     *
     * @param petId 宠物id
     * @return 宠物信息
     */
    OnePetBulletinVO getPBByPetId(Integer petId);
}
