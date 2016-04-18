package com.jeecg.p3.spinwin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;

/**
 * 描述：</b>WxActSpinwinRelationDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRelationDao extends GenericDao<WxActSpinwinRelation>{
	
	public Integer count(PageQuery<WxActSpinwinRelation> pageQuery);
	
	public List<WxActSpinwinRelation> queryPageList(PageQuery<WxActSpinwinRelation> pageQuery,Integer itemCount);

	public List<WxActSpinwinRelation> queryByActIdAndJwid(String actid,String jwid);

	public void updateRemainNum(String actid,String jwid,String awardid);
	
	public void batchDeleteByActId( String actid);
	public void bactchDeleteOldAwards(List<String> ids,String actId) ;
	
	public Boolean validUsed(String awadsid,String prizeid);

}

