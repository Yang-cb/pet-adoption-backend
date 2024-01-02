package com.ycb.mapper;

import com.github.pagehelper.Page;
import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.DeletePetBulletinDTO;
import com.ycb.pojo.dto.PagePetDTO;
import com.ycb.pojo.dto.PagePostPetDTO;
import com.ycb.pojo.entity.Bulletin;
import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.pojo.vo.OnePetBulletinVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 宠物和布告mapper
 */
@Mapper
public interface PetBulletinMapper {
    /**
     * 获取全部宠物和布告
     *
     * @param dto 分页参数
     * @return 宠物和布告列表
     */
    Page<AllPetBulletinVO> page(PagePetDTO dto);

    /**
     * 根据宠物id获取单个宠物和布告
     *
     * @param petId 宠物id
     * @return 宠物和布告
     */
    OnePetBulletinVO getOneByPetId(Integer petId);

    /**
     * 保存布告
     *
     * @param bulletin 布告
     */
    @AutoFill(OperationType.INSERT)
    void saveBulletin(Bulletin bulletin);

    /**
     * 保存宠物
     *
     * @param pet 宠物
     */
    @AutoFill(OperationType.INSERT)
    void savePet(Pet pet);

    /**
     * 根据用户id获取用户发布的宠物和布告
     *
     * @param dto 分页发布的宠物数据传输对象
     * @return 用户发布的宠物和布告
     */
    Page<AllPetBulletinVO> getPostPBByAccountId(PagePostPetDTO dto);

    /**
     * 根据宠物id获取布告id
     *
     * @param petId 宠物id
     * @return 布告id
     */
    int getBulletinIdByPetId(Integer petId);

    /**
     * 根据布告id修改用户发布的布告信息
     *
     * @param bulletin 布告
     * @return 修改结果
     */
    @AutoFill(OperationType.UPDATE)
    int updateBulletinByBulId(Bulletin bulletin);

    /**
     * 根据宠物id修改用户发布的宠物信息
     *
     * @param pet 宠物
     * @return 修改结果
     */
    @AutoFill(OperationType.UPDATE)
    int updatePetByPetId(Pet pet);

    /**
     * 根据宠物id删除用户发布的宠物信息
     *
     * @param dto 宠物id
     */
    @AutoFill(OperationType.DELETE)
    int deletePetByPetId(DeletePetBulletinDTO dto);

    /**
     * 根据布告id删除用户发布的布告信息
     *
     * @param dto 布告id
     */
    @AutoFill(OperationType.DELETE)
    int deleteBulletinByBulletinId(DeletePetBulletinDTO dto);

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
     *
     * @param petId 宠物id
     * @param picId 图片id
     */
    void updatePictureByPetId(Integer petId, Integer picId);
}
