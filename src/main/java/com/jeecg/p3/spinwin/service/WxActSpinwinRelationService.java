package com.jeecg.p3.spinwin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;

/**
 * 描述：</b>WxActSpinwinRelationService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRelationService {
	
	
	public void doAdd(WxActSpinwinRelation wxActSpinwinRelation);
	
	public void doEdit(WxActSpinwinRelation wxActSpinwinRelation);
	
	public void doDelete(String id);
	
	public WxActSpinwinRelation queryById(String id);
	
	public PageList<WxActSpinwinRelation> queryPageList(PageQuery<WxActSpinwinRelation> pageQuery);

	public List<WxActSpinwinRelation> queryByActIdAndJwid(String actid,String jwid);
}

