package com.demo.dao;


import com.demo.domain.HLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HLogDao {

    HLogEntity selectById(@Param("id") String id);


}
