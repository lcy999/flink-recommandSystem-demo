package com.demo.dao;


import com.demo.domain.HUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HUserDao {

    HUserEntity selectById(@Param("id") String id);


}
