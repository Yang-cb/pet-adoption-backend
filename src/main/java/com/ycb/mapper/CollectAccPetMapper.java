package com.ycb.mapper;

import com.ycb.entity.dto.CollectAccPet;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
