package com.tianpei.nytdforum.tools;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.mail.MailUtil;

public class Test {
    public static void main(String[] args) {
        String s = MailUtil.send("suzulang.jiang@gmail.com", "测试", "邮件来自Hutool测试", false);
        System.out.println(s);
    }
}
