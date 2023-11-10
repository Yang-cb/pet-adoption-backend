package com.ycb.mapper;

import com.ycb.entity.dto.Bulletin;
import com.ycb.entity.dto.Pet;
import com.ycb.entity.vo.response.PetAndBulVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    List<PetAndBulVO> getAll();

    List<Pet> getAllByType(String type);


    int saveBulletin(Bulletin bulletin);

    int savePet(Pet pet);
}
