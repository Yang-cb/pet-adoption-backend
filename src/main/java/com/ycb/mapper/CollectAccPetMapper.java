package com.ycb.mapper;

import com.ycb.entity.vo.request.AccIdPetIdVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Insert("insert into `pet-adoption`.collect_acc_pet(acc_id, pet_id) value(#{accId}, #{petId})")
    int save(AccIdPetIdVO vo);

    /**
     * 取消收藏宠物
     *
     * @param vo 用户id和宠物id
     */
    @Delete("delete from `pet-adoption`.collect_acc_pet where acc_id = #{accId} and pet_id = #{petId}")
    int delete(AccIdPetIdVO vo);

    /**
     * 根据用户id和宠物id获取收藏信息
     *
     * @param vo 用户id和宠物id
     * @return 收藏信息
     */
    @Select("select * from `pet-adoption`.collect_acc_pet where acc_id = #{accId} and pet_id = #{petId}")
    AccIdPetIdVO getOneByAccIdAndPetId(AccIdPetIdVO vo);

    /**
     * 根据用户id获取用户收藏的宠物和布告
     * @param id 用户id
     * @return 用户收藏的宠物和布告
     */
    List<AllPetAndBulVO> getCollectPBById(Integer id);
}
