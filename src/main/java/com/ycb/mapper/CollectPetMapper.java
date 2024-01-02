package com.ycb.mapper;

import com.github.pagehelper.Page;
import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PageCollectPetDTO;
import com.ycb.pojo.entity.CollectAccPet;
import com.ycb.pojo.vo.AllCollectVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户收藏宠物mapper
 */
@Mapper
public interface CollectPetMapper {
    /**
     * 收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     */
    @AutoFill(OperationType.INSERT)
    void save(CollectAccPet collectAccPet);

    /**
     * 取消收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     */
    @AutoFill(OperationType.DELETE)
    void delete(CollectAccPet collectAccPet);

    /**
     * 根据用户id和宠物id获取收藏信息
     *
     * @param vo 用户id和宠物id
     * @return 收藏信息
     */
    CollectAccPet getOneByAccIdAndPetId(AccIdPetIdDTO vo);

    /**
     * 根据用户id获取用户收藏的宠物和布告
     *
     * @param dto 用户id
     * @return 用户收藏的宠物和布告
     */
    Page<AllCollectVO> getCollectPBById(PageCollectPetDTO dto);
}
