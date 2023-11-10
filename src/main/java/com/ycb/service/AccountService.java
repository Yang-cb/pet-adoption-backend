package com.ycb.service;

import com.ycb.entity.vo.response.AccountVO;

public interface AccountService {
    AccountVO getAccountVOByUsername(String username);
}
