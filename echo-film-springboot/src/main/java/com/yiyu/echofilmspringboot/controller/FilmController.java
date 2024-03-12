package com.yiyu.echofilmspringboot.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 影视 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@RestController
@RequestMapping("/film")
@Api("影视")
public class FilmController {
    @GetMapping("/getFilmList")
    @ApiOperation("列表")
    public String getList() {
        return "12";
    }
}
