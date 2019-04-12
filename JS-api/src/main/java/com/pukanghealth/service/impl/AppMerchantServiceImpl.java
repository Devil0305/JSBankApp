package com.pukanghealth.service.impl;

import com.pukanghealth.ao.AppMerchantAO;
import com.pukanghealth.dao.AppMerchantDao;
import com.pukanghealth.dao.AppModuleOptionDao;
import com.pukanghealth.dao.AppOptionItemDao;
import com.pukanghealth.entity.AppMerchantEntity;
import com.pukanghealth.entity.AppModuleOptionEntity;
import com.pukanghealth.entity.AppOptionItemEntity;
import com.pukanghealth.service.AppMerchantService;
import com.pukanghealth.vo.AppMerchantVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pukanghealth.common.utils.PageUtils;

import javax.annotation.Resource;


@Service("appMerchantService")
public class AppMerchantServiceImpl extends ServiceImpl<AppMerchantDao, AppMerchantEntity> implements AppMerchantService {
    @Resource
    private AppMerchantDao appMerchantDao;

    @Resource
    private AppModuleOptionDao appModuleOptionDao;

    @Resource
    private AppOptionItemDao appOptionItemDao;

    @Override
    public PageUtils queryMerchantPage(AppMerchantAO appMerchantAO) {
        if (appMerchantAO.getCurrPageNo() == 0){
            appMerchantAO.setCurrPageNo(1);
        }
        if (appMerchantAO.getPageSize() == 0){
            appMerchantAO.setPageSize(10);
        }
        List<Integer> optionIds;
        List<Integer> moduleIds = new ArrayList<>();
        if (appMerchantAO.getOptionIds() != null && appMerchantAO.getOptionIds().size() > 0) {
            optionIds = appMerchantAO.getOptionIds();
            for (Integer optionId : optionIds) {
                List<AppModuleOptionEntity> appModuleOptionEntityList = appModuleOptionDao.selectList(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId).eq("type",2));
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
            appMerchantAO.setModuleIds(moduleIds);
        }
        List<AppMerchantEntity> appMerchantEntities = appMerchantDao.getMerchantList(appMerchantAO);
        int totalCount = appMerchantDao.getMerchantCount(appMerchantAO);
        List<AppMerchantVO> appMerchantVOList = new ArrayList<>();
        for (AppMerchantEntity appMerchantEntity : appMerchantEntities) {
            AppMerchantVO appMerchantVO = new AppMerchantVO();
            BeanUtils.copyProperties(appMerchantEntity, appMerchantVO);
            List<AppModuleOptionEntity> appModuleOptionEntityList = appModuleOptionDao.selectList(new QueryWrapper<AppModuleOptionEntity>().eq("module_id", appMerchantEntity.getMerchantId()).eq("type", 2));
            for (AppModuleOptionEntity appModuleOptionEntity : appModuleOptionEntityList) {
                AppOptionItemEntity appOptionItemEntity = appOptionItemDao.selectOne(new QueryWrapper<AppOptionItemEntity>().eq("option_id", appModuleOptionEntity.getOptionId()).eq("option_module_id", 2));
                if (null != appOptionItemEntity){
                    appMerchantVO.setAppMerchantTypeName(appOptionItemEntity.getOptionDisplayValue());
                }
            }
            appMerchantVOList.add(appMerchantVO);
        }
        return new PageUtils(appMerchantVOList, totalCount, appMerchantAO.getPageSize(), appMerchantAO.getCurrPageNo());
    }

}
