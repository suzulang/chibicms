package com.tianpei.nytdforum.pojo;

import lombok.Data;

@Data
public
class MyPutRet {
    public String key; // 文件保存的 key
    public String hash; // 文件保存的 Etag
    public String bucket; // 文件保存的 bucket
}
