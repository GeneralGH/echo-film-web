package com.yiyu.echofilmspringboot.service.impl;

import com.yiyu.echofilmspringboot.entity.User;
import com.yiyu.echofilmspringboot.mapper.UserMapper;
import com.yiyu.echofilmspringboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
