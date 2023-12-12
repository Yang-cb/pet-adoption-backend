package com.ycb.service;

import com.ycb.pojo.dto.UpdateAccountStatusDTO;
import com.ycb.pojo.dto.UpdateBulletinStatusDTO;
import com.ycb.pojo.vo.admin.AdminAllAccountVO;
import com.ycb.pojo.vo.admin.AdminAllPetBulVO;
import com.ycb.pojo.vo.admin.AdminAllWantAdoptVO;

import java.util.List;

public interface AdminService {
    /**
     * 获取所有用户信息
     *
     * @return 所有用户信息
     */
    List<AdminAllAccountVO> getAllAccount();

    /**
     * 获取所有宠物信息
     *
     * @return 所有宠物信息
     */
    List<AdminAllPetBulVO> getAllPet();

    /**
     * 获取所有想要领养的信息
     *
     * @return 所有想要领养的信息
     */
    List<AdminAllWantAdoptVO> getAllWantAdopt();

    /**
     * 修改布告状态（审核）
     *
     * @param dto 宠物id和要修改的布告状态
     */
    void updateBulletinStatus(UpdateBulletinStatusDTO dto);

    /**
     * 修改用户状态（禁用、启用）
     *
     * @param dto 用户id和要修改的用户状态
     */
    void updateAccountStatus(UpdateAccountStatusDTO dto);
}
