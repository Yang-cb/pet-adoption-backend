package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.AddBulletinDTO;
import com.ycb.pojo.dto.PagePostPetDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;

/**
 * 用户发布宠物和布告的服务接口
 */
public interface PostPetService {
    /**
     * 发布宠物布告领养信息
     *
     * @param vo 领养信息
     */
    void publishBulletin(AddBulletinDTO vo);

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param dto 分页发布的宠物数据传输对象
     * @return 用户发布的宠物和布告
     */
    PageResult getPostPBById(PagePostPetDTO dto);

    /**
     * 根据宠物id删除用户发布的宠物和布告
     * @param vo 用户id s宠物id
     */
    void deletePostPB(AccIdPetIdDTO vo);

    /**
     * 根据宠物id修改宠物信息
     * @param vo 宠物信息
     */
    void updatePetByPetId(UpdateBulletinDTO vo);
}
