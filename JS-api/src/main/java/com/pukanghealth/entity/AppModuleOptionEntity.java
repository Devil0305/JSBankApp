package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author wangli
 * @email 
 * @date 2019-04-04 15:10:39
 */
@Data
@TableName("app_module_option")
public class AppModuleOptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 1:合作网点 2:合作商户
	 */
	private Integer type;
	/**
	 * 模块id
	 */
	private Integer moduleId;
	/**
	 * option_item的id
	 */
	private Integer optionId;

}
