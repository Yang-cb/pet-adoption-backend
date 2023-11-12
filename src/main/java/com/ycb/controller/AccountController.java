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

    @GetMapping()
    public RestBean<AccountVO> getAccountByUsername(@RequestParam String username) {
        AccountVO accountVO = accountService.getAccountVOByUsername(username);
        return RestBean.success(accountVO);
    }

    @PostMapping("/updateAccountByUsername")
    public RestBean<String> updateAccountByUsername(@RequestBody UpdateAccountVO vo) {
        String message = accountService.updateAccountByUsername(vo);
        return message == null ? RestBean.success("更新成功") : RestBean.failure(401, message);
    }
}
