package com.yiyu.echofilmspringboot.controller;


import com.github.pagehelper.PageInfo;
import com.yiyu.echofilmspringboot.common.FilmListRequest;
import com.yiyu.echofilmspringboot.common.PageRequest;
import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Film;
import com.yiyu.echofilmspringboot.service.IFilmService;
import com.yiyu.echofilmspringboot.service.IFilmtypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 影视 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private IFilmService filmService;

    @ApiOperation("分页查询影视列表")
    @PostMapping("/getFilmList")
    public Result getFilmList(@RequestBody FilmListRequest request) {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        String name = request.getName();
        String filmType = request.getFilmType();
        String isRecommend = request.getIsRecommend();
        return filmService.getFilmsList(pageNum, pageSize, name, filmType, isRecommend);
    }

    @ApiOperation("获取推荐影片")
    @GetMapping("/getRecommendFilms")
    public Result getRecommendFilms() {
        return filmService.getRecommendFilms();
    }

    @ApiOperation("新增影视")
    @PostMapping("/addFilm")
    public Result addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    @ApiOperation("修改影视")
    @PutMapping("/updateFilm")
    public Result updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film);
    }

    @ApiOperation("删除影视")
    @DeleteMapping("/delFilm/{id}")
    public Result delFilm(@PathVariable Long id) {
        return filmService.delFilm(id);
    }

    @ApiOperation("增加观看量")
    @GetMapping("/addWatch/{id}")
    public Result addWatch(@PathVariable Long id) {
        return filmService.addWatch(id);
    }
}
