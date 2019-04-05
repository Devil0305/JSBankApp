package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 * 
 * @author wangli
 * @email
 * @date 2019-03-27 15:25:40
 */
@Data
@TableName("app_order")
public class AppOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer orderId;
	/**
	 * 订单编号
	 */
	private String orderCode;
	/**
	 * 订单状态0::待支付 2:已支付 3:已取消 4:已完成（购买商品已使用或者订单已经彻底结束）
	 */
	private Integer orderStatus;
	/**
	 * 订单类型 1：优惠券 2：体检 3：高端医疗 4：电话医生 5：其他
	 */
	private Integer orderType;
	/**
	 * 购买商品id
	 */
	private Integer orderGoodsId;
	/**
	 * 交易流水号（已支付完成生成交易流水号）
	 */
	private String orderTradindNo;
	/**
	 * 下单时间
	 */
	private Date orderDate;
	/**
	 * 订单金额
	 */
	private BigDecimal orderAmt;
	/**
	 * 实际付款
	 */
	private BigDecimal orderActualAmt;
	/**
	 * 订单商品数量
	 */
	private Integer orderGoodsQuantity;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 目前一个订单就同类型的商品如果出现多种需要用到订单详情表
	 */
	private Integer orderDetailId;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 0：正常订单  1异常订单
	 */
	private Integer hide;

}
