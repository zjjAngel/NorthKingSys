package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.biz.MenuInfo;
import com.NorthKingSys.jbf.cn.domain.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMngMeNuMapper {
  int  insertRoleMenu(@Param("role_id")String role_id,@Param("role_name") String role_name,
                      @Param("back_up")String back_up,@Param("feign_ids") String feign_ids);

    List<String> selectFein_ids(String role_id);

    List<MenuInfo> selectCurrentLevel(@Param("level")String level);

    List<MenuInfo>  queryMenuByNextLevel(@Param("level")String level,@Param("menu_Id")String menu_Id);
    List<RoleInfo> queryRoleMngMenuInfo(@Param("roleName")String roleName);
    List<RoleInfo> queryRoleEditMenuInfo(@Param("role_id")String role_id);
//     List<MenuInfo>   queryAllMenu();
    List<RoleInfo> modifyRoleMngMeNuInfo(@Param("role_id")String role_id,@Param("roleName")String role_name,
                                         @Param("back_up")String back_up, @Param("feign_ids")String feign_ids);
}
