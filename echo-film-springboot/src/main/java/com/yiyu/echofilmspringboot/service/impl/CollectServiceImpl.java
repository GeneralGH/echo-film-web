package com.yiyu.echofilmspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Collect;
import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.yiyu.echofilmspringboot.mapper.CollectMapper;
import com.yiyu.echofilmspringboot.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-22
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
    @Autowired
    CollectMapper collectMapper;
    @Override
    public Result getCollectByUser(Long id) {
        LambdaQueryWrapper<Collect> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Collect::getUserId, id);
        List<Collect> collects = collectMapper.selectList(queryWrapper);
        return Result.success(collects);
    }

    @Override
    public Result addCollect(Collect collect) {
        int insert = collectMapper.insert(collect);
        if (insert <= 0) {
            return Result.error("失败");
        }
        // 获取新增数据的主键值
        Long collectId = Long.valueOf(collect.getId());

        // 根据主键值查询新增的数据
        Collect newCollect = collectMapper.selectById(collectId);
        return Result.success(newCollect);
    }

    @Override
    public Result delCollect(Long id) {
        int i = collectMapper.deleteById(id);
        if (i <= 0) {
            return Result.error("删除失败");
        }
        return Result.success("成功");
    }
}
