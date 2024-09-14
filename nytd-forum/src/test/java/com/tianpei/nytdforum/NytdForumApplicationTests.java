package com.tianpei.nytdforum;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.tianpei.nytdforum.utils.QiniuUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static cn.hutool.core.io.FileUtil.readBytes;

@SpringBootTest
class NytdForumApplicationTests {
    @Test
    void contextLoads() {
//        String localFilePath = "/Users/wyatt/Desktop/test/631de810-4f77-43f8-bcf3-95cb50167e96.jpg";
//        byte[] uploadBytes = null;
//        uploadBytes = readBytes(localFilePath);
//        String key = QiniuUtil.uploadBytes(uploadBytes);
//        System.out.println(key);
//        String url = QiniuUtil.getDownloadUrl(key, 3600);
//        System.out.println(url);
    }


}
