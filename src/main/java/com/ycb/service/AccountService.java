package com.ycb.service;

import com.ycb.pojo.dto.UpdateAccPicDTO;
import com.ycb.pojo.dto.UpdateAccountDTO;
import com.ycb.pojo.entity.Picture;
import com.ycb.pojo.vo.AccountVO;

/**
 * 账户service
 */
public interface AccountService {
    /**
     * 根据用户id获取账户信息
     *
     * @param id 用户id
     * @return 账户信息
     */
    AccountVO getAccountVOById(Integer id);

    /**
     * 更新账户信息
     *
     * @param vo 账户信息
     */
    void updateAccountById(UpdateAccountDTO vo);


    /**
     * 更新头像
     *
     * @param vo 用户id和头像
     * @return 更新后的头像数据
     */
    Picture updateAccPic(UpdateAccPicDTO vo);

    /**
     * 根据宠物id获取账户信息
     *
     * @param petId 宠物id
     * @return 账户信息
     */
    AccountVO getAccountByPetId(Integer petId);
}
