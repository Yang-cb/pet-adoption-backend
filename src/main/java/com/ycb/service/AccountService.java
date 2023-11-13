package com.ycb.service;

import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;

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
     * @return 更新结果
     */
    String updateAccountById(UpdateAccountVO vo);

    /**
     * 收藏宠物
     *
     * @param collectAccPet 用户id和宠物id
     * @return 收藏结果
     */
    String collectPB(CollectAccPet collectAccPet);

    /**
     * 取消收藏宠物
     * @param collectAccPet 用户id和宠物id
     * @return 取消收藏结果
     */

    String cancelCollectPB(CollectAccPet collectAccPet);
}
