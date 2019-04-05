package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页模块显示控制
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@Data
@TableName("app_feature")
public class AppFeatureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer featureId;
	/**
	 * 模块名称
	 */
	private String featureName;
	/**
	 * 是否显示0:显示 1:不显示
	 */
	private Integer featureShow;
	/**
	 * 创建时间
	 */
	private Date featureCreateTime;
	/**
	 * 修改时间
	 */
	private Date featureUpdateTime;
	/**
	 * 模块唯一标识key
	 */
	private String featureKey;
	/**
	 * 模块展示顺序
	 */
	private Integer featureShowSort;
	/**
	 * 模块所属类型0:代表大模块 1:代表小模块（大模块不可显示小模块自然后设置不可显示）
	 */
	private Integer featureType;
	/**
	 * 小模块关联大模块
	 */
	private Integer featureParentId;

}
