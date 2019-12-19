package com.hq.modules.wi.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@DS("oracle")
public class UserServiceImpl implements UserService{
    @Autowired
    private WiUserDao userDao;
    @Override
    public Long login(Map<String,String> map) {
        String username = map.get("username");
        String password = map.get("password");
        WiUserEntity userEntity = userDao.selectOne(new QueryWrapper<WiUserEntity>()
                            .eq("username",username)
                            .eq("username",username));
        return userEntity.getUserId();
    }
}
