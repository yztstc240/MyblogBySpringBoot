package com.github.blog.service;

import com.github.blog.domain.User;

/**
 * 用户相关业务代码
 */
public interface UserService {

    /**
     * 登录时用户的验证
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);


}
