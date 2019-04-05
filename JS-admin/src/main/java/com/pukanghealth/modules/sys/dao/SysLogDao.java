

package com.pukanghealth.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author wangli
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
