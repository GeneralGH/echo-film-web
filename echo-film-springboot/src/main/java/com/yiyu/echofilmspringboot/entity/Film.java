package com.yiyu.echofilmspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 影视
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Getter
@Setter
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片id
     */
    @TableId(value = "film_id", type = IdType.AUTO)
    private Integer filmId;

    /**
     * 影片名称
     */
    private String name;

    /**
     * 影片链接
     */
    private String filmUrl;

    /**
     * 影片信息
     */
    private String info;

    /**
     * 影片封面
     */
    private String cover;

    /**
     * 观看次数
     */
    private Integer watchNumber;

    /**
     * 影片类型
     */
    private String typeIds;

    /**
     * 是否推荐
     */
    private Integer isRecommend;

    /**
     * 导演
     */
    private String director;

    /**
     * 演员
     */
    private String actor;

    /**
     * 上映时间
     */
    private LocalDateTime releaseTime;


}
