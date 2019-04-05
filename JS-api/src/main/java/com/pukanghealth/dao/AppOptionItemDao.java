package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppOptionItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 所有下拉框原始表
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Mapper
public interface AppOptionItemDao extends BaseMapper<AppOptionItemEntity> {
    List<AppOptionItemEntity> getOptionItemsByParam(String groupId, String itemValue);
	
}
