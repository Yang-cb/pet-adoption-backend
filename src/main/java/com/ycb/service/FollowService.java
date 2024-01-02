package com.ycb.service;

import com.ycb.common.result.PageResult;
import com.ycb.pojo.dto.FerIdFedIdDTO;
import com.ycb.pojo.dto.PageFollowDTO;

/**
 * 关注服务
 */
public interface FollowService {
    /**
     * 关注/取消关注
     *
     * @param dto 添加关注的数据传输对象
     */
    void followAuthor(FerIdFedIdDTO dto);

    /**
     * 判断是否关注
     *
     * @param dto 关注的数据传输对象
     * @return 是否关注
     */
    Boolean isFollowed(FerIdFedIdDTO dto);

    /**
     * 获取关注数
     *
     * @param accountId 用户id
     * @return 关注数
     */
    Integer getFollowNumByAccountId(Integer accountId);

    /**
     * 获取关注列表
     *
     * @param dto 关注列表的数据传输对象
     * @return 关注列表
     */
    PageResult getFollowedList(PageFollowDTO dto);
}
