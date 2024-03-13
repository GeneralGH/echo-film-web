package com.yiyu.echofilmspringboot.controller;


import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.User;
import com.yiyu.echofilmspringboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    IUserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
