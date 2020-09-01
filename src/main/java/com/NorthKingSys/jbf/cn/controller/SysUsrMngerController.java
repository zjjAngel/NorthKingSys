package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.service.SysUsrMngerService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/SysUsrMnger")
public class SysUsrMngerController {
    @Autowired
    private SysUsrMngerService sysUsrMngerService;

    @PostMapping("/insertInfo")
    public Result<?> insertInfo(@RequestBody Map re){
     return ResultUtil.success(sysUsrMngerService.insertUser(re));
    }
}
