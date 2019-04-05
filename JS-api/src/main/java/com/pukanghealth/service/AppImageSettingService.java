package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppImageSettingEntity;


import java.util.Map;

/**
 * 图片设置
 *
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:41
 */
public interface AppImageSettingService extends IService<AppImageSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

