package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppSysProvinceDao;
import com.pukanghealth.entity.AppSysProvinceEntity;
import com.pukanghealth.service.AppSysProvinceService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appSysProvinceService")
public class AppSysProvinceServiceImpl extends ServiceImpl<AppSysProvinceDao, AppSysProvinceEntity> implements AppSysProvinceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSysProvinceEntity> page = this.page(
                new Query<AppSysProvinceEntity>().getPage(params),
                new QueryWrapper<AppSysProvinceEntity>()
        );

        return new PageUtils(page);
    }

}
