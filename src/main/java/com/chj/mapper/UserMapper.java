package com.chj.mapper;

import com.chj.model.User;
import com.chj.model.vo.UserVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectUserByName(String username);

    List<UserVo> selectRoleByUserName(String username);

}