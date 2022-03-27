package com.demo.dao;

import com.demo.domain.ContactEntity;
import com.demo.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    UserEntity selectById(@Param("userId") int userId);
}
