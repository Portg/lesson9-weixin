package com.jeecg.p3.spinwin.entity;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActSpinwinPrizes:奖品<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public class WxActSpinwinPrizes implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *奖品名称	 */	private String name;	/**	 *奖品图片	 */	private String img;	/**	 *微信原始id	 */	private String jwid;

	/**
	 *奖项名称
	 */
	private String awardsName;

	/**
	 *奖项值
	 */
	private Integer awardsValue;

	/**
	 *数量
	 */
	private Integer amount;
	/**
	 *剩余数量
	 */
	private Integer remainNum;

	/**
	 *概率
	 */
	private Double probability;

	private String awardId;

	private String jwidName;
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getImg() {	    return this.img;	}	public void setImg(String img) {	    this.img=img;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public Integer getAwardsValue() {
		return awardsValue;
	}
	public void setAwardsValue(Integer awardsValue) {
		this.awardsValue = awardsValue;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public String getAwardId() {
		return awardId;
	}
	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
}

