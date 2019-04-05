package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.ao.AppNetworkAO;

/**
 * 网点信息
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppNetworkService extends IService<AppNetworkEntity> {
    /**
     * 查询建行网点
     * @param appNetworkAO
     * @return
     */
    PageUtils queryCCBPage(AppNetworkAO appNetworkAO);

    /**
     * 查询社保网点
     * @param appNetworkAO
     * @return
     */
    PageUtils querySocialSecurityPage(AppNetworkAO appNetworkAO);
}

