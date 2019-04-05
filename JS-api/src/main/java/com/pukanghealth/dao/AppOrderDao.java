package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppOrderEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:40
 */
@Mapper
public interface AppOrderDao extends BaseMapper<AppOrderEntity> {
	
}
