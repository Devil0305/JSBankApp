package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 网点信息
 * 
 * @author wangli
 * @email 
 * @date 2019-04-02 15:25:03
 */
@Data
@TableName("app_network")
public class AppNetworkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer networkId;
	/**
	 * 关联option_item
	 */
	private Integer networkTypeId;
	/**
	 * 1：社保网点   2：建行网点
	 */
	private Integer networkType;
	/**
	 * 网点名称
	 */
	private String networkName;
	/**
	 * 网点代吗
	 */
	private String networkCode;
	/**
	 * 网点距离
	 */
	private String networkDistance;
	/**
	 * 网点标签
	 */
	private String networkSign;
	/**
	 * 经度
	 */
	private BigDecimal networkLatitude;
	/**
	 * 维度
	 */
	private BigDecimal networkLongitude;
	/**
	 * 电话
	 */
	private String networkPhone;
	/**
	 * 移动电话
	 */
	private String networkMobile;
	/**
	 * 网点logo地址
	 */
	private String networkLogo;
	/**
	 * 网点地址
	 */
	private String networkAddress;
	/**
	 * 网点描述
	 */
	private String networkShortName;
	/**
	 * 
	 */
	private Integer networkCityId;
	/**
	 * 
	 */
	private Integer networkProvinceId;
	/**
	 * 
	 */
	private Integer networkDistrictId;
	/**
	 * 
	 */
	private Integer networkDeleted;
	/**
	 * 
	 */
	private Integer networkShow;
	/**
	 * 
	 */
	private String networkDesc;
	/**
	 * 
	 */
	private Date networkCreateTime;
	/**
	 * 
	 */
	private Date networkUpdateTime;

}
