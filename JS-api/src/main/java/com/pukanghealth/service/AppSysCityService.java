package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppSysCityEntity;


import java.util.Map;

/**
 * 城市原始表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppSysCityService extends IService<AppSysCityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer getCityIdByCityName(String cityName);
}

