package com.tianpei.nytdforum.tools;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.DownloadUrl;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;

import javax.swing.plaf.synth.Region;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static cn.hutool.core.io.FileUtil.readBytes;

public class Demo {
    public static void main(String[] args) throws QiniuException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "ig3UWFBY8XSlh4w7ghIOdRdPtFGPfP3MXreakJ2H";
        String secretKey = "aQm7wm80V3H1AEBSrzlahHMS8G6LuvG7IPKUKJFi";
        String bucket = "nytd-forum";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString() + ".jpg";

        // 本地文件路径
        String localFilePath = "/Users/wyatt/Downloads/pexels-pixabay-270404.jpg";

        // 读取文件内容到字节数组
        byte[] uploadBytes = null;
        uploadBytes = readBytes(localFilePath);
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(byteInputStream, key, upToken, null, null);
            MyPutRet myPutRet=response.jsonToObject(MyPutRet.class);
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

        String domain = "cdn.jtp26.vip";
        Boolean useHttps = false;
        DownloadUrl url = new DownloadUrl(domain, useHttps, key);
        // 带有效期
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        long deadline = System.currentTimeMillis()/1000 + expireInSeconds;
        String urlString = null;
        try {
            urlString = url.buildURL(auth, deadline);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        System.out.println(urlString);


    }

}
@Data
class MyPutRet {
    public String key; // 文件保存的 key
    public String hash; // 文件保存的 Etag
    public String bucket; // 文件保存的 bucket
    public long fsize; // 文件的大小，单位：B
}
