package com.jeecg.p3.spinwin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwin;

/**
 * 描述：</b>WxActSpinwinDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinDao extends GenericDao<WxActSpinwin>{
	
	public Integer count(PageQuery<WxActSpinwin> pageQuery);
	
	public List<WxActSpinwin> queryPageList(PageQuery<WxActSpinwin> pageQuery,Integer itemCount);

	public List<WxActSpinwin> queryActs(String jwid);
}

