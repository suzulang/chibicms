package com.tianpei.nytdforum.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tianpei.nytdforum.pojo.MyPutRet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static cn.hutool.core.io.FileUtil.readBytes;

public class QiniuUtil {
    private static String accessKey;
    private static String secretKey;
    private static String bucket;
    private static String domain;
    private static Boolean useHttps;
    private static Auth auth;
    private static UploadManager uploadManager;
    // 私有构造器防止实例化
    private QiniuUtil() {}

    public static void initialize(String accessKey, String secretKey, String bucket, String domain, Boolean useHttps) {
        QiniuUtil.accessKey = accessKey;
        QiniuUtil.secretKey = secretKey;
        QiniuUtil.bucket = bucket;
        QiniuUtil.domain = domain;
        QiniuUtil.useHttps = useHttps;
        Configuration cfg = new Configuration(Zone.zone0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        QiniuUtil.auth = Auth.create(accessKey, secretKey);
        QiniuUtil.uploadManager = new UploadManager(cfg);
    }

    public static String uploadBytes(String suffix,InputStream inputStream) {
        MyPutRet myPutRet = null;
        String key = UUID.randomUUID() + suffix;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            myPutRet=response.jsonToObject(MyPutRet.class);
            myPutRet.bucket = bucket;
            System.out.println(myPutRet);
        } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
                System.err.println(ex.response);
                try {
                    String body = ex.response.toString();
                    System.err.println(body);
                } catch (Exception ignored) {
                }
            }
        }
        return myPutRet.key;
    }

    public static String getDownloadUrl(String key, long expireInSeconds) {
        DownloadUrl url = new DownloadUrl(domain, useHttps, key);
        long deadline = System.currentTimeMillis()/1000 + expireInSeconds;
        String urlString = null;
        try {
            urlString = url.buildURL(auth, deadline);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return urlString;
    }
}
