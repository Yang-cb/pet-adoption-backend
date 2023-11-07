package com.ycb.mapper;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {
    List<Pet> getAll();

    List<Pet> getAllByType(String type);


    int saveBulletin(Bulletin bulletin);

    int savePet(Pet pet);
}
