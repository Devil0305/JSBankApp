package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppNetworkEntity;
import com.pukanghealth.ao.AppNetworkAO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 网点信息
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Mapper
public interface AppNetworkDao extends BaseMapper<AppNetworkEntity> {
    /**
     * 获取建行网点列表信息
     * @param appNetworkAO
     * @return
     */
	List<AppNetworkEntity> getCBBNetworkList(AppNetworkAO appNetworkAO);

    /**
     * 获取建行网点数量
     * @param appNetworkAO
     * @return
     */
	int getCBBNetworkCount(AppNetworkAO appNetworkAO);

    /**
     * 获取社保网点列表信息
     * @param appNetworkAO
     * @return
     */
	List<AppNetworkEntity> getSocialSecurityList(AppNetworkAO appNetworkAO);

    /**
     * 获取社保网点数量
     * @param appNetworkAO
     * @return
     */
	int getSocialSecurityCount(AppNetworkAO appNetworkAO);
}
