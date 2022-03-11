package com.demo.dao;


import com.demo.domain.HProdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HProdDao {

    List<HProdEntity> selectById(@Param("id") String id);


}
