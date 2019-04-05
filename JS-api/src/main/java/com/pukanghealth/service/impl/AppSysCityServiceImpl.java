package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppSysCityDao;
import com.pukanghealth.entity.AppSysCityEntity;
import com.pukanghealth.service.AppSysCityService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appSysCityService")
public class AppSysCityServiceImpl extends ServiceImpl<AppSysCityDao, AppSysCityEntity> implements AppSysCityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppSysCityEntity> page = this.page(
                new Query<AppSysCityEntity>().getPage(params),
                new QueryWrapper<AppSysCityEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer getCityIdByCityName(String cityName) {
        return this.list(new QueryWrapper<AppSysCityEntity>().like("city_name",cityName.substring(0, 2))).get(0).getCityId();
    }

}
