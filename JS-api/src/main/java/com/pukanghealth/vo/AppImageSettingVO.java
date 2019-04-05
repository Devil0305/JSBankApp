package com.pukanghealth.vo;

import com.pukanghealth.entity.AppImageSettingEntity;
import lombok.Data;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 10:47 2019/3/29
 * @Modified By:
 */
@Data
public class AppImageSettingVO extends AppImageSettingEntity{
    private Long remainingTime;
}
