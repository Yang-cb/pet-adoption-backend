package com.ycb.mapper;

import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户收藏宠物相关操作
 */
@Mapper
public interface CollectAccPetMapper {
    /**
     * 收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     */
    @Insert("insert into `pet-adoption`.collect_acc_pet(acc_id, pet_id) value(#{accId}, #{petId})")
    int save(CollectAccPet collectAccPet);

    /**
     * 取消收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     */
    @Delete("delete from `pet-adoption`.collect_acc_pet where acc_id = #{accId} and pet_id = #{petId}")
    int delete(CollectAccPet collectAccPet);

    /**
     * 根据用户id和宠物id获取收藏信息
     *
     * @param collectAccPet 用户id和宠物id
     * @return 收藏信息
     */
    @Select("select * from `pet-adoption`.collect_acc_pet where acc_id = #{accId} and pet_id = #{petId}")
    CollectAccPet getOne(CollectAccPet collectAccPet);

    List<AllPetAndBulVO> getCollectPBById(Integer id);
}
