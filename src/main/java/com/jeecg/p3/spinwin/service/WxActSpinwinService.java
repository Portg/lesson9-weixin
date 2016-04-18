package com.jeecg.p3.spinwin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwin;

/**
 * 描述：</b>WxActSpinwinService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinService {
	
	
	public void doAdd(WxActSpinwin wxActSpinwin);
	
	public void doEdit(WxActSpinwin wxActSpinwin);
	
	public void doDelete(String id);
	
	public WxActSpinwin queryById(String id);
	
	public PageList<WxActSpinwin> queryPageList(PageQuery<WxActSpinwin> pageQuery);

	public List<WxActSpinwin> queryActs(String jwid);
}

