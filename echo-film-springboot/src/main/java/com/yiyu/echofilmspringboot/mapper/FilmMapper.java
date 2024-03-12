package com.yiyu.echofilmspringboot.mapper;

import com.yiyu.echofilmspringboot.entity.Film;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 影视 Mapper 接口
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Mapper
public interface FilmMapper extends BaseMapper<Film> {

}
