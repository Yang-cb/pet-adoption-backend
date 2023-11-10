package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.vo.response.AccountVO;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
