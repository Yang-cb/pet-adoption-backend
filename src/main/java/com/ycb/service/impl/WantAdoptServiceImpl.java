package com.ycb.service.impl;

import com.ycb.entity.dto.WantAdopt;
import com.ycb.mapper.WantAdoptMapper;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class WantAdoptServiceImpl implements WantAdoptService {
    @Resource
    private WantAdoptMapper wantAdoptMapper;

    @Override
    public String addWantAdopt(WantAdopt wantAdopt) {
        Date date = new Date(new java.util.Date().getTime());
        wantAdopt.setGmtCreate(date);
        wantAdopt.setGmtModified(date);
        int i = wantAdoptMapper.addWantAdopt(wantAdopt);
        return i == 1 ? null : "操作失败";
    }
}
