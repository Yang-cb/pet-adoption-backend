package com.ycb.mapper;

import com.ycb.entity.dto.Account;
import com.ycb.entity.vo.request.RegisterVO;
import com.ycb.entity.vo.request.ResetPwVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountMapper {
    @Select("select * from `pet-adoption`.account where username = #{username}")
    Account getByUsername(String username);

    @Insert("insert into `pet-adoption`.account(username, password, email, create_time, update_time, authority)" +
            " value (#{username},#{password},#{email},#{createTime},#{updateTime},#{authority})")
    int save(Account account);

    @Select("select * from `pet-adoption`.account where email =#{email}")
    Account getByEmail(String email);

    @Update("update `pet-adoption`.account set password = #{password} where email = #{email}")
    int updatePwByEmail(ResetPwVO resetPwVO);
}
