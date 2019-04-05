package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券和用户中间表
 * 
 * @author wangli
 * @email 
 * @date 2019-04-03 14:24:53
 */
@Data
@TableName("app_user_coupon")
public class AppUserCouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userCouponId;
	/**
	 * 
	 */
	private Integer couponId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 购买数量
	 */
	private Integer count;
	/**
	 * 优惠券唯一识别码
	 */
	private String couponCode;

}
