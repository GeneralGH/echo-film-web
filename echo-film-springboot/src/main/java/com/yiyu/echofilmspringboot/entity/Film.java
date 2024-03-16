package com.yiyu.echofilmspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 影视
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Getter
@Setter
@ApiModel(value = "Film对象", description = "影视")
public class Film implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("影片id")
    @TableId(value = "film_id", type = IdType.AUTO)
    private Integer filmId;

    @ApiModelProperty("影片名称")
    private String name;

    @ApiModelProperty("影片链接")
    private String filmUrl;

    @ApiModelProperty("影片信息")
    private String info;

    @ApiModelProperty("影片封面")
    private String cover;

    @ApiModelProperty("观看次数")
    private Integer watchNumber;

    @ApiModelProperty("影片类型")
    private String typeIds;

    @ApiModelProperty("是否推荐")
    private Integer isRecommend;

    @ApiModelProperty("导演")
    private String director;

    @ApiModelProperty("演员")
    private String actor;

    @ApiModelProperty("上映时间")
    private Date releaseTime;


}
