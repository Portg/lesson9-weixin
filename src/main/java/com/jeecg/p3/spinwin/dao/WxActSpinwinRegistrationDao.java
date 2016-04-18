package com.jeecg.p3.spinwin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;

/**
 * 描述：</b>WxActSpinwinRegistrationDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRegistrationDao extends GenericDao<WxActSpinwinRegistration>{

	public Integer count(PageQuery<WxActSpinwinRegistration> pageQuery);

	public List<WxActSpinwinRegistration> queryPageList(PageQuery<WxActSpinwinRegistration> pageQuery,Integer itemCount);

	public WxActSpinwinRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid,String actId,String jwid);
}

