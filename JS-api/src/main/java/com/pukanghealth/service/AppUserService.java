package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.ao.LoginAO;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppUserEntity;



import java.util.Map;

/**
 * app端用户表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
public interface AppUserService extends IService<AppUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    AppUserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     * @param form    登录表单
     * @return        返回登录信息
     */
    Map<String, Object> login(LoginAO form);

    boolean updateMobileByUserId(String moblie,Long userId);
}

