package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.PageWantAdoptDTO;
import com.ycb.pojo.dto.UpdateWantStatusDTO;
import com.ycb.pojo.entity.WantAdopt;
import com.ycb.pojo.vo.AllWantAdoptVO;

import java.util.List;

/**
 * 用户想领服务接口
 */
public interface WantAdoptService {
    /**
     * 添加想领
     *
     * @param wantAdopt 想领信息
     */
    void addWantAdopt(WantAdopt wantAdopt);

    /**
     * 根据用户id获取用户发布的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    List<AllWantAdoptVO> getSendWant(Integer accountId);

    /**
     * 获取用户收到的想领信息
     *
     * @param dto 分页查询想领数据传输对象
     * @return 想领信息
     */
    PageResult getReceiveWant(PageWantAdoptDTO dto);

    /**
     * 更新想领状态
     *
     * @param vo 想领信息
     */
    void updateWantAdoptStatus(UpdateWantStatusDTO vo);

    /**
     * 判断用户是否有新的想领
     *
     * @param accountId 用户id
     * @return 是否有新的想领
     */
    boolean hasNewReceiveWant(Integer accountId);
}
