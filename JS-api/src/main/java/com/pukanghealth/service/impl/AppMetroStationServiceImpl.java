package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppMetroStationDao;
import com.pukanghealth.entity.AppMetroStationEntity;
import com.pukanghealth.service.AppMetroStationService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appMetroStationService")
public class AppMetroStationServiceImpl extends ServiceImpl<AppMetroStationDao, AppMetroStationEntity> implements AppMetroStationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppMetroStationEntity> page = this.page(
                new Query<AppMetroStationEntity>().getPage(params),
                new QueryWrapper<AppMetroStationEntity>()
        );

        return new PageUtils(page);
    }

}
