package com.ycb.service.impl;

import com.ycb.entity.vo.request.AccIdPetIdVO;
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
    public String collectPB(AccIdPetIdVO vo) {
        // 该用户是否已经收藏该宠物
        AccIdPetIdVO collect = collectAccPetMapper.getOneByAccIdAndPetId(vo);
        int line = 0;
        if (collect != null) {
            // 已收藏
            line += collectAccPetMapper.delete(vo);
        } else {
            // 未收藏
            line += collectAccPetMapper.save(vo);
        }
        if (line > 1) {
            return "系统异常";
        }
        return line > 0 ? null : "操作失败";
    }

    @Override
    public List<AllPetAndBulVO> getPostPBById(Integer id) {
        return postAccPetMapper.getPostPBById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updatePostPBIsDelete(AccIdPetIdVO vo) {
        int bulletinId = postAccPetMapper.getBulIdByPetId(vo.getPetId());
        if ("null".equals(String.valueOf(bulletinId))) {
            return "删除失败";
        }
        // 判断用户是否发布了该宠物
        int bul = postAccPetMapper.getBulByBulIdAndAccId(bulletinId, vo.getAccId());
        if (bul < 0) {
            return "删除失败";
        }
        int line = postAccPetMapper.updatePostPIsDeleteByPetId(vo.getPetId());
        line += postAccPetMapper.updatePostBIsDeleteByBulId(bulletinId);
        return line >= 2 ? null : "删除失败";
    }

    @Override
    public List<AllPetAndBulVO> getCollectPBById(Integer id) {
        return collectAccPetMapper.getCollectPBById(id);
    }

    @Override
    public Boolean isCollect(AccIdPetIdVO vo) {
        return collectAccPetMapper.getOneByAccIdAndPetId(vo) != null;
    }
}
