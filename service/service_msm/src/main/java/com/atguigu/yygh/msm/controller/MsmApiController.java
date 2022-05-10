package com.atguigu.yygh.msm.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.msm.service.MsmService;
import com.atguigu.yygh.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author
 * @date 2022/5/4 20:06
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private MsmService msmService;

    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取到，返回ok
        //key手机号  value验证码
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //如果从redis获取不到，通过调用service方法，整合短信服务进行发送
        code = RandomUtil.getSixBitRandom();
        boolean isSend = msmService.send(phone,code);

        //生成的验证码放到redis中，设置有效时间
        if(isSend){
            redisTemplate.opsForValue().set(phone,code,2, TimeUnit.MINUTES);
            return Result.ok();
        }else{
            return Result.fail().message("发送短信失败");
        }
    }
}
