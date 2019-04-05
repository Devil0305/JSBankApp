package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有下拉框原始表
 * 
 * @author wangli
 * @email 
 * @date 2019-04-04 15:10:39
 */
@Data
@TableName("app_option_item")
public class AppOptionItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer optionId;
	/**
	 * 模块所属分组（类型、筛选）
	 */
	private String optionGroupId;
	/**
	 * 所属模块分类 1:合作网点 2合作商户
	 */
	private Integer optionModuleId;
	/**
	 * 原显示是循序
	 */
	private String optionItemCode;
	/**
	 * 实际内容
	 */
	private String optionItemValue;
	/**
	 * 展示内容
	 */
	private String optionDisplayValue;
	/**
	 * 描述
	 */
	private String optionItemDiscription;
	/**
	 * 
	 */
	private Integer optionOrder;

}
