package com.ycb.service.impl;

import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.entity.vo.response.OnePB2PicVO;
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
    public List<AllPetAndBulVO> getAll() {
        return petBulletinMapper.getAll();
    }

    @Override
    public List<Pet> getAllByType(String type) {
        return petBulletinMapper.getAllByType(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OnePB2PicVO getPBByPetId(Integer petId) {
        // 获取宠物信息
        return petBulletinMapper.getByPetId(petId);
    }
}
