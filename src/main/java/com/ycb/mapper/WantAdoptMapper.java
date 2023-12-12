package com.ycb.mapper;

import com.ycb.pojo.dto.UpdateWantStatusDTO;
import com.ycb.pojo.entity.WantAdopt;
import com.ycb.pojo.vo.AllWantAdoptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户想领mapper
 */
@Mapper
public interface WantAdoptMapper {
    /**
     * 添加想领
     * @param wantAdopt 想领信息
     * @return 添加行数
     */
    int addWantAdopt(WantAdopt wantAdopt);

    /**
     * 根据用户id获取用户发布的全部想领信息
     * @param accountId 用户id
     * @return 发布的全部想领信息
     */
    List<AllWantAdoptVO> getWantAdoptByAccId(Integer accountId);

    /**
     * 根据用户id获取用户收到的全部想领信息
     * @param accountId 用户id
     * @return 收到的全部想领信息
     */
    List<AllWantAdoptVO> getReceiveWantAdoptByAccId(Integer accountId);

    /**
     * 根据发布宠物布告的用户id和想领id查看审核通过的（status = ?）想领信息是否存在
     *
     * @param vo 想领信息
     * @return 想领信息
     */
    int existByAccIdAndWantId(UpdateWantStatusDTO vo);

    /**
     * 更新想领状态
     * @param vo 想领信息
     * @return 更新行数
     */
    int updateWantAdoptStatus(UpdateWantStatusDTO vo);
}
