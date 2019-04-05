package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 合作商户
 * 
 * @author wangli
 * @email 
 * @date 2019-04-02 15:25:03
 */
@Data
@TableName("app_merchant")
public class AppMerchantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer merchantId;
	/**
	 * 关联option_item
	 */
	private Integer merchantTypeId;
	/**
	 * 商户名称
	 */
	private String merchantName;
	/**
	 * 商户代吗
	 */
	private String merchantCode;
	/**
	 * 商户距离
	 */
	private String merchantDistance;
	/**
	 * 商户标签
	 */
	private String merchantSign;
	/**
	 * 经度
	 */
	private BigDecimal merchantLatitude;
	/**
	 * 维度
	 */
	private BigDecimal merchantLongitude;
	/**
	 * 电话
	 */
	private String merchantPhone;
	/**
	 * 移动电话
	 */
	private String merchantMobile;
	/**
	 * 商户logo地址
	 */
	private String merchantLogo;
	/**
	 * 商户地址
	 */
	private String merchantAddress;
	/**
	 * 商户描述
	 */
	private String merchantShortName;
	/**
	 * 
	 */
	private Integer merchantCityId;
	/**
	 * 
	 */
	private Integer merchantProvinceId;
	/**
	 * 
	 */
	private Integer merchantDistrictId;
	/**
	 * 
	 */
	private Integer merchantDeleted;
	/**
	 * 
	 */
	private Integer merchantShow;
	/**
	 * 
	 */
	private String merchantDesc;
	/**
	 * 
	 */
	private Date merchantCreateTime;
	/**
	 * 
	 */
	private Date merchantUpdateTime;

}
