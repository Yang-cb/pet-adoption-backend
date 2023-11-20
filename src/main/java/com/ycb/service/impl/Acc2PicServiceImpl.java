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
        AccIdPetIdVO collect = collectAccPetMapper.getOneByAccIdAndPetId(vo);
        if (collect != null) {
            return "收藏失败";
        }
        int line = collectAccPetMapper.save(vo);
        return line > 0 ? null : "收藏失败";
    }

    @Override
    public String cancelCollectPB(AccIdPetIdVO vo) {
        int line = collectAccPetMapper.delete(vo);
        return line > 0 ? null : "取消收藏失败";
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
