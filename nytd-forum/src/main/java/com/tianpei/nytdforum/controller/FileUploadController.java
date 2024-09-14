package com.tianpei.nytdforum.controller;

import com.tianpei.nytdforum.pojo.Result;
import com.tianpei.nytdforum.utils.QiniuUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件的内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.indexOf("."));
        String key = QiniuUtil.uploadBytes(suffix, file.getInputStream());
        String url = QiniuUtil.getDownloadUrl(key, 3600);
        return Result.success(url);
    }
}
