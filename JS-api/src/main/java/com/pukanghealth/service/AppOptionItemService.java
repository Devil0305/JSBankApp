package com.pukanghealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pukanghealth.ao.OptionItemAO;
import com.pukanghealth.common.utils.PageUtils;
import com.pukanghealth.entity.AppOptionItemEntity;


import java.util.List;
import java.util.Map;

/**
 * 所有下拉框原始表
 *
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
public interface AppOptionItemService extends IService<AppOptionItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Map<String, Object> selectByOptionTeamId(Integer optionTeamId);

}

