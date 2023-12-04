package com.ycb.service.impl;

import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.pojo.vo.AllPetAndBulVO;
import com.ycb.exception.SystemException;
import com.ycb.mapper.CollectAccPetMapper;
import com.ycb.service.AccCollectBulService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏宠物和布告的服务实现类
 */
@Service
public class AccCollectBulServiceImpl implements AccCollectBulService {
    @Resource
    private CollectAccPetMapper collectAccPetMapper;

    @Override
    public void collectPB(AccIdPetIdDTO vo) {
        // 该用户是否已经收藏该宠物
        AccIdPetIdDTO collect = collectAccPetMapper.getOneByAccIdAndPetId(vo);
        int line = 0;
        if (collect != null) {
            // 已收藏
            line += collectAccPetMapper.delete(vo);
        } else {
            // 未收藏
            line += collectAccPetMapper.save(vo);
        }
        if (line != 1) {
            throw new SystemException();
        }
    }

    @Override
    public List<AllPetAndBulVO> getCollectPBById(Integer id) {
        return collectAccPetMapper.getCollectPBById(id);
    }

    @Override
    public Boolean isCollect(AccIdPetIdDTO vo) {
        return collectAccPetMapper.getOneByAccIdAndPetId(vo) != null;
    }
}
