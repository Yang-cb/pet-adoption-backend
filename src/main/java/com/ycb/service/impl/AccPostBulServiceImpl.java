package com.ycb.service.impl;

import com.ycb.common.constant.MessageConstant;
import com.ycb.common.constant.StatusConstant;
import com.ycb.pojo.entity.Bulletin;
import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.entity.Picture;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.dto.PublishBulletinDTO;
import com.ycb.pojo.dto.UpdateBulletinDTO;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.exception.FileException;
import com.ycb.exception.OperationException;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.service.FileService;
import com.ycb.service.AccPostBulService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

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
        bulletin.setBulletinStatus(0);
        BeanUtils.copyProperties(vo, pet);
        pet.setGmtCreate(date);
        pet.setGmtModified(date);
        petBulletinMapper.saveBulletin(bulletin);
        pet.setBulletinId(bulletin.getBulletinId());
        pet.setPictureId(picture.getPicId());
        petBulletinMapper.savePet(pet);
    }

    @Override
    public List<AllPetBulletinVO> getPostPBById(Integer id) {
        return petBulletinMapper.getPostPBByAccountId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePostPBIsDelete(AccIdPetIdDTO vo) {
        int bulletinId = petBulletinMapper.getBulletinIdByPetId(vo.getPetId());
        if ("null".equals(String.valueOf(bulletinId))) {
            throw new OperationException();
        }
        // 判断用户是否发布了该宠物
        int bul = petBulletinMapper.getBulByBulIdAndAccId(bulletinId, vo.getAccId());
        if (bul < 0) {
            throw new OperationException();
        }
        int line = petBulletinMapper.updatePostPet2IsDeleteByPetId(vo.getPetId());
        line += petBulletinMapper.updatePostBIsDeleteByBulId(bulletinId);
        if (line != 2) {
            throw new OperationException();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePetByPetId(UpdateBulletinDTO vo) {
        // 判断用户是否发布了该宠物
        int one = petBulletinMapper.getBulByBulIdAndAccId(vo.getBulletinId(), vo.getAccountId());
        if (one != 1) throw new OperationException();
        // 该布告是否已经审核
        if (Objects.equals(vo.getBulletinStatus(), StatusConstant.PENDING_REVIEW))
            throw new OperationException(HttpStatus.BAD_REQUEST.value(), MessageConstant.PENDING_REVIEW);
        // 如果上传了图片
        if (vo.getFile() != null) {
            // 上传图片
            Picture picture = fileService.upload(vo.getFile(), vo.getPicType());
            if (picture == null)
                throw new FileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.FILE_UPLOAD_FAILED);
            // 修改宠物与图片的关联
            petBulletinMapper.updatePictureByPetId(vo.getPetId(), picture.getPicId());
        }
        // 对象赋值
        Date date = new Date(new java.util.Date().getTime());
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setGmtModified(date);
        bulletin.setBulletinStatus(StatusConstant.PENDING_REVIEW);
        BeanUtils.copyProperties(vo, pet);
        pet.setGmtModified(date);
        int line = petBulletinMapper.updateBulletinByBulId(bulletin);
        line += petBulletinMapper.updatePetByPetId(pet);
        if (line != 2) {
            throw new OperationException();
        }
    }
}
