package com.ycb.mapper;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 宠物（布告）相关操作
 */
@Mapper
public interface PetMapper {
    /**
     * 获取所有宠物和布告
     *
     * @return 所有宠物和布告
     */
    List<AllPetAndBulVO> getAll();

    /**
     * 根据宠物id获取宠物和布告
     *
     * @param petId 宠物id
     * @return 宠物和布告
     */
    OnePB2PicVO getByPetId(Integer petId);

    /**
     * 根据宠物种类获取宠物和布告
     *
     * @param type 宠物种类
     * @return 宠物和布告
     */
    List<Pet> getAllByType(String type);

    /**
     * 保存布告
     *
     * @param bulletin 布告
     * @return 保存结果、布告id
     */
    int saveBulletin(Bulletin bulletin);

    /**
     * 保存宠物
     *
     * @param pet 宠物
     */
    void savePet(Pet pet);

    int getBIdByPid(Integer petId);

    int updateBulletinByBulId(Bulletin bulletin);

    int updatePetByPetId(Pet pet);
}
