package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Data
@TableName("app_sys_province")
public class AppSysProvinceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer provinceId;
	/**
	 * 
	 */
	private String provinceType;
	/**
	 * 
	 */
	private Integer provinceOrder;
	/**
	 * 
	 */
	private String provinceName;
	/**
	 * 
	 */
	private String provinceShortname;
	/**
	 * 
	 */
	private String provincePinyin;
	/**
	 * 
	 */
	private Integer provinceStatus;
	/**
	 * 
	 */
	private Integer provinceDeleted;
	/**
	 * 
	 */
	private Date provinceUpdatetime;

}
