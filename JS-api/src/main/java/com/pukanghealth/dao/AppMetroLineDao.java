package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.bo.MetroLineWithStationBO;
import com.pukanghealth.entity.AppMetroLineEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Mapper
public interface AppMetroLineDao extends BaseMapper<AppMetroLineEntity> {
    List<MetroLineWithStationBO> getMetroStationsByCity(int city);
}
