package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppOrderEntity;


import java.util.Map;

/**
 * 订单
 *
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:40
 */
public interface AppOrderService extends IService<AppOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

