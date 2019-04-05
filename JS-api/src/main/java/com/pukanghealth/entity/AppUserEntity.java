package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * app端用户表
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:29:14
 */
@Data
@TableName("app_user")
public class AppUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long userId;
	/**
	 * app端用户昵称
	 */
	private String userName;
	/**
	 * 电话号码
	 */
	private String mobile;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * app用户编号
	 */
	private String userCode;
	/**
	 * 用户性别0:男 1:女
	 */
	private Integer userSex;
	/**
	 * 用户邮箱
	 */
	private String userEmail;
	/**
	 * 省
	 */
	private String userProvince;
	/**
	 * 市
	 */
	private String userCity;
	/**
	 * 区
	 */
	private String userArea;
	/**
	 * 身份证号
	 */
	private String userCertId;
	/**
	 * 证件类型（默认1身份证）
	 */
	private Integer userCertType;
	/**
	 * 详细地址
	 */
	private String userAddress;
	/**
	 * 生日
	 */
	private Date userBirthday;
	/**
	 * 用户状态1：正常  0失效 
	 */
	private Integer userState;
	/**
	 * 创建时间 
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
