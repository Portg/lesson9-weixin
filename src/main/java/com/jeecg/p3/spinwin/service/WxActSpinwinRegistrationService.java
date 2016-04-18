package com.jeecg.p3.spinwin.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;

/**
 * 描述：</b>WxActSpinwinRegistrationService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRegistrationService {
	
	
	public void doAdd(WxActSpinwinRegistration wxActSpinwinRegistration);
	
	public void doEdit(WxActSpinwinRegistration wxActSpinwinRegistration);
	
	public void doDelete(String id);
	
	public WxActSpinwinRegistration queryById(String id);
	
	public PageList<WxActSpinwinRegistration> queryPageList(PageQuery<WxActSpinwinRegistration> pageQuery);

	public WxActSpinwinRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid,String actId,String jwid);

	public void add(WxActSpinwinRegistration wxActSpinwinRegistration);
}

