package com.ycb.service.impl;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.Picture;
import com.ycb.entity.vo.request.UpdateAccPicVO;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AccountService;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Resource
    private FileService fileService;

    @Override
    public AccountVO getAccountVOById(Integer id) {
        return accountMapper.getAccountVOById(id);
    }

    @Override
    public String updateAccountById(UpdateAccountVO vo) {
        int line = accountMapper.updateAccountById(vo);
        return line > 0 ? null : "更新失败";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateAccPic(UpdateAccPicVO vo) {
        Picture picture = fileService.upload(vo.getFile(), vo.getType());
        if (picture == null) {
            return RestBean.failure(401, "上传失败").jsonToString();
        }
        accountMapper.updateAccPic(vo.getId(), picture.getPicId());
        return RestBean.success(picture).jsonToString();
    }
}
