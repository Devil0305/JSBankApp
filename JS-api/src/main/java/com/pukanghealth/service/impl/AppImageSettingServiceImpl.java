package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppImageSettingDao;
import com.pukanghealth.entity.AppImageSettingEntity;
import com.pukanghealth.service.AppImageSettingService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appImageSettingService")
public class AppImageSettingServiceImpl extends ServiceImpl<AppImageSettingDao, AppImageSettingEntity> implements AppImageSettingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppImageSettingEntity> page = this.page(
                new Query<AppImageSettingEntity>().getPage(params),
                new QueryWrapper<AppImageSettingEntity>()
        );

        return new PageUtils(page);
    }

}
