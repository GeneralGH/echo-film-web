package com.yiyu.echofilmspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 影片类型
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Getter
@Setter
@ApiModel(value = "Filmtype对象", description = "影片类型")
public class Filmtype implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("影片类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型名称")
    private String name;


}
