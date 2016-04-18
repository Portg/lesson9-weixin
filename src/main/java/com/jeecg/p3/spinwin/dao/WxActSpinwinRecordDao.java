package com.jeecg.p3.spinwin.dao;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;

/**
 * 描述：</b>WxActSpinwinRecordDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRecordDao extends GenericDao<WxActSpinwinRecord>{
	
	public Integer count(PageQuery<WxActSpinwinRecord> pageQuery);
	
	public List<WxActSpinwinRecord> queryPageList(PageQuery<WxActSpinwinRecord> pageQuery,Integer itemCount);

	public List<WxActSpinwinRecord> queryPageListForJoin(PageQuery<WxActSpinwinRecord> pageQuery,Integer itemCount);
	public List<WxActSpinwinRecord> queryBargainRecordListByOpenidAndActidAndJwid(String openid,String actId,String jwid,Date currDate);
	public List<WxActSpinwinRecord> queryMyAwardsByOpenidAndActidAndJwid(String openid,String actId,String jwid);
	public List<WxActSpinwinRecord> queryBargainRecordListByActidAndJwid(String actId,String jwid);
	public List<WxActSpinwinRecord> exportRecordListByActidAndJwid(String actId,String jwid);
	
	public Integer getMaxAwardsSeq(String actid);

}

