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
@TableName("app_metro_station")
public class AppMetroStationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer metroStationId;
	/**
	 * 
	 */
	private String metroStationName;
	/**
	 * 
	 */
	private Integer metroStationLineId;
	/**
	 * 
	 */
	private Integer metroStationOrderInLine;
	/**
	 * 
	 */
	private String metroStationLongitude;
	/**
	 * 
	 */
	private String metroStationLatitude;
	/**
	 * 
	 */
	private Date metroStationCreateTime;
	/**
	 * 
	 */
	private Date metroStationUpdateTime;
	/**
	 * 
	 */
	private String metroStationComment;

}
