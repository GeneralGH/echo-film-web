package com.yiyu.echofilmspringboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用户列表请求参数")
@Data
public class UserListRequest extends PageRequest {
    @ApiModelProperty(value = "用户名")
    private String name;
}
