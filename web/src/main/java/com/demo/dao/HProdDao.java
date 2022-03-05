package com.demo.dao;


import com.demo.domain.HProdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HProdDao {

    HProdEntity selectById(@Param("id") String id);


}
