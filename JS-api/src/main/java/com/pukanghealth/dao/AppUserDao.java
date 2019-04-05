package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * app端用户表
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@Mapper
public interface AppUserDao extends BaseMapper<AppUserEntity> {
    boolean updateMobileByUserId(@Param("mobile")String mobile,@Param("userId")Long userId);
}
