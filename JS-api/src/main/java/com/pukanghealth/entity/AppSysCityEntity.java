package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市原始表
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Data
@TableName("app_sys_city")
public class AppSysCityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer cityId;
	/**
	 * 
	 */
	private Integer cityProvince;
	/**
	 * 
	 */
	private String cityName;
	/**
	 * 
	 */
	private Integer cityType;
	/**
	 * 
	 */
	private Integer cityLevel;
	/**
	 * 
	 */
	private Integer cityParentId;
	/**
	 * 
	 */
	private Integer cityOrder;
	/**
	 * 
	 */
	private String cityPinyin;
	/**
	 * 
	 */
	private Integer cityDeleted;
	/**
	 * 
	 */
	private Integer cityStatus;
	/**
	 * 
	 */
	private String cityMemo;
	/**
	 * 
	 */
	private Date cityUpdatetime;

}
