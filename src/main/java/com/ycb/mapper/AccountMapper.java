package com.ycb.mapper;

import com.ycb.entity.dto.Account;
import com.ycb.entity.vo.request.ResetPwVO;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface AccountMapper {
    /**
     * 根据用户名获取账户信息
     * @param username 用户名
     * @return 账户信息
     */
    @Select("select * from `pet-adoption`.account where username = #{username}")
    Account getByUsername(String username);

    /**
     * 保存账户信息
     * @param account 账户信息
     * @return 保存结果
     */
    @Insert("insert into `pet-adoption`.account(username, password, email, gmt_create, gmt_modified, authority, nike_name)" +
            " value (#{username},#{password},#{email},#{gmtCreate},#{gmtModified},#{authority},#{nikeName})")
    int save(Account account);

    /**
     * 根据用户email获取账户信息
     * @param email 用户email
     * @return 账户信息
     */
    @Select("select * from `pet-adoption`.account where email =#{email}")
    Account getByEmail(String email);

    /**
     * 重置密码
     * @param resetPwVO 重置密码信息
     * @return 重置结果
     */
    @Update("update `pet-adoption`.account set password = #{password} where email = #{email}")
    int updatePwByEmail(ResetPwVO resetPwVO);

    /**
     * 根据用户id获取账户信息
     * @param id 用户id
     * @return 账户信息
     */
    AccountVO getAccountVOById(Integer id);

    /**
     * 根据用户id更新账户信息
     * @param vo 账户信息
     * @return 更新结果
     */
    int updateAccountById(UpdateAccountVO vo);


}
