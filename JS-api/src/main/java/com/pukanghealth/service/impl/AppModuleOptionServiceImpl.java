package com.pukanghealth.service.impl;

import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;
import com.pukanghealth.dao.AppModuleOptionDao;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.service.AppModuleOptionService;


@Service("appModuleOptionService")
public class AppModuleOptionServiceImpl extends ServiceImpl<AppModuleOptionDao, AppModuleOptionEntity> implements AppModuleOptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppModuleOptionEntity> page = this.page(
                new Query<AppModuleOptionEntity>().getPage(params),
                new QueryWrapper<AppModuleOptionEntity>()
        );

        return new PageUtils(page);
    }

}
