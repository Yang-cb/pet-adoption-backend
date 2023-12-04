package com.ycb.mapper;

import com.ycb.pojo.entity.Bulletin;
import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.vo.AllPetAndBulVO;
import com.ycb.pojo.vo.OnePB2PicVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 宠物和布告mapper
 */
@Mapper
public interface PetBulletinMapper {
    /**
     * 获取所有宠物和布告
     *
     * @return 所有宠物和布告
     */
    List<AllPetAndBulVO> getAll();

    /**
     * 根据宠物id获取单个宠物和布告
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
     * @return 布告id
     */
    int saveBulletin(Bulletin bulletin);

    /**
     * 保存宠物
     *
     * @param pet 宠物
     */
    void savePet(Pet pet);

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    List<AllPetAndBulVO> getPostPBById(Integer id);

    /**
     * 根据宠物id获取布告id
     *
     * @param petId 宠物id
     * @return 布告id
     */
    int getBIdByPid(Integer petId);

    /**
     * 根据布告id修改用户发布的布告信息
     *
     * @param bulletin 布告
     * @return 修改结果
     */
    int updateBulletinByBulId(Bulletin bulletin);

    /**
     * 根据宠物id修改用户发布的宠物信息
     *
     * @param pet 宠物
     * @return 修改结果
     */
    int updatePetByPetId(Pet pet);

    /**
     * 根据宠物id删除用户发布的宠物信息
     *
     * @param petId 宠物id
     */
    int updatePostPIsDeleteByPetId(Integer petId);

    /**
     * 根据布告id获取宠物id
     *
     * @param bulletinId 布告id
     * @return 宠物id
     */
    int getBulIdByPetId(Integer bulletinId);

    /**
     * 根据布告id删除用户发布的布告信息
     *
     * @param bId 布告id
     */
    int updatePostBIsDeleteByBulId(Integer bId);

    /**
     * 根据宠物id和用户id获取宠物
     *
     * @param bulletinId 布告id
     * @param accId      用户id
     * @return 宠物
     */
    int getBulByBulIdAndAccId(Integer bulletinId, Integer accId);

    /**
     * 根据宠物id修改宠物图片
     * @param petId 宠物id
     * @param picId 图片id
     */
    void updatePictureByPetId(Integer petId, Integer picId);
}
