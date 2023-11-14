package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.CollectAccPet;
import com.ycb.entity.vo.request.UpdateAccountVO;
import com.ycb.entity.vo.response.AccountVO;
import com.ycb.entity.vo.response.AllPetAndBulVO;
import com.ycb.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 收藏宠物
     * @param collectAccPet 用户id和宠物id
     * @return 收藏结果
     */
    @PostMapping("/collectPB")
    public RestBean<String> collectPB(@RequestBody CollectAccPet collectAccPet) {
        String message = accountService.collectPB(collectAccPet);
        return message == null ? RestBean.success("收藏成功") : RestBean.failure(402, message);
    }

    /**
     * 取消收藏宠物
     * @param collectAccPet 用户id和宠物id
     * @return 取消收藏结果
     */
    @PostMapping("/cancelCollectPB")
    public RestBean<String> cancelCollectPB(@RequestBody CollectAccPet collectAccPet) {
        String message = accountService.cancelCollectPB(collectAccPet);
        return message == null ? RestBean.success("取消收藏成功") : RestBean.failure(403, message);
    }

    /**
     * 获取用户发布的宠物和布告
     * @param id 用户id
     * @return 用户发布的宠物和布告
     */
    @GetMapping("/getPostPB")
    public RestBean<List<AllPetAndBulVO>> getPostPB(@RequestParam Integer id) {
        List<AllPetAndBulVO> allPetAndBulVOS = accountService.getPostPBById(id);
        return RestBean.success(allPetAndBulVOS);
    }
}
