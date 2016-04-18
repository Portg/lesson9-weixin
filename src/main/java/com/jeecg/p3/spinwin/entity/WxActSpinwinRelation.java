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
		/**	 *主键	 */	private String id;	/**	 *奖品	 */	private String prizeId;	/**	 *活动	 */	private String actId;	/**	 *微信平台原始id	 */	private String jwid;
	/**
	 *奖项
	 */
	private String awardId;;	/**	 *数量	 */	private Integer amount;	/**	 *剩余数量	 */	private Integer remainNum;	/**	 *概率	 */	private Double probability;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getPrizeId() {	    return this.prizeId;	}	public void setPrizeId(String prizeId) {	    this.prizeId=prizeId;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getAwardId() {
		return awardId;
	}
	public void setAwardId(String awardId) {
		this.awardId = awardId;
	}	public Integer getAmount() {	    return this.amount;	}	public void setAmount(Integer amount) {	    this.amount=amount;	}	public Integer getRemainNum() {	    return this.remainNum;	}	public void setRemainNum(Integer remainNum) {	    this.remainNum=remainNum;	}	public Double getProbability() {	    return this.probability;	}	public void setProbability(Double probability) {	    this.probability=probability;	}
}

