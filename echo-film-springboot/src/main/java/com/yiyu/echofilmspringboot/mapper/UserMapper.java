package com.yiyu.echofilmspringboot.mapper;

import com.yiyu.echofilmspringboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
