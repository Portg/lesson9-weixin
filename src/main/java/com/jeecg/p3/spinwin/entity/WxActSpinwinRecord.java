package com.jeecg.p3.spinwin.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

import com.jeecg.p3.spinwin.annotation.Excel;

/**
 * 描述：</b>WxActSpinwinRecord:奖品记录<br>
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public class WxActSpinwinRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *主键	 */	private String id;	/**	 *act_id	 */	private String actId;

	private String actName;
	/**	 *openid	 */
	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String openid;	/**	 *昵称	 */
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String nickname;	/**	 *中奖时间	 */
	@Excel(exportName="中奖时间", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private Date recieveTime;	/**	 *奖项	 */	private String awardsId;

	@Excel(exportName="奖项", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String awardsName;
	/**	 *收货人姓名	 */
	@Excel(exportName="收货人姓名", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String realname;	/**	 *手机号	 */
	@Excel(exportName="手机号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String phone;	/**	 *地址	 */
	@Excel(exportName="地址", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String address;	/**	 *抽奖序号	 */	private Integer seq;	/**	 *微信平台原始id	 */	private String jwid;

	private String jwidName;
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public Date getRecieveTime() {	    return this.recieveTime;	}	public void setRecieveTime(Date recieveTime) {	    this.recieveTime=recieveTime;	}	public String getAwardsId() {	    return this.awardsId;	}	public void setAwardsId(String awardsId) {	    this.awardsId=awardsId;	}	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public String getRealname() {	    return this.realname;	}	public void setRealname(String realname) {	    this.realname=realname;	}	public String getPhone() {	    return this.phone;	}	public void setPhone(String phone) {	    this.phone=phone;	}	public String getAddress() {	    return this.address;	}	public void setAddress(String address) {	    this.address=address;	}	public Integer getSeq() {	    return this.seq;	}	public void setSeq(Integer seq) {	    this.seq=seq;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
}

