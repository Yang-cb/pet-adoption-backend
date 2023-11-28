package com.ycb.service;

import com.ycb.entity.dto.WantAdopt;
import com.ycb.entity.vo.request.UpdateWantAdoptVO;
import com.ycb.entity.vo.response.AllWantAdoptVO;

import java.util.List;

public interface WantAdoptService {
    /**
     * 添加想领
     *
     * @param wantAdopt 想领信息
     * @return 错误信息
     */
    String addWantAdopt(WantAdopt wantAdopt);

    /**
     * 根据用户id获取用户发布的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    List<AllWantAdoptVO> getWantAdoptByAccId(Integer accountId);

    /**
     * 获取用户收到的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    List<AllWantAdoptVO> getReceiveWantAdopt(Integer accountId);

    /**
     * 更新想领状态
     *
     * @param vo 想领信息
     * @return 错误信息
     */
    String updateWantAdoptStatus(UpdateWantAdoptVO vo);
}
