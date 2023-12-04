package com.ycb.service;

import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PublishBulletinDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;
import com.ycb.pojo.vo.AllPetAndBulVO;

import java.util.List;

/**
 * 用户发布宠物和布告的服务接口
 */
public interface AccPostBulService {
    /**
     * 发布宠物布告领养信息
     *
     * @param vo 领养信息
     */
    void publishBulletin(PublishBulletinDTO vo);

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    List<AllPetAndBulVO> getPostPBById(Integer id);

    /**
     * 根据宠物id删除用户发布的宠物和布告
     * @param vo 用户id s宠物id
     */
    void updatePostPBIsDelete(AccIdPetIdDTO vo);

    /**
     * 根据宠物id修改宠物信息
     * @param vo 宠物信息
     */
    void updatePetByPetId(UpdateBulletinDTO vo);
}
