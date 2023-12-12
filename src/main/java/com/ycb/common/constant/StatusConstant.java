package com.ycb.common.constant;

/**
 * 状态常量，启用或者禁用
 */
public class StatusConstant {
    // 启用
    public static final Integer ENABLE = 1;

    // 禁用
    public static final Integer DISABLE = 0;

    // 待审核
    public static final Integer PENDING_REVIEW = 0;

    // 审核通过
    public static final Integer REVIEW_PASS = 1;

    // 审核不通过
    public static final Integer REVIEW_NOT_PASS = 2;

    // 同意
    public static final Integer AGREE = 3;

    // 不同意
    public static final int DISAGREE = 4;
}