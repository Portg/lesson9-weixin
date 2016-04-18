package com.jeecg.p3.spinwin.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;

/**
 * 描述：</b>WxActSpinwinRelationDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
@Repository("wxActSpinwinRelationDao")
public class WxActSpinwinRelationDaoImpl extends GenericDaoDefault<WxActSpinwinRelation> implements WxActSpinwinRelationDao{

	@Override
	public Integer count(PageQuery<WxActSpinwinRelation> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRelation> queryPageList(
			PageQuery<WxActSpinwinRelation> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActSpinwinRelation> wrapper = new PageQueryWrapper<WxActSpinwinRelation>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActSpinwinRelation>)super.query("queryPageList",wrapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActSpinwinRelation> queryByActIdAndJwid(String actid, String jwid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		param.put("jwid", jwid);
		return (List<WxActSpinwinRelation>)super.query("queryByActIdAndJwid",param);
	}

	@Override
	public void updateRemainNum(String actid, String jwid, String awardid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		param.put("jwid", jwid);
		param.put("awardid", awardid);
		super.update("updateRemainNum", param);
	}

	@Override
	public void batchDeleteByActId(String actid) {

		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		super.delete("batchDeleteByActId", param);
	}

	@Override
	public void bactchDeleteOldAwards(List<String> ids, String actId) {
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("ids", ids);
		param.put("actId", actId);
		super.delete("bactchDeleteOldAwards",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean validUsed(String awadsid, String prizeid) {

		Map<String,String> param = Maps.newConcurrentMap();
		if(StringUtils.isEmpty(awadsid)){
			param.put("prizeId", prizeid);
		} else {
			param.put("awardId", awadsid);
		}
		List<WxActSpinwinRelation> relations = (List<WxActSpinwinRelation>)super.query("validUsed",param);
		if(relations.size() > 0) {
			return true;
		}else{
			return false;
		}
	}

}
