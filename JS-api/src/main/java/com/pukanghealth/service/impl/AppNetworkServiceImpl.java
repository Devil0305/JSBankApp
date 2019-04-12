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
        if (appNetworkAO.getCurrPageNo() == 0){
            appNetworkAO.setCurrPageNo(1);
        }
        if (appNetworkAO.getPageSize() == 0){
            appNetworkAO.setPageSize(10);
        }
        List<AppNetworkEntity> appNetworkEntityList = appNetworkDao.getCBBNetworkList(appNetworkAO);
        int totalCount = appNetworkDao.getCBBNetworkCount(appNetworkAO);
        return new PageUtils(appNetworkEntityList, totalCount, appNetworkAO.getPageSize(), appNetworkAO.getCurrPageNo());
    }

    @Override
    public PageUtils querySocialSecurityPage(AppNetworkAO appNetworkAO) {
        if (appNetworkAO.getCurrPageNo() == 0){
            appNetworkAO.setCurrPageNo(1);
        }
        if (appNetworkAO.getPageSize() == 0){
            appNetworkAO.setPageSize(10);
        }
        List<Integer> optionIds;
        List<Integer> moduleIds = new ArrayList<>();
        if (appNetworkAO.getOptionIds() != null && appNetworkAO.getOptionIds().size() > 0) {
            optionIds = appNetworkAO.getOptionIds();
            for (Integer optionId : optionIds) {
                List<AppModuleOptionEntity> appModuleOptionEntityList = appModuleOptionDao.selectList(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId).eq("type", 1));
                if (null == appModuleOptionEntityList || appModuleOptionEntityList.size() == 0){
                    return null;
                }
                for (AppModuleOptionEntity appModuleOptionEntity : appModuleOptionEntityList) {
                    Integer moduleId = appModuleOptionEntity.getModuleId();
                    moduleIds.add(moduleId);
                }
            }
        }
        if (moduleIds.size() > 0){
            appNetworkAO.setModuleIds(moduleIds);
        }
        List<AppNetworkVO> appNetworkVOList = new ArrayList<>();
        List<AppNetworkEntity> appNetworkEntityList = appNetworkDao.getSocialSecurityList(appNetworkAO);
        for (AppNetworkEntity appNetworkEntity : appNetworkEntityList) {
            AppNetworkVO appNetworkVO = new AppNetworkVO();
            BeanUtils.copyProperties(appNetworkEntity, appNetworkVO);
            List<AppModuleOptionEntity> appModuleOptionEntityList = appModuleOptionDao.selectList(new QueryWrapper<AppModuleOptionEntity>().eq("module_id", appNetworkEntity.getNetworkId()).eq("type", 1));
            for (AppModuleOptionEntity appModuleOptionEntity : appModuleOptionEntityList) {
                AppOptionItemEntity appOptionItemEntity = appOptionItemDao.selectOne(new QueryWrapper<AppOptionItemEntity>().eq("option_id", appModuleOptionEntity.getOptionId()).eq("option_module_id", 1));
                if (null != appOptionItemEntity) {
                    appNetworkVO.setAppNetworkTypeName(appOptionItemEntity.getOptionDisplayValue());
                }
            }
            appNetworkVOList.add(appNetworkVO);
        }
        int totalCount = appNetworkDao.getSocialSecurityCount(appNetworkAO);
        return new PageUtils(appNetworkVOList, totalCount, appNetworkAO.getPageSize(), appNetworkAO.getCurrPageNo());
    }


}
