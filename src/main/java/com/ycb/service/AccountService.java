package com.ycb.service;

import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;

public interface AccountService {
    /**
     * 根据用户名获取账户信息
     * @param username 用户名
     * @return 账户信息
     */
    AccountVO getAccountVOByUsername(String username);

    /**
     * 更新账户信息
     * @param vo 账户信息
     * @return 更新结果
     */
    String updateAccountByUsername(UpdateAccountVO vo);
}
