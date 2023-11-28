package com.ycb.service.impl;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.dto.Picture;
import com.ycb.entity.vo.request.AccIdPetIdVO;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.request.UpdateBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
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
    public String publishBulletin(PublishBulletinVO vo) {
        // 上传图片
        Picture picture = fileService.upload(vo.getFile(), vo.getPicType());
        if (picture == null) {
            return "图片上传失败";
        }
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
        return null;
    }

    @Override
    public List<AllPetAndBulVO> getPostPBById(Integer id) {
        return petBulletinMapper.getPostPBById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePostPBIsDelete(AccIdPetIdVO vo) {
        int bulletinId = petBulletinMapper.getBulIdByPetId(vo.getPetId());
        if ("null".equals(String.valueOf(bulletinId))) {
            return "删除失败";
        }
        // 判断用户是否发布了该宠物
        int bul = petBulletinMapper.getBulByBulIdAndAccId(bulletinId, vo.getAccId());
        if (bul < 0) {
            return "删除失败";
        }
        int line = petBulletinMapper.updatePostPIsDeleteByPetId(vo.getPetId());
        line += petBulletinMapper.updatePostBIsDeleteByBulId(bulletinId);
        return line >= 2 ? null : "删除失败";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePetByPetId(UpdateBulletinVO vo) {
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
        return line == 2 ? null : "修改失败";
    }
}
