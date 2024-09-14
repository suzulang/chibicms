package com.tianpei.nytdforum.service.Impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.tianpei.nytdforum.mapper.UserMapper;
import com.tianpei.nytdforum.pojo.User;
import com.tianpei.nytdforum.service.UserService;
import com.tianpei.nytdforum.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = DigestUtil.md5Hex(password);
        //添加
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(DigestUtil.md5Hex(newPwd),id);
    }
}
