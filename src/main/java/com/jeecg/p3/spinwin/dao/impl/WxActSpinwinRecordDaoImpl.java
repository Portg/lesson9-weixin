package com.jeecg.p3.spinwin.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRecordDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;

/**
 * 描述：</b>WxActSpinwinRecordDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinRecordDao")
public class WxActSpinwinRecordDaoImpl extends GenericDaoDefault<WxActSpinwinRecord> implements WxActSpinwinRecordDao{

	@Override
	public Integer count(PageQuery<WxActSpinwinRecord> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> queryPageList(
			PageQuery<WxActSpinwinRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwinRecord> wrapper = new PageQueryWrapper<WxActSpinwinRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinRecord>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> queryPageListForJoin(PageQuery<WxActSpinwinRecord> pageQuery, Integer itemCount) {

		PageQueryWrapper<WxActSpinwinRecord> wrapper = new PageQueryWrapper<WxActSpinwinRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinRecord>) super.query("queryPageListForJoin",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> queryBargainRecordListByOpenidAndActidAndJwid(String openid, String actId,
			String jwid, Date currDate) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
		if(currDate==null){
			return (List<WxActSpinwinRecord>)super.query("queryBargainRecordListByOpenidAndActidAndJwid",param);
		}
		param.put("currDate", DateUtil.date2Str(currDate,"yyyy-MM-dd"));
		return (List<WxActSpinwinRecord>)super.query("queryBargainRecordListByOpenidAndActidAndJwidAndCurdate",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> queryMyAwardsByOpenidAndActidAndJwid(String openid, String actId, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (List<WxActSpinwinRecord>)super.query("queryMyAwardsByOpenidAndActidAndJwid",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> queryBargainRecordListByActidAndJwid(String actId, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (List<WxActSpinwinRecord>)super.query("queryBargainRecordListByActidAndJwid",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRecord> exportRecordListByActidAndJwid(String actId, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (List<WxActSpinwinRecord>)super.query("exportRecordListByActidAndJwid",param);
	}

	@Override
	public Integer getMaxAwardsSeq(String actid) {

		Integer maxAwardsSeq = (Integer) super.queryOne("getMaxSeq",actid);
		return maxAwardsSeq==null? 0:maxAwardsSeq;
	}


}

