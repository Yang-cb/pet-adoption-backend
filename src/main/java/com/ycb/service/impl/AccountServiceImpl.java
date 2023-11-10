package com.ycb.service.impl;

import com.ycb.entity.vo.response.AccountVO;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Override
    public AccountVO getAccountVOByUsername(String username) {
        return accountMapper.getAccountVOByUsername(username);
    }
}
