package com.yiyu.echofilmspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Film;
import com.yiyu.echofilmspringboot.entity.User;
import com.yiyu.echofilmspringboot.mapper.UserMapper;
import com.yiyu.echofilmspringboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Result addUser(User user) {
        user.setUserPassword("123456");
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            return Result.error("新增失败");
        }
        return Result.success();
    }

    @Override
    public Result updateUsr(User user) {
        int i = userMapper.updateById(user);
        if (i <= 0) {
            return Result.error("编辑失败");
        }
        return Result.success();
    }

    @Override
    public Result delUser(Long id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            userMapper.deleteById(id);
        }
        return Result.success();
    }

    @Override
    public Result<List<User>> getUserList(int pageNum, int pageSize, String name) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("user_name", name);
        }
        // 执行分页查询
        List<User> users = userMapper.selectList(queryWrapper);
        // 返回Result对象
        return Result.success(users);
    }

    @Override
    public Result login(User user) {
        User user1 = userMapper.getUserByAccount(user.getUserAccount());
        if (user1 == null) {
            return Result.error("不存在该账号");
        } else {
            if (user.getUserPassword().equals(user1.getUserPassword())) {
                return Result.success(user1);
            } else {
                return Result.error("密码错误");
            }
        }
    }
}
