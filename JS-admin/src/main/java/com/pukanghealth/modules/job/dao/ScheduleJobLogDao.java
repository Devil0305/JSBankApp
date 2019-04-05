

package com.pukanghealth.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pukanghealth.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author wangli
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}
