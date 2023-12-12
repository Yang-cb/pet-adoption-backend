package com.ycb.service.impl;

import com.ycb.mapper.AdminMapper;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.pojo.dto.UpdateAccountStatusDTO;
import com.ycb.pojo.dto.UpdateBulletinStatusDTO;
import com.ycb.pojo.vo.admin.AdminAllAccountVO;
import com.ycb.pojo.vo.admin.AdminAllPetBulVO;
import com.ycb.pojo.vo.admin.AdminAllWantAdoptVO;
import com.ycb.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Override
    public List<AdminAllAccountVO> getAllAccount() {
        return adminMapper.getAllAccount();
    }

    @Override
    public List<AdminAllPetBulVO> getAllPet() {
        return adminMapper.getAllPet();
    }

    @Override
    public List<AdminAllWantAdoptVO> getAllWantAdopt() {
        return adminMapper.getAllWantAdopt();
    }

    @Override
    public void updateBulletinStatus(UpdateBulletinStatusDTO dto) {
        Integer bulletinId = petBulletinMapper.getBulletinIdByPetId(dto.getPetId());
        adminMapper.updateBulletinStatus(bulletinId, dto.getBulletinStatus());
    }

    @Override
    public void updateAccountStatus(UpdateAccountStatusDTO dto) {
        adminMapper.updateAccountStatus(dto);
    }
}
