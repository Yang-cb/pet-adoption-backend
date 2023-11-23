package com.ycb.service;

import com.ycb.entity.dto.WantAdopt;

public interface WantAdoptService {
    /**
     * 添加想领
     * @param wantAdopt 想领信息
     * @return 错误信息
     */
    String addWantAdopt(WantAdopt wantAdopt);
}
