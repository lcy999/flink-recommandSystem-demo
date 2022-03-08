package com.demo.dao;


import com.demo.domain.HUnionProdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HUnionProdDao {

    HUnionProdEntity selectById(@Param("id") String id);
    List<HUnionProdEntity> selectByIdAndType(@Param("id") String id, @Param("unionType") String unionType);


}
