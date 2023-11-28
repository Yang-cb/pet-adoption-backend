package com.ycb.service.impl;

import com.ycb.entity.dto.WantAdopt;
import com.ycb.entity.vo.request.UpdateWantAdoptVO;
import com.ycb.entity.vo.response.AllWantAdoptVO;
import com.ycb.mapper.WantAdoptMapper;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class WantAdoptServiceImpl implements WantAdoptService {
    @Resource
    private WantAdoptMapper wantAdoptMapper;

    @Override
    public String addWantAdopt(WantAdopt wantAdopt) {
        Date date = new Date(new java.util.Date().getTime());
        wantAdopt.setGmtCreate(date);
        wantAdopt.setGmtModified(date);
        wantAdopt.setWantStatus(0);
        int i = wantAdoptMapper.addWantAdopt(wantAdopt);
        return i == 1 ? null : "操作失败";
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
    public String updateWantAdoptStatus(UpdateWantAdoptVO vo) {
        // 根据发布宠物布告的用户id和想领id查看审核通过的（status = 1）想领信息是否存在
        int len = wantAdoptMapper.existByAccIdAndWantId(vo.getAccountId(), vo.getWantId());
        if (len != 1) {
            return "操作失败";
        }
        // 更新想领状态
        len += wantAdoptMapper.updateWantAdoptStatus(vo.getWantId(), vo.getWantStatus());
        return len == 2 ? null : "操作失败";
    }
}
