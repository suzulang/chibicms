package com.tianpei.nytdforum.service.Impl;

import com.tianpei.nytdforum.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {

}
