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

/**
 * @Author: liukang
 * @Date: 2019/4/7 15:31
 */

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
        List<Integer> optionIds;
        List<Integer> moduleIds = new ArrayList<>();
        if (appMerchantAO.getOptionIds() != null) {
            optionIds = appMerchantAO.getOptionIds();
            for (Integer optionId : optionIds) {
               // Integer moduleId = appModuleOptionService.list(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId)).get(0).getModuleId();
                Integer moduleId = appModuleOptionDao.selectOne(new QueryWrapper<AppModuleOptionEntity>().eq("option_id", optionId)).getModuleId();
                moduleIds.add(moduleId);
            }
        }
        appMerchantAO.setModuleIds(moduleIds);
        List<AppMerchantEntity> appMerchantEntities = appMerchantDao.getMerchantList(appMerchantAO);
        List<AppMerchantVO> appMerchantVOList = new ArrayList<>();
        AppMerchantVO appMerchantVO = new AppMerchantVO();
        for (AppMerchantEntity appMerchantEntity : appMerchantEntities) {
            //AppModuleOptionEntity appModuleOptionEntity = appModuleOptionService.getOne(new QueryWrapper<AppModuleOptionEntity>().eq("module_id", appMerchantEntity.getMerchantId()));
            AppModuleOptionEntity appModuleOptionEntity = appModuleOptionDao.selectOne(new QueryWrapper<AppModuleOptionEntity>().eq("module_id", appMerchantEntity.getMerchantId()));
            //AppOptionItemEntity appOptionItemEntity = appOptionItemService.query().getBaseMapper().selectById(appModuleOptionEntity.getOptionId());
            AppOptionItemEntity appOptionItemEntity = appOptionItemDao.selectById(appModuleOptionEntity.getOptionId());
//            appMerchantVO.setMerchantAddress(appMerchantEntity.getMerchantAddress());
//            appMerchantVO.setMerchantCityId(appMerchantEntity.getMerchantCityId());
//            appMerchantVO.setMerchantCode(appMerchantEntity.getMerchantCode());
//            appMerchantVO.setMerchantCreateTime(appMerchantEntity.getMerchantCreateTime());
//            appMerchantVO.setMerchantDeleted(appMerchantEntity.getMerchantDeleted());
//            appMerchantVO.setMerchantDesc(appMerchantEntity.getMerchantDesc());
//            appMerchantVO.setMerchantDistance(appMerchantEntity.getMerchantDistance());
            BeanUtils.copyProperties(appMerchantEntity, appMerchantVO);
            appMerchantVO.setAppMerchantTypeName(appOptionItemEntity.getOptionItemValue());
            appMerchantVOList.add(appMerchantVO);
        }
        int totalCount = appMerchantDao.getMerchantCount(appMerchantAO);
        return new PageUtils(appMerchantVOList, totalCount, appMerchantAO.getCurrPageNo(), appMerchantAO.getPageSize());
    }

}
