package com.ycb.common.result;

import lombok.Data;

import java.util.List;

/**
 * 分页结果
 */
@Data
public class PageResult {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页的数据集合
     */
    private List<?> records;

    private PageResult(Builder builder) {
        this.total = builder.total;
        this.records = builder.records;
    }

    /**
     * 静态方法，返回构造器
     * @return 构造器
     */
    public static Builder builder() {
        return new Builder();
    }

    // 构造器
    public static class Builder {
        private Long total;
        private List<?> records;

        public Builder total(Long total) {
            this.total = total;
            return this;
        }

        public Builder records(List<?> records) {
            this.records = records;
            return this;
        }

        public PageResult build() {
            return new PageResult(this);
        }
    }
}
