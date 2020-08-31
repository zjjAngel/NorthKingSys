package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.UserInfo;
import com.NorthKingSys.jbf.cn.mapper.UserInfoMapper;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

   @Autowired
   UserInfoMapper userInfoMapper;
    /**
     *
     */
    public Result<Object> insertUser(String username, String password){
        UserInfo userInfo= new UserInfo();
        userInfo.setUserid("JBF-001");
        userInfo.setUsername(username);
        userInfo.setUserrole("1");
        userInfoMapper.insertDemo(userInfo);
        return ResultUtil.success();
    }
}
