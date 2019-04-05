package com.pukanghealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社保一键通通知公告
 * 
 * @author wangli
 * @email
 * @date 2019-03-26 10:24:27
 */
@Data
@TableName("app_announcement")
public class AppAnnouncementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer announcementId;
	/**
	 * 公告title
	 */
	private String announcementTitle;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 0:通知公告 1:政策查询
	 */
	private Integer announcementType;
	/**
	 * 跳转的静态页面url
	 */
	private String announcementUrl;

}
