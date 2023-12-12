package com.ycb.service.impl;

import com.ycb.pojo.dto.UpdateWantStatusDTO;
import com.ycb.pojo.entity.WantAdopt;
import com.ycb.pojo.vo.AllWantAdoptVO;
import com.ycb.exception.OperationException;
import com.ycb.exception.SystemException;
import com.ycb.mapper.WantAdoptMapper;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * 想领的服务实现类
 */
@Service
public class WantAdoptServiceImpl implements WantAdoptService {
    @Resource
    private WantAdoptMapper wantAdoptMapper;

    @Override
    public void addWantAdopt(WantAdopt wantAdopt) {
        Date date = new Date(new java.util.Date().getTime());
        wantAdopt.setGmtCreate(date);
        wantAdopt.setGmtModified(date);
        wantAdopt.setWantStatus(0);
        int i = wantAdoptMapper.addWantAdopt(wantAdopt);
        if (i != 1) {
            throw new SystemException();
        }
    }

    @Override
    public List<AllWantAdoptVO> getWantAdoptByAccId(Integer accountId) {
        return wantAdoptMapper.getWantAdoptByAccId(accountId);
    }

    @Override
    public List<AllWantAdoptVO> getReceiveWantAdopt(Integer accountId) {
        return wantAdoptMapper.getReceiveWantAdoptByAccId(accountId);
    }

    @Override
    public void updateWantAdoptStatus(UpdateWantStatusDTO vo) {
        // 根据发布宠物布告的用户id和想领id查看审核通过的（status = ?）想领信息是否存在
        int len = wantAdoptMapper.existByAccIdAndWantId(vo);
        if (len != 1) {
            throw new OperationException();
        }
        // 更新想领状态
        wantAdoptMapper.updateWantAdoptStatus(vo);
    }
}