package com.wut.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wut.common.model.entity.User;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author zeng1998
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request 请求对象
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     * @param request 请求对象
     * @return 用户对象
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     * @param request 请求对象
     * @return boolean
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request 请求对象
     * @return 注销是否成功
     */
    boolean userLogout(HttpServletRequest request);
}
