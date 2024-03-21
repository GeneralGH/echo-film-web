package com.yiyu.echofilmspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiyu.echofilmspringboot.common.CommonPage;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Film;
import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.yiyu.echofilmspringboot.mapper.FilmMapper;
import com.yiyu.echofilmspringboot.service.IFilmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 影视 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {
    @Autowired
    FilmMapper filmMapper;

    @Override
    public Result getFilmsList(int pageNum, int pageSize, String name, String filmType, String isRecommend) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 构建查询条件
        QueryWrapper<Film> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        if (filmType != null && !filmType.isEmpty()) {
            queryWrapper.like("type_ids", "%" + filmType + "%");
        }
        if (isRecommend != null && !isRecommend.isEmpty()) {
            queryWrapper.like("is_recommend", isRecommend);
        }
        // 执行分页查询
        List<Film> films = filmMapper.selectList(queryWrapper);
        // 返回Result对象
        return Result.success(CommonPage.restPage(films));
    }

    @Override
    public Result addFilm(Film film) {
        int insert = filmMapper.insert(film);
        if (insert <= 0) {
            return Result.error("新增失败");
        }
        return Result.success();
    }

    @Override
    public Result updateFilm(Film film) {
        int i = filmMapper.updateById(film);
        if (i <= 0 ) {
            return Result.error("修改失败");
        }
        return  Result.success();
    }

    @Override
    public Result delFilm(Long id) {
        Film film = filmMapper.selectById(id);
        if (film != null) {
            filmMapper.deleteById(id);
        }
        return Result.success();
    }

    @Override
    public Result getRecommendFilms() {
        QueryWrapper<Film> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("is_recommend", 1);
        List<Film> films = filmMapper.selectList(queryWrapper);
        return Result.success(films);
    }

    @Override
    public Result addWatch(Long id) {
        Film film = filmMapper.selectById(id);
        Integer watchNumber = film.getWatchNumber();
        if (watchNumber == null) {
            watchNumber = 0;
        }
        watchNumber += 1;
        film.setWatchNumber(watchNumber);
        filmMapper.updateById(film);
        return Result.success("成功");
    }
}
