package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.constant.StatusConstant;
import com.ycb.common.result.PageResult;
import com.ycb.mapper.AccountMapper;
import com.ycb.pojo.dto.PageWantAdoptDTO;
import com.ycb.pojo.dto.UpdateWantStatusDTO;
import com.ycb.pojo.entity.WantAdopt;
import com.ycb.pojo.vo.AllWantAdoptVO;
import com.ycb.exception.OperationException;
import com.ycb.mapper.WantAdoptMapper;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 想领的服务实现类
 */
@Service
public class WantAdoptServiceImpl implements WantAdoptService {
    @Resource
    private WantAdoptMapper wantAdoptMapper;
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void addWantAdopt(WantAdopt wantAdopt) {
        wantAdopt.setWantStatus(StatusConstant.PENDING_REVIEW);
        wantAdoptMapper.addWantAdopt(wantAdopt);
    }

    @Override
    public List<AllWantAdoptVO> getSendWant(Integer accountId) {
        return wantAdoptMapper.getSendWant(accountId);
    }

    @Override
    public PageResult getReceiveWant(PageWantAdoptDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllWantAdoptVO> page = wantAdoptMapper.getReceiveWant(dto);
        // 填充用户头像
        page.getResult().forEach(allWantAdoptVO ->
                allWantAdoptVO.setAccPicName(
                        accountMapper.getAccPicNameByAccId(allWantAdoptVO.getAccountId())
                ));
        return PageResult.builder().records(page.getResult()).total(page.getTotal()).build();
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

    @Override
    public boolean hasNewReceiveWant(Integer accountId) {
        return wantAdoptMapper.hasNewReceiveWant(accountId) > 0;
    }
}