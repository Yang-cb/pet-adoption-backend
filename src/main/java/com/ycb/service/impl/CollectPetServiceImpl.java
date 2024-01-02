package com.ycb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ycb.common.constant.StatusConstant;
import com.ycb.common.result.PageResult;
import com.ycb.exception.OperationException;
import com.ycb.mapper.AccountMapper;
import com.ycb.pojo.dto.AccIdPetIdDTO;
import com.ycb.mapper.CollectPetMapper;
import com.ycb.pojo.dto.PageCollectPetDTO;
import com.ycb.pojo.entity.CollectAccPet;
import com.ycb.pojo.vo.AllCollectVO;
import com.ycb.service.CollectPetService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 收藏宠物和布告的服务实现类
 */
@Service
public class CollectPetServiceImpl implements CollectPetService {
    @Resource
    private CollectPetMapper collectPetMapper;
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void collectPB(AccIdPetIdDTO vo) {
        // 该用户是否被禁用
        if (accountMapper.isDisableByAccountId(vo.getAccId()) == StatusConstant.DISABLE) {
            throw new OperationException();
        }
        // 该用户是否已经收藏该宠物
        Boolean isCollect = this.isCollect(vo);
        CollectAccPet collectAccPet = new CollectAccPet();
        BeanUtils.copyProperties(vo, collectAccPet);
        if (isCollect) {
            // 取消收藏
            collectPetMapper.delete(collectAccPet);
        } else {
            // 收藏
            collectPetMapper.save(collectAccPet);
        }
    }

    @Override
    public PageResult getAllCollect(PageCollectPetDTO dto) {
        // 分页查询收藏数据
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        Page<AllCollectVO> page = collectPetMapper.getCollectPBById(dto);
        // 填充用户头像
        page.getResult().forEach(allCollectVO ->
                allCollectVO.setAuthorPicName(
                        accountMapper.getAccPicNameByAccId(allCollectVO.getAccountId())
                ));
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public Boolean isCollect(AccIdPetIdDTO vo) {
        return collectPetMapper.getOneByAccIdAndPetId(vo) != null;
    }
}
