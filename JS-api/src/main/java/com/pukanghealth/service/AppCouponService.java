package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppCouponEntity;


import java.util.Map;

/**
 * 优惠券
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppCouponService extends IService<AppCouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

