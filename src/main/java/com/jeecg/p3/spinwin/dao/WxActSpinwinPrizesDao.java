package com.jeecg.p3.spinwin.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;

/**
 * 描述：</b>WxActSpinwinPrizesDao<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinPrizesDao extends GenericDao<WxActSpinwinPrizes>{
	
	public Integer count(PageQuery<WxActSpinwinPrizes> pageQuery);
	
	public List<WxActSpinwinPrizes> queryPageList(PageQuery<WxActSpinwinPrizes> pageQuery,Integer itemCount);

	public List<WxActSpinwinPrizes> queryByActId(String actid);
	public List<WxActSpinwinPrizes> queryRemainAwardsByActId(String actid);
	public List<WxActSpinwinPrizes> queryByAwardIdAndActId(String awardid,String actId);
	public List<WxActSpinwinPrizes> queryPrizes(String jwid);
}

