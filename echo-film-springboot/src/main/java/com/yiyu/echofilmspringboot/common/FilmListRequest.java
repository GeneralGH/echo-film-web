package com.yiyu.echofilmspringboot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "影视列表请求参数")
@Data
public class FilmListRequest extends PageRequest {
    @ApiModelProperty(value = "影视名称（可选）")
    private String name;

    @ApiModelProperty(value = "影视类型（可选）字符串形式，数字用,隔开")
    private String filmType;

    @ApiModelProperty(value = "是否推荐 1推荐 2不推荐")
    private String isRecommend;
}
