package com.jeecg.p3.spinwin.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActSpinwinRegistration:活动参与记录<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public class WxActSpinwinRegistration implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *活动	 */	private String actId;	/**	 *活动参与人openid	 */	private String openid;	/**	 *活动参与人昵称	 */	private String nickname;	/**	 *活动参与人头像	 */	private String head;	/**	 *抽奖次数	 */	private Integer awardsNum;	/**	 *抽奖状态0:未抽奖;1:已抽奖;	 */	private String awardsStatus;	/**	 *创建时间	 */	private Date createTime;	/**	 *更新时间	 */	private Date updateTime;	/**	 *微信平台原始id	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getHead() {	    return this.head;	}	public void setHead(String head) {	    this.head=head;	}	public Integer getAwardsNum() {	    return this.awardsNum;	}	public void setAwardsNum(Integer awardsNum) {	    this.awardsNum=awardsNum;	}	public String getAwardsStatus() {	    return this.awardsStatus;	}	public void setAwardsStatus(String awardsStatus) {	    this.awardsStatus=awardsStatus;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
}

