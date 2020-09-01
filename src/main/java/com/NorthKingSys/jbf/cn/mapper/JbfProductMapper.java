package com.NorthKingSys.jbf.cn.mapper;

import com.NorthKingSys.jbf.cn.domain.JbfProduct;

public interface JbfProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JbfProduct record);

    int insertSelective(JbfProduct record);

    JbfProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JbfProduct record);

    int updateByPrimaryKey(JbfProduct record);
}