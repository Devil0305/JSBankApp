package com.pukanghealth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pukanghealth.dao.AppModuleOptionDao;
import com.pukanghealth.dao.AppNetworkDao;
import com.pukanghealth.dao.AppOptionItemDao;
import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.ao.AppNetworkAO;
import com.pukanghealth.entity.AppOptionItemEntity;
import com.pukanghealth.service.AppNetworkService;
import com.pukanghealth.vo.AppNetworkVO;
import org.springframework.beans.BeanUtils;
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
    private AppModuleOptionDao appModuleOptionDao;

    @Resource
    private AppOptionItemDao appOptionItemDao;


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
                Integer moduleId = appModuleOptionDao.selectOne(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId)).getModuleId();
                moduleIds.add(moduleId);
            }
        }
        appNetworkAO.setModuleIds(moduleIds);
        List<AppNetworkVO> appNetworkVOList = new ArrayList<>();
        List<AppNetworkEntity> appNetworkEntityList = appNetworkDao.getSocialSecurityList(appNetworkAO);
        AppNetworkVO appNetworkVO = new AppNetworkVO();
        for (AppNetworkEntity appNetworkEntity : appNetworkEntityList){
            AppModuleOptionEntity appModuleOptionEntity = appModuleOptionDao.selectOne(new QueryWrapper<AppModuleOptionEntity>().eq("module_id", appNetworkEntity.getNetworkId()));
            AppOptionItemEntity appOptionItemEntity = appOptionItemDao.selectById(appModuleOptionEntity.getOptionId());
            BeanUtils.copyProperties(appNetworkEntity, appNetworkVO);
            appNetworkVO.setAppNetworkTypeName(appOptionItemEntity.getOptionItemValue());
            appNetworkVOList.add(appNetworkVO);
        }
        int totalCount = appNetworkDao.getSocialSecurityCount(appNetworkAO);
        return new PageUtils(appNetworkVOList, totalCount,appNetworkAO.getPageSize(), appNetworkAO.getCurrPageNo());
    }



}
