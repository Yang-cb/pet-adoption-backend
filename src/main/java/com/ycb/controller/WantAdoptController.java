package com.ycb.controller;

import com.ycb.common.result.PageResult;
import com.ycb.common.result.RestBean;
import com.ycb.pojo.dto.PageWantAdoptDTO;
import com.ycb.pojo.dto.UpdateWantStatusDTO;
import com.ycb.pojo.entity.WantAdopt;
import com.ycb.pojo.dto.UpdateWantStatus34DTO;
import com.ycb.pojo.vo.AllWantAdoptVO;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户想领控制器
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
        wantAdoptService.addWantAdopt(wantAdopt);
        return RestBean.success();
    }

    /**
     * 根据用户id获取用户发布的想领信息
     *
     * @param accountId 用户id
     * @return 想领信息
     */
    @GetMapping("/getSendWant")
    public RestBean<List<AllWantAdoptVO>> getSendWant(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                                              @RequestParam("accountId") String accountId) {
        List<AllWantAdoptVO> wantAdopts = wantAdoptService.getSendWant(Integer.valueOf(accountId));
        return RestBean.success(wantAdopts);
    }

    /**
     * 获取用户收到的想领信息
     *
     * @param dto 分页查询想领数据传输对象
     * @return 想领信息
     */
    @GetMapping("/getReceiveWant")
    public RestBean<PageResult> getReceiveWant(PageWantAdoptDTO dto) {
        PageResult wantAdopts = wantAdoptService.getReceiveWant(dto);
        return RestBean.success(wantAdopts);
    }

    /**
     * 用户更新想领状态
     *
     * @param dto 想领信息
     * @return 错误信息
     */
    @PutMapping("/updateWantAdoptStatus")
    public RestBean<String> updateWantAdoptStatus(@Valid @RequestBody UpdateWantStatus34DTO dto) {
        UpdateWantStatusDTO updateWantStatusDTO = new UpdateWantStatusDTO();
        BeanUtils.copyProperties(dto, updateWantStatusDTO);
        wantAdoptService.updateWantAdoptStatus(updateWantStatusDTO);
        return RestBean.success();
    }

    /**
     * 是否有新地想领请求
     *
     * @param accountId 用户id
     * @return 是否有新地想领请求
     */
    @GetMapping("/hasNewReceive")
    public RestBean<Boolean> hasNewReceiveWant(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "id格式有误")
                                             @RequestParam("accountId") String accountId) {
        boolean hasNewWantAdopt = wantAdoptService.hasNewReceiveWant(Integer.valueOf(accountId));
        return RestBean.success(hasNewWantAdopt);
    }
}
