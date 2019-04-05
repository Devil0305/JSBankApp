package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.ao.AppMerchantAO;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppMerchantEntity;


import java.util.Map;

/**
 * 合作商户
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppMerchantService extends IService<AppMerchantEntity> {

    PageUtils queryMerchantPage(AppMerchantAO appMerchantAO);
}

