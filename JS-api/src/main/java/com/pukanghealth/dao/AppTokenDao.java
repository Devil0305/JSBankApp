package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Token
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@Mapper
public interface AppTokenDao extends BaseMapper<AppTokenEntity> {
	
}
