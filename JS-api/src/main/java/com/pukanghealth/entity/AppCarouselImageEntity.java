package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 轮播图
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@Data
@TableName("app_carousel_image")
public class AppCarouselImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer imageId;
	/**
	 * 图片url
	 */
	private String imageUrl;
	/**
	 * 轮播顺序
	 */
	private Integer imageSort;
	/**
	 * 有效开始时间
	 */
	private Date imageBeginTime;
	/**
	 * 轮播结束时间
	 */
	private Date imageEndTime;
	/**
	 * 第三方图片url
	 */
	private String imageRefUrl;
	/**
	 * 图片显示终端
	 */
	private String imageTerminal;
	/**
	 * 图片是否有效0::有效  1:失效
	 */
	private Integer imageDeleted;
	/**
	 * 说明
	 */
	private String imageDescription;
	/**
	 * 点击图片跳转url
	 */
	private String imageJumpUrl;

}
