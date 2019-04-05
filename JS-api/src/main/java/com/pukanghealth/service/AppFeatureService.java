package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppFeatureEntity;


import java.util.Map;

/**
 * 首页模块显示控制
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
public interface AppFeatureService extends IService<AppFeatureEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

