package com.yiyu.echofilmspringboot.service.impl;

import com.yiyu.echofilmspringboot.entity.Film;
import com.yiyu.echofilmspringboot.mapper.FilmMapper;
import com.yiyu.echofilmspringboot.service.IFilmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 影视 服务实现类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements IFilmService {

}
