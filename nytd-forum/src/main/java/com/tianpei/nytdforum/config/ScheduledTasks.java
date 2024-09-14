package com.tianpei.nytdforum.config;

import com.tianpei.nytdforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    UserService userService;
    public void updateAllUrl() {
        // 更新所有头像的url
    }
}
