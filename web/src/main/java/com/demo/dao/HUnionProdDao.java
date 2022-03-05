package com.demo.dao;


import com.demo.domain.HUnionProdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HUnionProdDao {

    HUnionProdEntity selectById(@Param("id") String id);


}
