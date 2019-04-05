package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppFeatureDao;
import com.pukanghealth.entity.AppFeatureEntity;
import com.pukanghealth.service.AppFeatureService;
import org.springframework.stereotype.Service;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appFeatureService")
public class AppFeatureServiceImpl extends ServiceImpl<AppFeatureDao, AppFeatureEntity> implements AppFeatureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppFeatureEntity> page = this.page(
                new Query<AppFeatureEntity>().getPage(params),
                new QueryWrapper<AppFeatureEntity>()
        );

        return new PageUtils(page);
    }

}
