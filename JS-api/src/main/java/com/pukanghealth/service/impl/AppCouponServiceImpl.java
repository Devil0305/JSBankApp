package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppCouponDao;
import com.pukanghealth.entity.AppCouponEntity;
import com.pukanghealth.service.AppCouponService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appCouponService")
public class AppCouponServiceImpl extends ServiceImpl<AppCouponDao, AppCouponEntity> implements AppCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppCouponEntity> page = this.page(
                new Query<AppCouponEntity>().getPage(params),
                new QueryWrapper<AppCouponEntity>()
        );

        return new PageUtils(page);
    }

}
