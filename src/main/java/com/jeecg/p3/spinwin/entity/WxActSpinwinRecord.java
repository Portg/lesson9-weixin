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
	

	private String actName;

	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	@Excel(exportName="中奖时间", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)

	@Excel(exportName="奖项", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String awardsName;

	@Excel(exportName="收货人姓名", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	@Excel(exportName="手机号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	@Excel(exportName="地址", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)

	private String jwidName;

		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getOpenid() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public String getRealname() {
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
}
