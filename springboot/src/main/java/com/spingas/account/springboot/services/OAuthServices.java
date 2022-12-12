package com.spingas.account.springboot.services;

import com.spingas.account.springboot.entity.User;
import com.spingas.account.springboot.utils.AESUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class OAuthServices {

    @Autowired
    UserServices userServices;

    @Autowired
    RedisTemplate<String,String> redis;

    public Response login(User user){
        if (user.getEmail()!=null && user.getPwd()!=null) {
            List<User> list = userServices.auto(user);
            if (list.size()!=0){
                User content = list.get(0);
                String tiemstamp = new Date().getTime()+"";
                String cipher = AESUtils.encrypt(tiemstamp);
                redis.opsForValue().set(tiemstamp,content.getId().toString(),30, TimeUnit.DAYS);
                return new Response(true,cipher);
            }
            return new Response(false,"邮箱或密码错误");
        }
        return new Response(false,"参数错误");
    }

    public Object decrypt(String cipher){
        String key;
        int id;
        try {
            key =  AESUtils.decrypt(cipher);
            id = Integer.parseInt(Objects.requireNonNull(redis.opsForValue().get(key)));
        }catch (Exception e){
            return new Response(false,"登陆已过期");
        }

        List<User> list = userServices.auto(new User(id));
        if (list.size()!=0){
            return new SuccessRes(true,"success",list.get(0));
        }

        return new Response(false,"账号不存在");
    }

    @Data
    class Response{
        boolean state;
        String message;
        public Response(boolean state, String message){
            this.state = state;
            this.message = message;
        }

        public Response(){}
    }

    @Data
    class SuccessRes extends Response{
        User user;
        public SuccessRes(boolean state, String message,User user){
            this.state = state;
            this.user = user;
            this.message = message;
        }
    }
}
