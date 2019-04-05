package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppMetroLineEntity;


import java.util.Map;

/**
 * 
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppMetroLineService extends IService<AppMetroLineEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Map<String,Object> getMetroStationsByCity(int city);

}

