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
 * 用户
 * </p>
 *
 * @author yiyu
 * @since 2024-03-13
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("账号")
    private String userAccount;

    @ApiModelProperty("密码-默认123456")
    private String userPassword;

    @ApiModelProperty("昵称")
    private String userName;

    @ApiModelProperty("收藏")
    private String collect;


}
