package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppTokenEntity;

import java.util.Map;

/**
 * 用户Token
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
public interface AppTokenService extends IService<AppTokenEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AppTokenEntity queryByToken(String token);

    /**
     * 生成token
     * @param userId  用户ID
     * @return        返回token信息
     */
    AppTokenEntity createToken(long userId);

    /**
     * 设置token过期
     * @param userId 用户ID
     */
    void expireToken(long userId);
}

