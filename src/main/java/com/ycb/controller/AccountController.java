package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.vo.request.UpdateAccPicVO;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Resource
    private AccountService accountService;

    /**
     * 根据用户id获取账户信息
     *
     * @param id 用户id
     * @return 账户信息
     */
    @GetMapping()
    public RestBean<AccountVO> getAccountById(@RequestParam Integer id) {
        AccountVO accountVO = accountService.getAccountVOById(id);
        return RestBean.success(accountVO);
    }

    /**
     * 更新账户信息
     *
     * @param vo 账户信息
     * @return 更新结果
     */
    @PostMapping("/updateAccountById")
    public RestBean<String> updateAccountById(@RequestBody UpdateAccountVO vo) {
        String message = accountService.updateAccountById(vo);
        return message == null ? RestBean.success("更新成功") : RestBean.failure(401, message);
    }

    /**
     * 更新头像
     * @param vo 用户id和头像
     * @return 更新结果
     */
    @PostMapping("/updateAccPic")
    public String updateAccPic(UpdateAccPicVO vo) {
        return accountService.updateAccPic(vo);
    }
}
