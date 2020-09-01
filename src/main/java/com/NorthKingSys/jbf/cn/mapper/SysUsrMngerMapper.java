package com.NorthKingSys.jbf.cn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface SysUsrMngerMapper {
   List<Map> selectUsr(@Param("username")String username);
   int insertUser(@Param("username")String username,@Param("backup")String backup,
                  @Param("transRole")String  transRole,@Param("userId")String userId
                  );
   int insertPassWord(@Param("userId")String userId,@Param("username")String username,
                      @Param("password")String password);
}
