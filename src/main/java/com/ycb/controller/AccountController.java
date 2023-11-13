package com.ycb.controller;

import com.ycb.entity.RestBean;
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
     * 根据用户名获取账户信息
     *
     * @param username 用户名
     * @return 账户信息
     */
    @GetMapping()
    public RestBean<AccountVO> getAccountByUsername(@RequestParam String username) {
        AccountVO accountVO = accountService.getAccountVOByUsername(username);
        return RestBean.success(accountVO);
    }

    /**
     * 更新账户信息
     *
     * @param vo 账户信息
     * @return 更新结果
     */
    @PostMapping("/updateAccountByUsername")
    public RestBean<String> updateAccountByUsername(@RequestBody UpdateAccountVO vo) {
        String message = accountService.updateAccountByUsername(vo);
        return message == null ? RestBean.success("更新成功") : RestBean.failure(401, message);
    }

//    @GetMapping("/collectPB")
//    public RestBean<String> collectPB(@RequestParam String username, @RequestParam Integer petId) {
//        String message = accountService.collectPB(username, petId);
//        return message == null ? RestBean.success("收藏成功") : RestBean.failure(402, message);
//    }
}
