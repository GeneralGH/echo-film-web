package com.yiyu.echofilmspringboot.controller;


import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Collect;
import com.yiyu.echofilmspringboot.service.ICollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yiyu
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private ICollectService collectService;

    @ApiOperation("根据userID查询收藏列表")
    @GetMapping("/getUserCollect/{id}")
    public Result getUserCollect(@PathVariable Long id) {
        return collectService.getCollectByUser(id);
    }

    @ApiOperation("新增收藏")
    @PostMapping("/addCollect")
    public Result addCollect(@RequestBody Collect collect) {
        return collectService.addCollect(collect);
    }

    @ApiOperation("取消收藏")
    @DeleteMapping("/delCollect/{id}")
    public Result delCollect(@PathVariable Long id) {
        return collectService.delCollect(id);
    }
}
