package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppSysProvinceEntity;


import java.util.Map;

/**
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppSysProvinceService extends IService<AppSysProvinceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

