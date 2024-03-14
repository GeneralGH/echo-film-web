package com.yiyu.echofilmspringboot.controller;


import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.common.UserListRequest;
import com.yiyu.echofilmspringboot.entity.User;
import com.yiyu.echofilmspringboot.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private IUserService userService;

    @ApiOperation("获取用户列表")
    @PostMapping("/getUserList")
    public Result<List<User>> getUserList(@RequestBody UserListRequest request) {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        String name = request.getName();
        return userService.getUserList(pageNum, pageSize, name);
    }

    @ApiOperation("新增用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @ApiOperation("编辑用户")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {
        return userService.updateUsr(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/delUser/{id}")
    public Result delUser(@PathVariable Long id) {
        return userService.delUser(id);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }
}
