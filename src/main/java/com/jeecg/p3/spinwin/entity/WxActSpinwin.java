package com.jeecg.p3.spinwin.entity;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActSpinwin:幸运大转盘活动配置<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public class WxActSpinwin implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 *微信公众号
	 */
	private String jwidName;

	private List<WxActSpinwinRelation> awarsList;

		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getProjectCode() {
	public List<WxActSpinwinRelation> getAwarsList() {
		return awarsList;
	}
	public void setAwarsList(List<WxActSpinwinRelation> awarsList) {
		this.awarsList = awarsList;
	}
}
