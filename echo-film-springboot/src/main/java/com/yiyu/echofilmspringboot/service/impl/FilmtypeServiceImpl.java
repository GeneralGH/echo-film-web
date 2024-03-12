package com.yiyu.echofilmspringboot.service.impl;

import com.yiyu.echofilmspringboot.entity.Filmtype;
import com.yiyu.echofilmspringboot.mapper.FilmtypeMapper;
import com.yiyu.echofilmspringboot.service.IFilmtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 影片类型 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Service
public class FilmtypeServiceImpl extends ServiceImpl<FilmtypeMapper, Filmtype> implements IFilmtypeService {

}
