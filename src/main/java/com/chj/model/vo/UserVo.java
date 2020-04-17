package com.chj.model.vo;

import java.io.Serializable;

/**
 * @author ：chj
 * @date ：Created in 2020/4/17 16:33
 * @params :
 */
public class UserVo implements Serializable {
    private Integer uid;

    private String username;

    private String password;

    private String rolename;

    public UserVo(Integer uid, String username, String password, String rolename) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.rolename = rolename;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
