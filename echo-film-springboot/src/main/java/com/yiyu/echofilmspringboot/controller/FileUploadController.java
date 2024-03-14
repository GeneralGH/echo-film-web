package com.yiyu.echofilmspringboot.controller;

import cn.hutool.core.io.FileUtil;
import com.yiyu.echofilmspringboot.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    private String Path = "uploads/";
    private String IP = "http://localhost:8090";

    /**
     * 文件上传
     * @param file
     * @param
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<Map> fileUpload(MultipartFile file) throws IOException {
        //下载接口路径
        String download = "/file/download/";

        String originalFilename = file.getOriginalFilename(); //文件原始全名

        String mainName = FileUtil.mainName(originalFilename); //文件前缀
        String extName = FileUtil.extName(originalFilename); // 文件后缀
        String fileNewName = mainName + "." + extName;//新的文件名
        String fileRealPath = Path  + fileNewName; //文件保存路径
        File saveFile = new File(fileRealPath);
        //判断文件夹目录是否存在
        if (FileUtil.exist(saveFile)) {
            saveFile.getParentFile().mkdirs();
        }
        file.transferTo(saveFile); //存储到硬盘
        String url = IP + download + fileNewName;

        Map map = new HashMap();
        map.put("fileName", fileNewName);
        map.put("url", url);
        map.put("filePath", fileRealPath);
        map.put("originalFilename", originalFilename);
        return Result.success(map);
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public void fileDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String filePath = Path + fileName;
        if (!FileUtil.exist(filePath)) {
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
