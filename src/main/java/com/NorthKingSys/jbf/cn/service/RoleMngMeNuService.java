package com.NorthKingSys.jbf.cn.service;

import com.NorthKingSys.jbf.cn.biz.BusinessException;
import com.NorthKingSys.jbf.cn.biz.MenuInfo;
import com.NorthKingSys.jbf.cn.domain.RoleInfo;
import com.NorthKingSys.jbf.cn.mapper.MenuMngerMapper;
import com.NorthKingSys.jbf.cn.mapper.RoleMngMeNuMapper;
import com.NorthKingSys.jbf.cn.util.SnowflakeIdWorkerUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RoleMngMeNuService {
    @Autowired
   private RoleMngMeNuMapper roleMngMeNuMapper;
    @Autowired
    private MenuMngerMapper menuMngerMapper;
   public Object insertRoleMngMeNuInfo(Map re){
       String role_id = SnowflakeIdWorkerUntil.roleIdNextId();
       String role_name = String.valueOf(re.get("ROLE_NAME"));
       String back_up = String.valueOf(re.get("BACK_UP"));
       String feign_ids = String.valueOf(re.get("FEIGN_IDS"));//menuIds
       if(feign_ids.contains(",")){
           return  roleMngMeNuMapper.insertRoleMenu(role_id,role_name,back_up,feign_ids);
       }else if(feign_ids.split(",").length==1){
           return  roleMngMeNuMapper.insertRoleMenu(role_id,role_name,back_up,feign_ids);
       }else {
           throw new BusinessException("FEIGN_IDS 该字段为非法字段，必须用逗号相隔");
       }

   }

    public Object modifyRoleMngMeNuInfo(Map re){
        String role_id =String.valueOf(re.get("role_id"));
        String role_name = String.valueOf(re.get("ROLE_NAME"));
        String back_up = String.valueOf(re.get("BACK_UP"));
        String feign_ids = String.valueOf(re.get("FEIGN_IDS"));//menuIds 用逗号分隔
        return roleMngMeNuMapper.modifyRoleMngMeNuInfo(role_id,role_name,back_up,feign_ids);
    }

   public Object queryRoleMngMenuInfo(String roleName,String pageNum,String pageSize){
       PageHelper.startPage(Integer.valueOf(pageNum),Integer.valueOf(pageSize));
       List<RoleInfo> roleInfos = roleMngMeNuMapper.queryRoleMngMenuInfo(roleName);
       PageInfo<RoleInfo> pageInfo= new PageInfo<>(roleInfos);
       return pageInfo;
   }

    /**
     * 数据结构无法确定
     * @param role_Id
     * @return
     */
//   public  Object queryRoleEditMenuInfo( String role_id){
//        Map res=new HashMap();
//       //通过角色id 去查询该角色已经分配了的菜单
//       List<RoleInfo> roleInfos = roleMngMeNuMapper.queryRoleEditMenuInfo(role_id);
//       if(roleInfos.size()==1){
//         List<String> roles=  Arrays.asList(roleInfos.get(0).getFEIGN_IDS().split(","));// 分配的菜单ids
//         List<MenuInfo> allMenus=menuMngerMapper.queryAllMenu(null);//按照等级查询
//         List<MenuInfo>  levelInfos=   menuMngerMapper.queryAllMenuLevel();//查询所有级别
//           levelInfos.stream().forEach(e->{
//               String  currLevel=e.getMenu_level();
//               List<MenuInfo> menuInfos = menuMngerMapper.queryAllMenu(currLevel);
//               for (MenuInfo mu: menuInfos) {
//                   List<String> collect = roles.stream().filter(Predicate.isEqual(mu.getMenu_id())).collect(Collectors.toList());
//                   if (null!=collect&&collect.size()>0){
//                       mu.setChecked("0");
//                   }
//               };
//               res.put(currLevel,menuInfos);
//           });
//         //标记已分配菜单
//       }else if(roleInfos.size()>=1){
//         throw new BusinessException("异常数据");
//       }else {
//         throw new BusinessException("异常数据");
//       }
//   }

   public Object queryMenuByRoleId(String role_Id){
       List<String> menuInfos= roleMngMeNuMapper.selectFein_ids(role_Id);//查询出所有菜单的id
       List<String> sortMnInfos= menuInfos.stream().sorted().collect(Collectors.toList());
       return sortMnInfos;
   }

   public Object queryMenuByCurrentLevel(String level){
     return    roleMngMeNuMapper.selectCurrentLevel(level);
   }

   public Object queryMenuByNextLevel(String level,String menu_Id){
      return roleMngMeNuMapper.queryMenuByNextLevel(String.valueOf(Integer.valueOf(level)+1),menu_Id);
   }

}
