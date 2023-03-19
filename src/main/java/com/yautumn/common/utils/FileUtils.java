package com.yautumn.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileUtils {

    public ResultUtil upload(MultipartFile file,String fileUploadPath,int shopId) {
        // 1、获取文件的原始文件名, 通过原始文件名获取文件后缀 例如：abc.jpg
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 2、重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = DateUtils.dateFormatToString(new Date())+ String.valueOf(shopId) + suffix;

        // 3、创建一个目录对象
        File dir = new File(fileUploadPath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            // 4、将临时文件转存到指定位置
            file.transferTo(new File(fileUploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.success(fileName);
    }
}
