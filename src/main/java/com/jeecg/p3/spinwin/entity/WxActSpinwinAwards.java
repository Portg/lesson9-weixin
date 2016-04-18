package com.jeecg.p3.spinwin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActSpinwinAwards:奖项<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public class WxActSpinwinAwards implements Entity<String> {
	private static final long serialVersionUID = 1L;
	

	private String jwidName;
	public String getId() {
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
}
