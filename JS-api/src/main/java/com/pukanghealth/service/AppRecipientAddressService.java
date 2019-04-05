package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppRecipientAddressEntity;


import java.util.Map;

/**
 * 保存地址
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppRecipientAddressService extends IService<AppRecipientAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateRecipientDeletedById(Integer[] ids);
}

