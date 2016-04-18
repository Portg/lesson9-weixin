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
		/**	 *主键	 */	private String id;	/**	 *活动名称	 */	private String title;	/**	 *开始时间	 */	private Date starttime;	/**	 *结束时间	 */	private Date endtime;	/**	 *背景图	 */	private String banner;	/**	 *抽奖次数	 */	private Integer count;	/**	 *入口地址	 */	private String hdurl;	/**	 *是否关注可参加	 */	private String foucsUserCanJoin;	/**	 *是否绑定手机可参加	 */	private String bindingMobileCanJoin;	/**	 *每日抽奖次数	 */	private Integer numPerDay;	/**	 *是否中奖可参与 0：中奖可继续参与 1:中奖不可参与	 */	private String prizeStatus;	/**	 *微信原始id	 */	private String jwid;
	/**
	 *微信公众号
	 */
	private String jwidName;	/**	 *活动编码	 */	private String projectCode;	/**	 *活动描述	 */	private String description;

	private List<WxActSpinwinRelation> awarsList;
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public Date getStarttime() {	    return this.starttime;	}	public void setStarttime(Date starttime) {	    this.starttime=starttime;	}	public Date getEndtime() {	    return this.endtime;	}	public void setEndtime(Date endtime) {	    this.endtime=endtime;	}	public String getBanner() {	    return this.banner;	}	public void setBanner(String banner) {	    this.banner=banner;	}	public Integer getCount() {	    return this.count;	}	public void setCount(Integer count) {	    this.count=count;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public String getBindingMobileCanJoin() {	    return this.bindingMobileCanJoin;	}	public void setBindingMobileCanJoin(String bindingMobileCanJoin) {	    this.bindingMobileCanJoin=bindingMobileCanJoin;	}	public Integer getNumPerDay() {	    return this.numPerDay;	}	public void setNumPerDay(Integer numPerDay) {	    this.numPerDay=numPerDay;	}	public String getPrizeStatus() {	    return this.prizeStatus;	}	public void setPrizeStatus(String prizeStatus) {	    this.prizeStatus=prizeStatus;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}
	public List<WxActSpinwinRelation> getAwarsList() {
		return awarsList;
	}
	public void setAwarsList(List<WxActSpinwinRelation> awarsList) {
		this.awarsList = awarsList;
	}
}

