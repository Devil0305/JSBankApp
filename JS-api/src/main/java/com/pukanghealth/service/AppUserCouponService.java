package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppUserCouponDO;
import com.pukanghealth.entity.AppUserCouponEntity;

import java.util.List;
import java.util.Map;

/**
 * 优惠券和用户中间表
 *
 * @author wangli
 * @email 
 * @date 2019-04-03 14:24:53
 */
public interface AppUserCouponService extends IService<AppUserCouponEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AppUserCouponDO> myCouponListByUserId(Integer type, String userId);
}

