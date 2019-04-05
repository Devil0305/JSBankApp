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
@TableName("app_metro_line")
public class AppMetroLineEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer metroLineId;
	/**
	 * 
	 */
	private String metroLineName;
	/**
	 * 
	 */
	private Integer metroLineOrderInCity;
	/**
	 * 
	 */
	private Integer metroLineCityId;
	/**
	 * 
	 */
	private Date metroLineCreateTime;
	/**
	 * 
	 */
	private Date metroLineUpdateTime;
	/**
	 * 
	 */
	private String metroLineComment;

}
