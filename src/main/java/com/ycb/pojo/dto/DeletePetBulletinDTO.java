package com.ycb.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 删除宠物布告数据传输对象
 */
@Data
public class DeletePetBulletinDTO {
    private Integer petId;
    private Integer bulletinId;
    private LocalDateTime gmtModified;
    private Integer isDelete;

    private DeletePetBulletinDTO(Builder builder) {
        this.petId = builder.petId;
        this.bulletinId = builder.bulletinId;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Integer petId;
        private Integer bulletinId;

        public Builder petId(Integer petId) {
            this.petId = petId;
            return this;
        }

        public Builder bulletinId(Integer bulletinId) {
            this.bulletinId = bulletinId;
            return this;
        }

        public  DeletePetBulletinDTO build() {
            return new DeletePetBulletinDTO(this);
        }
    }
}
