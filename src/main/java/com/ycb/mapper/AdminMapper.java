package com.ycb.mapper;

import com.ycb.pojo.dto.UpdateAccountStatusDTO;
import com.ycb.pojo.vo.admin.AdminAllAccountVO;
import com.ycb.pojo.vo.admin.AdminAllPetBulVO;
import com.ycb.pojo.vo.admin.AdminAllWantAdoptVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<AdminAllAccountVO> getAllAccount();

    List<AdminAllPetBulVO> getAllPet();

    List<AdminAllWantAdoptVO> getAllWantAdopt();

    void updateBulletinStatus(Integer bulletinId, Integer bulletinStatus);

    void updateAccountStatus(UpdateAccountStatusDTO dto);

}
