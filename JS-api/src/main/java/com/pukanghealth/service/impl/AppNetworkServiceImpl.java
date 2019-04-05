package com.pukanghealth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.dao.AppNetworkDao;
import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.ao.AppNetworkAO;
import com.pukanghealth.service.AppModuleOptionService;
import com.pukanghealth.service.AppNetworkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;

import javax.annotation.Resource;


@Service("appNetworkService")
public class AppNetworkServiceImpl extends ServiceImpl<AppNetworkDao, AppNetworkEntity> implements AppNetworkService {
    @Resource
    private AppNetworkDao appNetworkDao;

    @Resource
    private AppModuleOptionService appModuleOptionService;


    @Override
    public PageUtils queryCCBPage(AppNetworkAO appNetworkAO) {
        List<AppNetworkEntity> appNetworkEntityList = appNetworkDao.getCBBNetworkList(appNetworkAO);
        int totalCount = appNetworkDao.getCBBNetworkCount(appNetworkAO);
        return new PageUtils(appNetworkEntityList, totalCount, appNetworkAO.getPageSize(), appNetworkAO.getCurrPageNo());
    }

    @Override
    public PageUtils querySocialSecurityPage(AppNetworkAO appNetworkAO) {
        List<Integer> optionIds;
        List<Integer> moduleIds = new ArrayList<>();
        if (appNetworkAO.getOptionIds() != null){
            optionIds = appNetworkAO.getOptionIds();
            for (Integer optionId : optionIds){
                Integer moduleId = appModuleOptionService.list(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId)).get(0).getModuleId();
                moduleIds.add(moduleId);
            }
        }
        appNetworkAO.setModuleIds(moduleIds);
        List<AppNetworkEntity> appNetworkEntityList = appNetworkDao.getSocialSecurityList(appNetworkAO);
        int totalCount = appNetworkDao.getSocialSecurityCount(appNetworkAO);
        return new PageUtils(appNetworkEntityList, totalCount,appNetworkAO.getPageSize(), appNetworkAO.getCurrPageNo());
    }



}
