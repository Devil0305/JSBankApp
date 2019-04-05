package com.pukanghealth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppUserCouponDO;
import com.pukanghealth.entity.AppUserCouponEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券和用户中间表
 * 
 * @author wangli
 * @email 
 * @date 2019-04-03 14:24:53
 */
@Mapper
public interface AppUserCouponDao extends BaseMapper<AppUserCouponEntity> {
    List<AppUserCouponDO> myCouponListByUserId(@Param("type")Integer type, @Param("userId")String userId);
}
