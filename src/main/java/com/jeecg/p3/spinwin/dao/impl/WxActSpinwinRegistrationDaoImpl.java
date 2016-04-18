package com.jeecg.p3.spinwin.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRegistrationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;

/**
 * 描述：</b>WxActSpinwinRegistrationDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinRegistrationDao")
public class WxActSpinwinRegistrationDaoImpl extends GenericDaoDefault<WxActSpinwinRegistration> implements WxActSpinwinRegistrationDao{

	@Override
	public Integer count(PageQuery<WxActSpinwinRegistration> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRegistration> queryPageList(
			PageQuery<WxActSpinwinRegistration> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwinRegistration> wrapper = new PageQueryWrapper<WxActSpinwinRegistration>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinRegistration>)super.query("queryPageList",wrapper);
	}

	@Override
	public WxActSpinwinRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid, String actId, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (WxActSpinwinRegistration)super.queryOne("queryRegistrationByOpenidAndActIdAndJwid",param);
	}


}

