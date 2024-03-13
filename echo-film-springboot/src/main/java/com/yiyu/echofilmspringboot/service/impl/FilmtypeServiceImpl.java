package com.yiyu.echofilmspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.yiyu.echofilmspringboot.mapper.FilmtypeMapper;
import com.yiyu.echofilmspringboot.service.IFilmtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 影片类型 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Service
public class FilmtypeServiceImpl extends ServiceImpl<FilmtypeMapper, Filmtype> implements IFilmtypeService {
    @Autowired
    FilmtypeMapper filmtypeMapper;

    @Override
    public Result getList() {
        LambdaQueryWrapper<Filmtype> queryWrapper = new LambdaQueryWrapper<>();
        List<Filmtype> filmtypes = filmtypeMapper.selectList(null);
        return Result.success(filmtypes);
    }

    @Override
    public Result addFilmType(Filmtype filmtype) {
        int insert = filmtypeMapper.insert(filmtype);
        if (insert <= 0) {
            return Result.error("新增失败");
        }
        return Result.success();
    }

    @Override
    public Result updateFilmType(Filmtype filmtype) {
        int i = filmtypeMapper.updateById(filmtype);
        if (i <= 0 ) {
            return Result.error("修改失败");
        }
        return  Result.success();
    }

    @Override
    public Result delFilmType(Long id) {
        Filmtype filmtype = filmtypeMapper.selectById(id);
        if (filmtype != null) {
            filmtypeMapper.deleteById(id);
        }
        return Result.success();
    }
}
