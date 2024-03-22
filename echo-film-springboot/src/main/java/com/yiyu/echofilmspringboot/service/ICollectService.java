package com.yiyu.echofilmspringboot.service;

import com.yiyu.echofilmspringboot.common.Result;
import com.yiyu.echofilmspringboot.entity.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yiyu
 * @since 2024-03-22
 */
public interface ICollectService extends IService<Collect> {

    Result getCollectByUser(Long id);

    Result addCollect(Collect collect);

    Result delCollect(Long id);
}
