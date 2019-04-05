package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppModuleOptionEntity;

import java.util.Map;

/**
 * 
 *
 * @author wangli
 * @email 
 * @date 2019-04-04 15:10:39
 */
public interface AppModuleOptionService extends IService<AppModuleOptionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

