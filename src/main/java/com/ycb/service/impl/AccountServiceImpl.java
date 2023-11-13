package com.ycb.service.impl;

import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;
import com.ycb.mapper.AccountMapper;
import com.ycb.mapper.CollectAccPetMapper;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private CollectAccPetMapper collectAccPetMapper;

    @Override
    public AccountVO getAccountVOById(Integer id) {
        return accountMapper.getAccountVOById(id);
    }

    @Override
    public String updateAccountById(UpdateAccountVO vo) {
        int line = accountMapper.updateAccountById(vo);
        return line > 0 ? null : "更新失败";
    }

    @Override
    public String collectPB(CollectAccPet collectAccPet){
        int line = collectAccPetMapper.save(collectAccPet);
        return line > 0 ? null : "收藏失败";
    }

    @Override
    public String cancelCollectPB(CollectAccPet collectAccPet) {
        int line = collectAccPetMapper.delete(collectAccPet);
        return line > 0 ? null : "取消收藏失败";
    }
}
