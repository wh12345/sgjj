package com.hq.modules.wi.service.impl;

import com.hq.modules.wi.dao.WiUserDao;
import com.hq.modules.wi.entity.WiUserEntity;
import com.hq.modules.wi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Because of you on 2019/12/17.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private WiUserDao userDao;
    @Override
    public Long login(Map<String,String> map) {
        WiUserEntity userEntity = new WiUserEntity();
        String username = map.get("username");
        String password = map.get("password");
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity = userDao.selectOne(userEntity);
        return userEntity.getUserId();
    }
}
