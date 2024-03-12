package com.yiyu.echofilmspringboot.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generater {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/echo_film?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("yiyu") // 设置作者
                            .dateType(DateType.TIME_PACK)//时间策略
                            .commentDate("yyyy-MM-dd")//注释日期
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.yiyu.echofilmspringboot") // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(getTables("all")) // 设置需要生成的表名
                            .addTablePrefix("tbl_") // 设置过滤表前缀
                            .controllerBuilder()
                            .enableRestStyle()//开启restful风格
                            .entityBuilder()
                            .enableLombok()//开启 Lombok
                            .mapperBuilder()
                            .enableMapperAnnotation();//开启 @Mapper
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}