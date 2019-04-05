package com.pukanghealth.service.impl;

import com.pukanghealth.dao.AppOrderDao;
import com.pukanghealth.entity.AppOrderEntity;
import com.pukanghealth.service.AppOrderService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;



@Service("appOrderService")
public class AppOrderServiceImpl extends ServiceImpl<AppOrderDao, AppOrderEntity> implements AppOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppOrderEntity> page = this.page(
                new Query<AppOrderEntity>().getPage(params),
                new QueryWrapper<AppOrderEntity>()
        );

        return new PageUtils(page);
    }

}
