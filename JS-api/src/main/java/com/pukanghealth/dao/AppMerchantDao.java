package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.ao.AppMerchantAO;
import com.pukanghealth.entity.AppMerchantEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 合作商户
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Mapper
public interface AppMerchantDao extends BaseMapper<AppMerchantEntity> {

    List<AppMerchantEntity> getMerchantList(AppMerchantAO appMerchantAO);

    int getMerchantCount(AppMerchantAO appMerchantAO);
	
}
