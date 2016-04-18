package com.jeecg.p3.spinwin.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;

/**
 * 描述：</b>WxActSpinwinRecordService<br>
 * @author：junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
public interface WxActSpinwinRecordService {

	public void doAdd(WxActSpinwinRecord wxActSpinwinRecord);
	
	public void doEdit(WxActSpinwinRecord wxActSpinwinRecord);
	
	public void doDelete(String id);
	
	public WxActSpinwinRecord queryById(String id);
	
	public PageList<WxActSpinwinRecord> queryPageList(PageQuery<WxActSpinwinRecord> pageQuery);

	public PageList<WxActSpinwinRecord> queryPageListForJoin(PageQuery<WxActSpinwinRecord> pageQuery);
	
	public List<WxActSpinwinRecord> queryBargainRecordListByOpenidAndActidAndJwid(String openid,String actId,String jwid,Date currDate);
	public List<WxActSpinwinRecord> queryMyAwardsByOpenidAndActidAndJwid(String openid,String actId,String jwid);
	public List<WxActSpinwinRecord> queryBargainRecordListByActidAndJwid(String actId,String jwid);

	public WxActSpinwinPrizes creatAwards(WxActSpinwinRecord wxActSpinwinRecord);
	
	public InputStream  exportExcel(String actId,String jwid)throws FileNotFoundException;
}

