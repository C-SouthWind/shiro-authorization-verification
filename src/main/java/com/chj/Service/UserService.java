package com.chj.Service;

import com.chj.mapper.UserMapper;
import com.chj.model.User;
import com.chj.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 11:20
 * @params :
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据姓名查询所有用户
     * @param username
     * @return
     */
    public User selectUserByName(String username){
        return userMapper.selectUserByName(username);
    }

    /**
     * 根据姓名查询权限
     * @param username
     * @return
     */
    public List<UserVo> selectRoleByUserName(String username){
        return userMapper.selectRoleByUserName(username);
    }
}
