package com.jeecg.p3.spinwin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActSpinwinRelation:活动奖品明细<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public class WxActSpinwinRelation implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 *奖项
	 */
	private String awardId;;
	public String getAwardId() {
		return awardId;
	}
	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}
}
