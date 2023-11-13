package com.ycb.service.impl;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.request.PublishBulletinVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
import com.ycb.mapper.PetMapper;
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
        // 对象赋值
        Date date = new Date(new java.util.Date().getTime());
        Bulletin bulletin = new Bulletin();
        Pet pet = new Pet();
        BeanUtils.copyProperties(vo, bulletin);
        bulletin.setGmtCreate(date);
        bulletin.setGmtModified(date);
        // TODO 获取登录者id
        bulletin.setAccountId(1);
        BeanUtils.copyProperties(vo, pet);
        pet.setGmtCreate(date);
        pet.setGmtModified(date);
        petMapper.saveBulletin(bulletin);
        pet.setBulletinId(bulletin.getId());
        petMapper.savePet(pet);
        return null;
    }

    @Override
    public OnePB2PicVO getPBByPetId(Integer petId) {
        return petMapper.getByPetId(petId);
    }
}
