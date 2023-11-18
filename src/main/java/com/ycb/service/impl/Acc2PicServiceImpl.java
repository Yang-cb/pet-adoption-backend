package com.ycb.service.impl;

import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.mapper.CollectAccPetMapper;
import com.ycb.mapper.PostAccPetMapper;
import com.ycb.service.Acc2PicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Acc2PicServiceImpl implements Acc2PicService {
    @Resource
    private CollectAccPetMapper collectAccPetMapper;
    @Resource
    private PostAccPetMapper postAccPetMapper;

    @Override
    public String collectPB(CollectAccPet collectAccPet) {
        CollectAccPet collect = collectAccPetMapper.getOne(collectAccPet);
        if (collect != null) {
            return "收藏失败";
        }
        int line = collectAccPetMapper.save(collectAccPet);
        return line > 0 ? null : "收藏失败";
    }

    @Override
    public String cancelCollectPB(CollectAccPet collectAccPet) {
        int line = collectAccPetMapper.delete(collectAccPet);
        return line > 0 ? null : "取消收藏失败";
    }

    @Override
    public List<AllPetAndBulVO> getPostPBById(Integer id) {
        return postAccPetMapper.getPostPBById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePostPBIsDeleteByPetId(Integer petId) {
        int bulletinId = postAccPetMapper.getBulIdByPetId(petId);
        if ("null".equals(String.valueOf(bulletinId))) {
            return "删除失败";
        }
        int line = postAccPetMapper.updatePostPIsDeleteByPetId(petId);
        line += postAccPetMapper.updatePostBIsDeleteByBulId(bulletinId);
        return line >= 2 ? null : "删除失败";
    }

    @Override
    public List<AllPetAndBulVO> getCollectPBById(Integer id) {
        return collectAccPetMapper.getCollectPBById(id);
    }
}
