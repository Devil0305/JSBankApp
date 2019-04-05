package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppTokenDao;
import com.pukanghealth.entity.AppTokenEntity;
import com.pukanghealth.service.AppTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appTokenService")
public class AppTokenServiceImpl extends ServiceImpl<AppTokenDao, AppTokenEntity> implements AppTokenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppTokenEntity> page = this.page(
                new Query<AppTokenEntity>().getPage(params),
                new QueryWrapper<AppTokenEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    @Override
    public AppTokenEntity queryByToken(String token) {
        return this.getOne(new QueryWrapper<AppTokenEntity>().eq("token", token));
    }

    @Override
    public AppTokenEntity createToken(long userId) {
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //生成token
        String token = generateToken();

        //保存或更新用户token
        AppTokenEntity tokenEntity = new AppTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);
        this.saveOrUpdate(tokenEntity);

        return tokenEntity;
    }

    @Override
    public void expireToken(long userId){
        Date now = new Date();

        AppTokenEntity tokenEntity = new AppTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(now);
        this.saveOrUpdate(tokenEntity);
    }

    private String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
