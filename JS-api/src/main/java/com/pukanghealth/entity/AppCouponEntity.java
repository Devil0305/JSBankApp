package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券
 * 
 * @author wangli
 * @email 
 * @date 2019-04-03 14:24:53
 */
@Data
@TableName("app_coupon")
public class AppCouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer couponId;
	/**
	 * 优惠券名
	 */
	private String couponName;
	/**
	 * 优惠券logo url
	 */
	private String couponLogo;
	/**
	 * 所属商家编号
	 */
	private String couponMerchantCode;
	/**
	 * 所属商家名称
	 */
	private String couponMerchantName;
	/**
	 * 优惠券总数量
	 */
	private Integer couponTotal;
	/**
	 * 已卖出优惠券数量
	 */
	private Integer couponCount;
	/**
	 * 优惠券开始卖时间
	 */
	private Date couponBeginTime;
	/**
	 * 优惠券失效时间
	 */
	private Date couponInvalidTime;
	/**
	 * 优惠券生效时间
	 */
	private Date couponEffectiveTime;
	/**
	 * 优惠券结束销售时间
	 */
	private Date couponEndTime;
	/**
	 * 优惠券优惠价格
	 */
	private BigDecimal couponCouponPrice;
	/**
	 * 优惠券详情
	 */
	private String couponDetail;
	/**
	 * 优惠券描述
	 */
	private String couponDesc;
	/**
	 * 优惠券价格
	 */
	private BigDecimal couponPrice;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 0:待使用  1：过期  2:已使用 
	 */
	private Integer couponStatus;
	/**
	 * 优惠券状态0:可买 1:不可卖
	 */
	private Integer couponDeleted;

}
