

package com.pukanghealth.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.modules.sys.entity.SysLogEntity;
import com.pukanghealth.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 * @author wangli
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
