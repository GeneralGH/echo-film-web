package com.yiyu.echofilmspringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 影片类型
 * </p>
 *
 * @author yiyu
 * @since 2024-03-12
 */
@Getter
@Setter
public class Filmtype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片类型id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型名称
     */
    private String name;


}
