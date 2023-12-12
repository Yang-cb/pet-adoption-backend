package com.ycb.mapper;

import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.vo.AllPetBulletinVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户收藏宠物mapper
 */
@Mapper
public interface CollectAccPetMapper {
    /**
     * 收藏宠物
     *
     * @param vo 用户id和宠物id
     */
    int save(AccIdPetIdDTO vo);

    /**
     * 取消收藏宠物
     *
     * @param vo 用户id和宠物id
     */
    int delete(AccIdPetIdDTO vo);

    /**
     * 根据用户id和宠物id获取收藏信息
     *
     * @param vo 用户id和宠物id
     * @return 收藏信息
     */
    AccIdPetIdDTO getOneByAccIdAndPetId(AccIdPetIdDTO vo);

    /**
     * 根据用户id获取用户收藏的宠物和布告
     * @param id 用户id
     * @return 用户收藏的宠物和布告
     */
    List<AllPetBulletinVO> getCollectPBById(Integer id);
}
