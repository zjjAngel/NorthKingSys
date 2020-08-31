package com.NorthKingSys.jbf.cn.controller;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.JBFErrorCode;
import com.NorthKingSys.jbf.cn.biz.Result;
import com.NorthKingSys.jbf.cn.domain.UserBack;
import com.NorthKingSys.jbf.cn.mapper.UserInfoMapper;
import com.NorthKingSys.jbf.cn.service.UserService;
import com.NorthKingSys.jbf.cn.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoMapper userInfoMapper;
    @GetMapping("/demo")
   public Result<Object> getTest(){
       return ResultUtil.success(userInfoMapper.selectByPrimaryKey(1));
   }

    /**
     * 登陆界面
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 去登录 判断登录成功
     */
    @GetMapping("/index")
    public Result<?> index(String username, String password, HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(username)) {
            throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
        if (StringUtils.isEmpty(password)){
            throw new BusinessException(JBFErrorCode.NULL_OBJ);
        }
       request.getSession().setAttribute("user", UserBack.build(username,password));
       return ResultUtil.success();
    }
    @PostMapping("/registry")
    public  Result<?> insertUser(String username, String password){
       return userService.insertUser(username,password);
    }
}
