package com.hq.modules.wi.controller;

import com.hq.common.utils.R;
import com.hq.modules.wi.service.UserService;
import com.hq.modules.wi.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Because of you on 2019/12/16.
 */
@RestController
@RequestMapping("/wi/user")
public class UserController {
        @Autowired
        private UserService userService;
        @Autowired
        private JwtUtils jwtUtils;
        @PostMapping("/login")
        public R login(@RequestBody Map<String,String> data) {
           /* String password = data.get("password");
            password  = DigestUtils.sha256Hex(password);
            data.put("password",password);*/
            Long userId= userService.login(data);
            if(userId==null) {
                return R.error(-1,"用户名或密码错误");
            }
            String token = jwtUtils.generateToken(userId);
            return R.ok().put("token",token).put("expire", jwtUtils.getExpire());
        }
}
