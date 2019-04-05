package com.pukanghealth.service.impl;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;
import com.pukanghealth.entity.AppUserCouponDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.pukanghealth.dao.AppUserCouponDao;
import com.pukanghealth.entity.AppUserCouponEntity;
import com.pukanghealth.service.AppUserCouponService;


@Service("appUserCouponService")
public class AppUserCouponServiceImpl extends ServiceImpl<AppUserCouponDao, AppUserCouponEntity> implements AppUserCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppUserCouponEntity> page = this.page(
                new Query<AppUserCouponEntity>().getPage(params),
                new QueryWrapper<AppUserCouponEntity>()
        );

        return new PageUtils(page);
    }

    @Override
        public List<AppUserCouponDO> myCouponListByUserId(Integer type, String userId) {
        return this.baseMapper.myCouponListByUserId(type,userId);
    }

}
