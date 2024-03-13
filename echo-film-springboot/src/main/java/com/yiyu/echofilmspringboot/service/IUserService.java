package com.yiyu.echofilmspringboot.service;

import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
public interface IUserService extends IService<User> {

    Result addUser(User user);
}
