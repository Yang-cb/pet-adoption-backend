package com.ycb.mapper;

import com.ycb.annotation.AutoFill;
import com.ycb.common.enumerate.OperationType;
import com.ycb.pojo.entity.Account;
import com.ycb.pojo.dto.ResetPwDTO;
import com.ycb.pojo.entity.Picture;
import com.ycb.pojo.vo.AccountVO;
import com.ycb.pojo.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户mapper
 */
@Mapper
public interface AccountMapper {
    /**
     * 根据用户名获取账户信息
     *
     * @param username 用户名
     * @return 账户信息
     */
    Account getByUsername(String username);

    /**
     * 登陆成功后，根据用户名获取账户信息
     *
     * @param username 用户名
     * @return 账户信息
     */
    LoginVO getLoginVOByUsername(String username);

    /**
     * 添加账户信息
     *
     * @param account 账户信息
     * @return 保存结果
     */
    @AutoFill(OperationType.INSERT)
    int save(Account account);

    /**
     * 根据用户email获取账户信息
     *
     * @param email 用户email
     * @return 账户信息
     */
    Account getByEmail(String email);

    /**
     * 重置密码
     *
     * @param resetPwDTO 重置密码信息
     * @return 重置结果
     */
    @AutoFill(OperationType.UPDATE)
    int updatePwByEmail(ResetPwDTO resetPwDTO);

    /**
     * 根据用户id获取账户信息
     *
     * @param id 用户id
     * @return 账户信息
     */
    AccountVO getAccountVOById(Integer id);

    /**
     * 根据用户id更新账户信息
     *
     * @param account 账户信息
     */
    @AutoFill(OperationType.UPDATE)
    void updateAccountById(Account account);

    /**
     * 根据用户id更新头像
     *
     * @param picture 头像
     * @param accId   用户id
     */
    @AutoFill(OperationType.UPDATE)
    void updateAccPic(Picture picture, Integer accId);

    /**
     * 根据用户名获取账户状态
     *
     * @param username 用户名
     * @return 账户状态
     */
    int isDisableByUsername(String username);

    /**
     * 根据用户id获取账户状态
     *
     * @param id 用户id
     * @return 账户状态
     */
    int isDisableByAccountId(Integer id);

    /**
     * 根据用户id获取邮箱
     *
     * @param accountId 用户id
     * @return 邮箱
     */
    String getEmailById(Integer accountId);

    /**
     * 根据布告id获取发布宠物的账户信息
     *
     * @param bulletinId 布告id
     * @return 账户信息
     */
    AccountVO getAccountByBulletinId(Integer bulletinId);


    /**
     * 根据用户id获取用户头像
     *
     * @param accountId 用户id
     * @return 用户头像
     */
    String getAccPicNameByAccId(Integer accountId);
}
