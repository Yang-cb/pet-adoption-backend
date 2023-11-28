package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.WantAdopt;
import com.ycb.entity.vo.request.UpdateWantAdoptVO;
import com.ycb.entity.vo.response.AllWantAdoptVO;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WantAdoptController
 */
@RestController
@RequestMapping("/api/wantAdopt")
@Validated
public class WantAdoptController {
    @Resource
    private WantAdoptService wantAdoptService;

    /**
     * 添加想领
     *
     * @param wantAdopt 想领信息
     * @return 错误信息
     */
    @PostMapping("/addWantAdopt")
    public RestBean<String> addWantAdopt(@Valid @RequestBody WantAdopt wantAdopt) {
        String msg = wantAdoptService.addWantAdopt(wantAdopt);
        return msg == null ? RestBean.success(null) : RestBean.failure(400, msg);
    }

    /**
     * 根据用户id获取用户发布的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    @GetMapping("/getWantAdopt")
    public RestBean<List<AllWantAdoptVO>> getWantAdoptByAccId(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                                              @RequestParam("accountId") String accountId) {
        List<AllWantAdoptVO> wantAdopts = wantAdoptService.getWantAdoptByAccId(Integer.valueOf(accountId));
        return wantAdopts == null ? RestBean.failure(400, "获取失败") : RestBean.success(wantAdopts);
    }

    /**
     * 获取用户收到的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    @GetMapping("/getReceiveWantAdopt")
    public RestBean<List<AllWantAdoptVO>> getReceiveWantAdopt(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                                              @RequestParam("accountId") String accountId) {
        List<AllWantAdoptVO> wantAdopts = wantAdoptService.getReceiveWantAdopt(Integer.valueOf(accountId));
        return wantAdopts == null ? RestBean.failure(400, "获取失败") : RestBean.success(wantAdopts);
    }

    /**
     * 更新想领状态
     *
     * @param vo 想领信息
     * @return 错误信息
     */
    @PutMapping("/updateWantAdoptStatus")
    public RestBean<String> updateWantAdoptStatus(@Valid @RequestBody UpdateWantAdoptVO vo) {
        String msg = wantAdoptService.updateWantAdoptStatus(vo);
        return msg == null ? RestBean.success(null) : RestBean.failure(400, msg);
    }
}
