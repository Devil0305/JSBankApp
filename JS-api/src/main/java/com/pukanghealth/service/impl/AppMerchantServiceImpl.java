package com.pukanghealth.service.impl;

import com.pukanghealth.ao.AppMerchantAO;
import com.pukanghealth.dao.AppMerchantDao;
import com.pukanghealth.entity.AppMerchantEntity;
import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.service.AppMerchantService;
import com.pukanghealth.service.AppModuleOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.common.utils.Query;

import javax.annotation.Resource;


@Service("appMerchantService")
public class AppMerchantServiceImpl extends ServiceImpl<AppMerchantDao, AppMerchantEntity> implements AppMerchantService {
    @Resource
    private AppMerchantDao appMerchantDao;

    @Resource
    private AppModuleOptionService appModuleOptionService;

    @Override
    public PageUtils queryMerchantPage(AppMerchantAO appMerchantAO) {
        List<Integer> optionIds;
        List<Integer> moduleIds = new ArrayList<>();
        if (appMerchantAO.getOptionIds() != null){
            optionIds = appMerchantAO.getOptionIds();
            for (Integer optionId : optionIds){
                Integer moduleId = appModuleOptionService.list(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId)).get(0).getModuleId();
                moduleIds.add(moduleId);
            }
        }
        appMerchantAO.setModuleIds(moduleIds);
        List<AppMerchantEntity> appMerchantEntities = appMerchantDao.getMerchantList(appMerchantAO);
        int totalCount = appMerchantDao.getMerchantCount(appMerchantAO);
        return new PageUtils(appMerchantEntities, totalCount, appMerchantAO.getCurrPageNo(), appMerchantAO.getPageSize());
    }

}
