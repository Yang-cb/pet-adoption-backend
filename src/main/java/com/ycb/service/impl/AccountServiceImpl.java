package com.ycb.service.impl;

import com.ycb.entity.vo.request.UpdateAccountVO;
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
    public AccountVO getAccountVOById(Integer id) {
        return accountMapper.getAccountVOById(id);
    }

    @Override
    public String updateAccountById(UpdateAccountVO vo) {
        int line = accountMapper.updateAccountById(vo);
        return line > 0 ? null : "更新失败";
    }
}
