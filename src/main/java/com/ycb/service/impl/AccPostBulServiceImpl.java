package com.ycb.service.impl;

import com.ycb.pojo.entity.Bulletin;
import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.entity.Picture;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PublishBulletinDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;
import com.ycb.pojo.vo.AllPetAndBulVO;
import com.ycb.exception.FileException;
import com.ycb.exception.OperationException;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.service.FileService;
import com.ycb.service.AccPostBulService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * 用户发布宠物和布告的服务实现类
 */
@Service
public class AccPostBulServiceImpl implements AccPostBulService {
    @Resource
    private FileService fileService;
    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void publishBulletin(PublishBulletinDTO vo) {
        // 上传图片
        Picture picture = fileService.upload(vo.getFile(), vo.getPicType());
        if (picture == null)
            throw new FileException();
        // 对象赋值
        Date date = new Date(new java.util.Date().getTime());
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setGmtCreate(date);
        bulletin.setGmtModified(date);
        bulletin.setAccountId(vo.getAccountId());
        BeanUtils.copyProperties(vo, pet);
        pet.setGmtCreate(date);
        pet.setGmtModified(date);
        petBulletinMapper.saveBulletin(bulletin);
        pet.setBulletinId(bulletin.getBulletinId());
        pet.setPictureId(picture.getPicId());
        petBulletinMapper.savePet(pet);
    }

    @Override
    public List<AllPetAndBulVO> getPostPBById(Integer id) {
        return petBulletinMapper.getPostPBById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePostPBIsDelete(AccIdPetIdDTO vo) {
        int bulletinId = petBulletinMapper.getBulIdByPetId(vo.getPetId());
        if ("null".equals(String.valueOf(bulletinId))) {
            throw new OperationException();
        }
        // 判断用户是否发布了该宠物
        int bul = petBulletinMapper.getBulByBulIdAndAccId(bulletinId, vo.getAccId());
        if (bul < 0) {
            throw new OperationException();
        }
        int line = petBulletinMapper.updatePostPIsDeleteByPetId(vo.getPetId());
        line += petBulletinMapper.updatePostBIsDeleteByBulId(bulletinId);
        if (line != 2) {
            throw new OperationException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePetByPetId(UpdateBulletinDTO vo) {
        // 对象赋值
        Date date = new Date(new java.util.Date().getTime());
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setGmtModified(date);
        BeanUtils.copyProperties(vo, pet);
        pet.setGmtModified(date);
        int bId = petBulletinMapper.getBIdByPid(vo.getPetId());
        bulletin.setBulletinId(bId);
        int line = petBulletinMapper.updateBulletinByBulId(bulletin);
        line += petBulletinMapper.updatePetByPetId(pet);
        if (line != 2) {
            throw new OperationException();
        }
    }
}
