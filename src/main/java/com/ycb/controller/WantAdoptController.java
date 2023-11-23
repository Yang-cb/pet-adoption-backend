package com.ycb.controller;

import com.ycb.entity.RestBean;
import com.ycb.entity.dto.WantAdopt;
import com.ycb.service.WantAdoptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WantAdoptController
 */
@RestController
@RequestMapping("/api/wantAdopt")
public class WantAdoptController {
    @Resource
    private WantAdoptService wantAdoptService;

    @PostMapping("/addWantAdopt")
    public RestBean<String> addWantAdopt(@RequestBody WantAdopt wantAdopt) {
        String msg = wantAdoptService.addWantAdopt(wantAdopt);
        return msg == null ? RestBean.success(null) : RestBean.failure(400, msg);
    }
}
