package com.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RTopProductDao {

    List<String> selectTopN(@Param("rankName") String rankName);


}
