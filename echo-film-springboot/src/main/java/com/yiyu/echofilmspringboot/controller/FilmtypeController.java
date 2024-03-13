package com.yiyu.echofilmspringboot.controller;


import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.yiyu.echofilmspringboot.service.IFilmtypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 影片类型 前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/filmtype")
public class FilmtypeController {
    @Autowired
    private IFilmtypeService filmtypeService;

    @GetMapping("/getFilmTypeList")
    @ApiOperation("列表")
    public Result getList() {
        return filmtypeService.getList();
    }

    @PostMapping("/addFilmType")
    @ApiOperation("新增")
    public Result add(@RequestBody Filmtype filmtype) {
        return filmtypeService.addFilmType(filmtype);
    }

    @PutMapping("/updateFilmType")
    @ApiOperation("编辑")
    public Result update(@RequestBody Filmtype filmtype) {
        return filmtypeService.updateFilmType(filmtype);
    }

    @DeleteMapping("/delFilmType/{id}")
    @ApiOperation("删除")
    public Result del(@PathVariable Long id) {
        return filmtypeService.delFilmType(id);
    }
}
