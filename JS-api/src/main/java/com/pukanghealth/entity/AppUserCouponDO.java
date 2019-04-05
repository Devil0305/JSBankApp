package com.pukanghealth.entity;

import lombok.Data;

/**
 * @Author:LiWang
 * @Description:
 * @Date:Created in 14:51 2019/4/3
 * @Modified By: 双表查询返回DO
 */
@Data
public class AppUserCouponDO extends AppCouponEntity{
    private Integer count;//个人当前购买此优惠券数量

    private Integer userCouponId;//生成二位码传过来
}
