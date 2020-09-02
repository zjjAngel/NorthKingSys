package com.NorthKingSys.jbf.cn.biz;

import lombok.Data;

@Data
public class MenuInfo {
    private Integer id;
    private String menu_id;
    private String menu_name;
    private String menu_level;
    private String menu_level_desc;
    private String  menu_level_parent;
    private String path;
    private String  back_up;
    private String  status;
    private String checked;//是否被选中 0 选中 1未选中
}
