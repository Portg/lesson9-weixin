package com.jeecg.p3.spinwin.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.spinwin.dao.WxActSpinwinPrizesDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;

/**
 * 描述：</b>WxActSpinwinPrizesDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinPrizesDao")
public class WxActSpinwinPrizesDaoImpl extends GenericDaoDefault<WxActSpinwinPrizes> implements WxActSpinwinPrizesDao{

	@Override
	public Integer count(PageQuery<WxActSpinwinPrizes> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinPrizes> queryPageList(
			PageQuery<WxActSpinwinPrizes> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwinPrizes> wrapper = new PageQueryWrapper<WxActSpinwinPrizes>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinPrizes>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinPrizes> queryByActId(String actid) {

		return (List<WxActSpinwinPrizes>)super.query("queryByActId",actid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinPrizes> queryRemainAwardsByActId(String actid) {

		return (List<WxActSpinwinPrizes>)super.query("queryRemainAwardsByActId",actid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinPrizes> queryByAwardIdAndActId(String awardid, String actId) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("awardId", awardid);
		param.put("actId", actId);
		return (List<WxActSpinwinPrizes>)super.query("queryByAwardIdAndActId",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinPrizes> queryPrizes(String jwid) {

		return (List<WxActSpinwinPrizes>)super.query("queryPrizes",jwid);
	}

}
