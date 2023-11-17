package com.ycb.service;

import com.ycb.entity.vo.request.UpdateAccPicVO;
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
     * 更新头像
     * @param vo 用户id和头像
     * @return 更新结果
     */
    String updateAccPic(UpdateAccPicVO vo);
}
