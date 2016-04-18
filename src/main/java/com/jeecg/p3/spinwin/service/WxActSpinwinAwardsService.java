package com.jeecg.p3.spinwin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;

/**
 * 描述：</b>WxActSpinwinAwardsService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinAwardsService {
	
	
	public void doAdd(WxActSpinwinAwards wxActSpinwinAwards);
	
	public void doEdit(WxActSpinwinAwards wxActSpinwinAwards);
	
	public void doDelete(String id);
	
	public WxActSpinwinAwards queryById(String id);
	
	public PageList<WxActSpinwinAwards> queryPageList(PageQuery<WxActSpinwinAwards> pageQuery);

	public List<WxActSpinwinAwards> queryAwards(String jwid);
	
	public Boolean validUsed(String id);

	public Boolean validReat(int value,String jwid);

	public Boolean validReat(String id,int value,String jwid);
}

