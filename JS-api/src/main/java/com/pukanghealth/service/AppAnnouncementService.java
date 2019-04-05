package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppAnnouncementEntity;

import java.util.Map;

/**
 * 社保一键通通知公告
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppAnnouncementService extends IService<AppAnnouncementEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

