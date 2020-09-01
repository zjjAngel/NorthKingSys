package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.mapper.SysUsrMngerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SysUsrMngerService {
    @Autowired
    SysUsrMngerMapper sysUsrMngerMapper;

    @Transactional
    public Object insertUser(Map re){
        //查看用户名 是否被占用
        List<Map> maps = sysUsrMngerMapper.selectUsr(String.valueOf(re.get("username")));
        Optional<List<Map>> mapsOpt = Optional.of(maps);
        if(!mapsOpt.isPresent()){
            throw new BusinessException(-1,"该用户已名已经被占用");
        }

      int result=  sysUsrMngerMapper.insertUser(String.valueOf(re.get("username")),
                String.valueOf(re.get("backup")),String.valueOf(re.get("transRole")),
                String.valueOf(re.get("userId")));
        sysUsrMngerMapper.insertPassWord(String.valueOf(re.get("userId")),
                String.valueOf(re.get("username")),String.valueOf(re.get("password")));
        //刚设置的账号默认三天内有效
        return result;
   }
}
