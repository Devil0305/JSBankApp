package com.pukanghealth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.ao.LoginAO;
import com.pukanghealth.common.exception.RRException;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;
import com.pukanghealth.common.validator.Assert;
import com.pukanghealth.dao.AppUserDao;
import com.pukanghealth.entity.AppTokenEntity;
import com.pukanghealth.entity.AppUserEntity;

import com.pukanghealth.service.AppTokenService;
import com.pukanghealth.service.AppUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("appUserService")
public class AppUserServiceImpl extends ServiceImpl<AppUserDao, AppUserEntity> implements AppUserService {
    private Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);
    @Autowired
    private AppTokenService tokenService;
    @Override
    public AppUserEntity queryByMobile(String mobile) {
        return baseMapper.selectOne(new QueryWrapper<AppUserEntity>().eq("mobile", mobile));
    }

    /**
     *
     * @param form    登录表单 type: 1手机验证码登录 2账号登录
     * @return
     */
    @Override
    public Map<String, Object> login(LoginAO form) {

        logger.info("login is coming, param=".concat(form.toString()));
        String type = form.getType();
        AppUserEntity user = queryByMobile(form.getMobile());
        //未注册可以使用手机电话号码使用验证码登录
        if(null == user){
            if(!"1".equals(type)){
                throw new RRException("请先注册或使用手机验证码登录");
            }else{
                AppUserEntity saveUser = new AppUserEntity();
                saveUser.setMobile(form.getMobile());
                saveUser.setUserName(form.getMobile());
                saveUser.setCreateTime(new Date());
                this.saveOrUpdate(saveUser);
            }
            user = queryByMobile(form.getMobile());
        }else{
            //已经存在信息
            Assert.isNull(user, "手机号不存在");
            //密码错误
            if(!"1".equals(type)){
                if(StringUtils.isBlank(user.getPassword())){
                    throw new RRException("请先注册或使用手机验证码登录");
                }
                if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
                    throw new RRException("手机号或密码错误");
                }
            }
        }
        //获取登录token
        AppTokenEntity tokenEntity = tokenService.createToken(user.getUserId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

        return map;
    }

    @Override
    public boolean updateMobileByUserId(String moblie, Long userId) {
        return this.baseMapper.updateMobileByUserId(moblie,userId);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppUserEntity> page = this.page(
                new Query<AppUserEntity>().getPage(params),
                new QueryWrapper<AppUserEntity>()
        );

        return new PageUtils(page);
    }

}
