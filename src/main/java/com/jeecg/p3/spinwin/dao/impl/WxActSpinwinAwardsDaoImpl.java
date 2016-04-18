package com.jeecg.p3.spinwin.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.spinwin.dao.WxActSpinwinAwardsDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;

/**
 * 描述：</b>WxActSpinwinAwardsDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinAwardsDao")
public class WxActSpinwinAwardsDaoImpl extends GenericDaoDefault<WxActSpinwinAwards> implements WxActSpinwinAwardsDao{

	@Override
	public Integer count(PageQuery<WxActSpinwinAwards> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinAwards> queryPageList(
			PageQuery<WxActSpinwinAwards> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwinAwards> wrapper = new PageQueryWrapper<WxActSpinwinAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinAwards>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinAwards> queryAwards(String jwid) {

		return (List<WxActSpinwinAwards>)super.query("queryAwards",jwid);
	}

	@Override
	public Boolean validReat(int value, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("value", String.valueOf(value));
		param.put("jwid", jwid);
		WxActSpinwinAwards award = (WxActSpinwinAwards)super.queryOne("validReat",param);
		if(award==null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Boolean validReat(String id, int value, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("value", String.valueOf(value));
		param.put("jwid", jwid);
		WxActSpinwinAwards award= (WxActSpinwinAwards)super.queryOne("editValidReat",param);
		if(award==null) {
			return false;
		} else {
			return true;
		}
	}

}
