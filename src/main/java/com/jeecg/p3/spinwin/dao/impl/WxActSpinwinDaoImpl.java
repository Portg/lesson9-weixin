package com.jeecg.p3.spinwin.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.jeecg.p3.spinwin.dao.WxActSpinwinDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwin;

/**
 * 描述：</b>WxActSpinwinDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinDao")
public class WxActSpinwinDaoImpl extends GenericDaoDefault<WxActSpinwin> implements WxActSpinwinDao{

	@Override
	public Integer count(PageQuery<WxActSpinwin> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwin> queryPageList(
			PageQuery<WxActSpinwin> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwin> wrapper = new PageQueryWrapper<WxActSpinwin>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwin>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwin> queryActs(String jwid) {

		return (List<WxActSpinwin>)super.query("queryActs",jwid);
	}


}

