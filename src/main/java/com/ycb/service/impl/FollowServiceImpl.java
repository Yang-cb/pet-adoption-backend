package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.result.PageResult;
import com.ycb.mapper.FollowMapper;
import com.ycb.pojo.dto.FerIdFedIdDTO;
import com.ycb.pojo.dto.PageFollowDTO;
import com.ycb.pojo.entity.Follow;
import com.ycb.pojo.vo.FollowVO;
import com.ycb.service.FollowService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Resource
    private FollowMapper followMapper;

    @Override
    public void followAuthor(FerIdFedIdDTO dto) {
        Follow follow = new Follow();
        BeanUtils.copyProperties(dto, follow);
        if (this.isFollowed(dto)) {
            followMapper.deleteFollow(follow);
        } else {
            followMapper.addFollow(follow);
        }
    }

    @Override
    public Boolean isFollowed(FerIdFedIdDTO dto) {
        return followMapper.getOneByFerIdFedId(dto) != null;
    }

    @Override
    public Integer getFollowNumByAccountId(Integer accountId) {
        return followMapper.getFollowNumByAccountId(accountId);
    }

    @Override
    public PageResult getFollowedList(PageFollowDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<FollowVO> page = followMapper.getFollowedList(dto);
        return PageResult.builder().records(page.getResult()).total(page.getTotal()).build();
    }
}
