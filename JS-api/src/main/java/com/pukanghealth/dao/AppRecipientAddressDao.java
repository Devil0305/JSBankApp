package com.pukanghealth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.entity.AppRecipientAddressEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 保存地址
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Mapper
public interface AppRecipientAddressDao extends BaseMapper<AppRecipientAddressEntity> {
    boolean updateRecipientDeletedById(@Param("ids")Integer[] ids);
}
