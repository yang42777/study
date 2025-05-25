package com.example.ecommerce.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ecommerce.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findByUsername(String userName);
//
//    void insert(User entity);
//
//    List<User> selectAll();
}