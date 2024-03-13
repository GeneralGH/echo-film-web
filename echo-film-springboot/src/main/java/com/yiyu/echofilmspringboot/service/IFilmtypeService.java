package com.yiyu.echofilmspringboot.service;

import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 影片类型 服务类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
public interface IFilmtypeService extends IService<Filmtype> {

    Result getList();

    Result addFilmType(Filmtype filmtype);

    Result updateFilmType(Filmtype filmtype);

    Result delFilmType(Long id);
}
