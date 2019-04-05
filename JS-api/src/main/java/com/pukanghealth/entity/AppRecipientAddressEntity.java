package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 保存地址
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Data
@TableName("app_recipient_address")
public class AppRecipientAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.INPUT)
	private Integer recipientId;

	private Integer recipientUserId;
	/**
	 * 收件人
	 */
	private String recipientName;
	/**
	 * 电话号码
	 */
	private String recipientMobile;
	/**
	 * 收货地址
	 */
	private String recipientAdderss;
	/**
	 * 
	 */
	private Integer recipientPovinceId;
	/**
	 * 
	 */
	private Integer recipientCityId;
	/**
	 * 
	 */
	private Integer recipientDistrictId;
	/**
	 * 详细地址
	 */
	private String recipientAdderssDetail;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 0:正常  1:删除
	 */
	private Integer recipientDeleted;
	/**
	 * 0:默认地址
	 */
	private Integer recipientStatus;

}
