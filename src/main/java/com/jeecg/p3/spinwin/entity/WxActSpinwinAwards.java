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
		/**	 *主键	 */	private String id;	/**	 *奖项名称	 */	private String awardsName;	/**	 *微信原始id	 */	private String jwid;	/**	 *奖项值	 */	private Integer awardsValue;

	private String jwidName;
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getAwardsName() {	    return this.awardsName;	}	public void setAwardsName(String awardsName) {	    this.awardsName=awardsName;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public Integer getAwardsValue() {	    return this.awardsValue;	}	public void setAwardsValue(Integer awardsValue) {	    this.awardsValue=awardsValue;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
}

