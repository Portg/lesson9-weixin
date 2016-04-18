package com.jeecg.p3.spinwin.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;

/**
 * 描述：</b>WxActSpinwinPrizesService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinPrizesService {
	
	
	public void doAdd(WxActSpinwinPrizes wxActSpinwinPrizes);
	
	public void doEdit(WxActSpinwinPrizes wxActSpinwinPrizes);
	
	public void doDelete(String id);
	
	public WxActSpinwinPrizes queryById(String id);
	
	public PageList<WxActSpinwinPrizes> queryPageList(PageQuery<WxActSpinwinPrizes> pageQuery);

	public List<WxActSpinwinPrizes> queryByActId(String actid);
	public List<WxActSpinwinPrizes> queryRemainAwardsByActId(String actid);
	public List<WxActSpinwinPrizes> queryByAwardIdAndActId(String awardid,String actId);

	public List<WxActSpinwinPrizes> queryPrizes(String jwid);
	public Boolean validUsed(String id);
}

