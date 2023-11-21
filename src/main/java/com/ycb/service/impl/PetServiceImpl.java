package com.ycb.service.impl;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.dto.Picture;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.request.UpdateBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import com.ycb.mapper.PetMapper;
import com.ycb.service.FileService;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    @Resource
    private PetMapper petMapper;
    @Resource
    private FileService fileService;

    @Override
    public List<AllPetAndBulVO> getAll() {
        return petMapper.getAll();
    }

    @Override
    public List<Pet> getAllByType(String type) {
        return petMapper.getAllByType(type);
    }

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
        petMapper.saveBulletin(bulletin);
        pet.setBulletinId(bulletin.getBulletinId());
        pet.setPictureId(picture.getPicId());
        petMapper.savePet(pet);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OnePB2PicVO getPBByPetId(Integer petId) {
        // 获取宠物信息
        return petMapper.getByPetId(petId);
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
        int bId = petMapper.getBIdByPid(vo.getPetId());
        bulletin.setBulletinId(bId);
        int line = petMapper.updateBulletinByBulId(bulletin);
        line += petMapper.updatePetByPetId(pet);
        return line == 2 ? null : "修改失败";
    }
}
