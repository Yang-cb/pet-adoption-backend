package com.ycb.service.impl;

import com.ycb.common.constant.MessageConstant;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.pojo.entity.Account;
import com.ycb.pojo.entity.Picture;
import com.ycb.pojo.dto.UpdateAccPicDTO;
import com.ycb.pojo.dto.UpdateAccountDTO;
import com.ycb.pojo.vo.AccountVO;
import com.ycb.exception.FileException;
import com.ycb.mapper.AccountMapper;
import com.ycb.service.AccountService;
import com.ycb.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 账户的服务实现类
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Resource
    private FileService fileService;

    @Override
    public AccountVO getAccountVOById(Integer id) {
        return accountMapper.getAccountVOById(id);
    }

    @Override
    public void updateAccountById(UpdateAccountDTO vo) {
        Account account = new Account();
        BeanUtils.copyProperties(vo, account);
        accountMapper.updateAccountById(account);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Picture updateAccPic(UpdateAccPicDTO vo) {
        Picture picture = fileService.upload(vo.getFile(), vo.getType());
        if (picture == null)
            throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.FILE_UPLOAD_FAILED);
        accountMapper.updateAccPic(picture, vo.getId());
        return picture;
    }

    @Override
    public AccountVO getAccountByPetId(Integer petId) {
        int bulletinId = petBulletinMapper.getBulletinIdByPetId(petId);
        return accountMapper.getAccountByBulletinId(bulletinId);
    }
}
