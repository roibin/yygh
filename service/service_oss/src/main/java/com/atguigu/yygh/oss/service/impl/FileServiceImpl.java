package com.atguigu.yygh.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.atguigu.yygh.oss.service.FileService;
import com.atguigu.yygh.oss.utils.ConstantOssPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author
 * @date 2022/5/5 16:27
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        String endpoint = ConstantOssPropertiesUtils.ENDPOINT;
        String accessKeyId = ConstantOssPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantOssPropertiesUtils.SECRET;
        String bucket = ConstantOssPropertiesUtils.BUCKET;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //上传文件流
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename= uuid + filename;

            //按照日期创建文件夹
            String timeUrl = new DateTime().toString("yyyy/MM/dd");
            filename = timeUrl + "/" + filename;

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucket, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //返回上传后文件路径
            String url = "https://" + bucket + "." + endpoint + "/" + filename;

            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
