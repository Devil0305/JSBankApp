package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppMetroStationEntity;


import java.util.Map;

/**
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppMetroStationService extends IService<AppMetroStationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

