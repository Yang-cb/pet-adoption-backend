package com.ycb.mapper;

import com.ycb.entity.dto.WantAdopt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WantAdoptMapper {
    @Insert("insert into `pet-adoption`.want_adopt" +
            "(account_id, pet_id, contacts_name, contacts_location, contacts_phone, " +
            "contacts_email, contacts_wechat, text, gmt_create, gmt_modified)" +
            " values (#{accountId}, #{petId}, #{contactsName}, #{contactsLocation}, #{contactsPhone}," +
            " #{contactsEmail}, #{contactsWechat}, #{text}, #{gmtCreate}, #{gmtModified})")
    int addWantAdopt(WantAdopt wantAdopt);
}
