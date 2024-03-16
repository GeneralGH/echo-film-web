package com.yiyu.echofilmspringboot.service;

import com.github.pagehelper.PageInfo;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Film;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 影视 服务类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
public interface IFilmService extends IService<Film> {

    Result<List<Film>> getFilmsList(int pageNum, int pageSize, String name, String filmType, String isRecommend);

    Result addFilm(Film film);

    Result updateFilm(Film film);

    Result delFilm(Long id);
}
