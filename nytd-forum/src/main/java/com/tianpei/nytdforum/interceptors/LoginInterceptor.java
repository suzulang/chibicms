package com.tianpei.nytdforum.interceptors;

import com.tianpei.nytdforum.utils.JwtUtil;
import com.tianpei.nytdforum.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // 所有请求进来前，拦截处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token =request.getHeader("Authorization");
        try {
            //从redis中获取相同的token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token);
            if (redisToken==null){
                //token已经失效了
                throw new RuntimeException();
            }
            Map<String, Object> map = JwtUtil.parseToken(token);
            //解析出来的map集合，放到ThreadLocal里面，方便在这个请求的生命周期内，共享参数
            ThreadLocalUtil.set(map);
            //能解析出来，没抛出异常，说明请求头里有JWT令牌,放行请求
            return true;
        }catch (Exception e) {
            //如果抛出异常了，则拦截请求，设置http状态码为401
            response.setStatus(401);
            return false;
        }
    }
    //请求结束后，处理一下ThreadLocal
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }

}
