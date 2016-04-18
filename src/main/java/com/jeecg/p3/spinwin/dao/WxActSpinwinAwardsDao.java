package com.jeecg.p3.spinwin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;

/**
 * 描述：</b>WxActSpinwinAwardsDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinAwardsDao extends GenericDao<WxActSpinwinAwards>{
	
	public Integer count(PageQuery<WxActSpinwinAwards> pageQuery);
	
	public List<WxActSpinwinAwards> queryPageList(PageQuery<WxActSpinwinAwards> pageQuery,Integer itemCount);

	public List<WxActSpinwinAwards> queryAwards(String jwid);
	public Boolean validReat(int value,String jwid);
	public Boolean validReat(String id,int value,String jwid);
}

