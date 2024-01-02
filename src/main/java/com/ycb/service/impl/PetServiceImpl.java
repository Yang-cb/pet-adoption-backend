package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.constant.IsFreeConstant;
import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.PagePetDTO;
import com.ycb.pojo.vo.AllPetBulletinVO;
import com.ycb.pojo.vo.OnePetBulletinVO;
import com.ycb.mapper.PetBulletinMapper;
import com.ycb.service.PetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 获取宠物布告的服务实现类
 */
@Service
public class PetServiceImpl implements PetService {
    @Resource
    private PetBulletinMapper petBulletinMapper;

    @Override
    public PageResult page(PagePetDTO dto) {
        String isFreeStr = dto.getIsFreeStr();
        // 如果isFree为null或者空字符串，则设置为null
        if (isFreeStr == null || isFreeStr.isEmpty()) {
            dto.setIsFree(null);
        } else {
            // 如果isFree为免费，则设置为1
            if (IsFreeConstant.IS_FREE_STR.equals(isFreeStr)) {
                dto.setIsFree(IsFreeConstant.IS_FREE);
            } else {
                // 如果isFree为收费，则设置为0
                dto.setIsFree(IsFreeConstant.NOT_FREE);
            }
        }
        // 分页查询
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllPetBulletinVO> page = petBulletinMapper.page(dto);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public OnePetBulletinVO getPBByPetId(Integer petId) {
        // 获取宠物信息
        return petBulletinMapper.getOneByPetId(petId);
    }
}
