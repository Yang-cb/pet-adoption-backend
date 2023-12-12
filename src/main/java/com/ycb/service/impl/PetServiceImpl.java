package com.ycb.service.impl;

import com.ycb.pojo.entity.Pet;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.pojo.vo.OnePetBulletinVO;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 获取宠物布告的服务实现类
 */
@Service
public class PetServiceImpl implements PetService {
    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Override
    public List<AllPetBulletinVO> getAll() {
        return petBulletinMapper.getAll();
    }

    @Override
    public List<Pet> getAllByType(String type) {
        return petBulletinMapper.getAllByPetType(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OnePetBulletinVO getPBByPetId(Integer petId) {
        // 获取宠物信息
        return petBulletinMapper.getOneByPetId(petId);
    }
}
