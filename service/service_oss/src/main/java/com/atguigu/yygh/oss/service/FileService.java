package com.atguigu.yygh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author
 * @date 2022/5/5 16:27
 */
public interface FileService {
    String upload(MultipartFile file);
}
