package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.constant.MessageConstant;
import com.ycb.common.constant.StatusConstant;
import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.*;
import com.ycb.pojo.entity.Bulletin;
import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.entity.Picture;
import com.ycb.exception.FileException;
import com.ycb.exception.OperationException;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.service.FileService;
import com.ycb.service.PostPetService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 用户发布宠物和布告的服务实现类
 */
@Service
public class PostPetServiceImpl implements PostPetService {
    @Resource
    private FileService fileService;
    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void publishBulletin(AddBulletinDTO vo) {
        // 上传图片
        Picture picture = fileService.upload(vo.getFile(), vo.getPicType());
        if (picture == null)
            throw new FileException();
        // 对象赋值
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setAccountId(vo.getAccountId());
        bulletin.setBulletinStatus(StatusConstant.PENDING_REVIEW);
        BeanUtils.copyProperties(vo, pet);
        petBulletinMapper.saveBulletin(bulletin);
        pet.setBulletinId(bulletin.getBulletinId());
        pet.setPictureId(picture.getPicId());
        petBulletinMapper.savePet(pet);
    }

    @Override
    public PageResult getPostPBById(PagePostPetDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllPetBulletinVO> page = petBulletinMapper.getPostPBByAccountId(dto);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePostPB(AccIdPetIdDTO vo) {
        int bulletinId = petBulletinMapper.getBulletinIdByPetId(vo.getPetId());
        if ("null".equals(String.valueOf(bulletinId))) {
            throw new OperationException();
        }
        // 判断用户是否发布了该宠物
        int bul = petBulletinMapper.getBulByBulIdAndAccId(bulletinId, vo.getAccId());
        if (bul < 0) {
            throw new OperationException();
        }
        DeletePetBulletinDTO dto =
                DeletePetBulletinDTO.builder().petId(vo.getPetId()).bulletinId(bulletinId).build();
        int line = petBulletinMapper.deletePetByPetId(dto);
        line += petBulletinMapper.deleteBulletinByBulletinId(dto);
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
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setBulletinStatus(StatusConstant.PENDING_REVIEW);
        BeanUtils.copyProperties(vo, pet);
        int line = petBulletinMapper.updateBulletinByBulId(bulletin);
        line += petBulletinMapper.updatePetByPetId(pet);
        if (line != 2) {
            throw new OperationException();
        }
    }
}
