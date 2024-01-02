package com.ycb.mapper;

import com.github.pagehelper.Page;
import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.dto.FerIdFedIdDTO;
import com.ycb.pojo.dto.PageFollowDTO;
import com.ycb.pojo.entity.Follow;
import com.ycb.pojo.vo.FollowVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 关注mapper
 */
@Mapper
public interface FollowMapper {
    /**
     * 添加关注
     *
     * @param follow 关注实体类
     */
    @AutoFill(OperationType.INSERT)
    void addFollow(Follow follow);

    /**
     * 删除关注
     *
     * @param follow 关注实体类
     */
    @AutoFill(OperationType.DELETE)
    void deleteFollow(Follow follow);

    /**
     * 根据关注者id和被关注者id获取关注
     *
     * @param dto 关注的数据传输对象
     * @return 关注
     */
    Follow getOneByFerIdFedId(FerIdFedIdDTO dto);

    /**
     * 根据用户id获取关注数
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
    Page<FollowVO> getFollowedList(PageFollowDTO dto);
}
