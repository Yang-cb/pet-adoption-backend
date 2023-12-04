package com.ycb.controller;

import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.UpdateAccPicDTO;
import com.ycb.pojo.dto.UpdateAccountDTO;
import com.ycb.pojo.vo.AccountVO;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 账户控制器
 */
@RestController
@RequestMapping("/api/account")
@Validated
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
    public RestBean<AccountVO> getAccountById(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "用户id格式有误")
                                              @RequestParam String id) {
        AccountVO accountVO = accountService.getAccountVOById(Integer.valueOf(id));
        return RestBean.success(accountVO);
    }

    /**
     * 更新账户信息
     *
     * @param vo 账户信息
     * @return 更新结果
     */
    @PostMapping("/updateAccountById")
    public RestBean<String> updateAccountById(@Valid @RequestBody UpdateAccountDTO vo) {
        accountService.updateAccountById(vo);
        return RestBean.success();
    }

    /**
     * 更新头像
     *
     * @param vo 用户id和头像
     * @return 更新后的头像数据
     */
    @PostMapping("/updateAccPic")
    public String updateAccPic(@Valid UpdateAccPicDTO vo) {
        return accountService.updateAccPic(vo);
    }
}
