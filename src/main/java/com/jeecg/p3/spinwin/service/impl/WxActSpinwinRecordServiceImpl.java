package com.jeecg.p3.spinwin.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.spinwin.dao.WxActSpinwinPrizesDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRecordDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRegistrationDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;
import com.jeecg.p3.spinwin.service.WxActSpinwinRecordService;
import com.jeecg.p3.spinwin.util.ExcelUtil;

@Service("wxActSpinwinRecordService")
public class WxActSpinwinRecordServiceImpl implements WxActSpinwinRecordService {
	@Resource
	private WxActSpinwinRecordDao wxActSpinwinRecordDao;

	@Resource
	private WxActSpinwinPrizesDao wxActSpinwinPrizesDao;

	@Resource
	private WxActSpinwinRegistrationDao wxActSpinwinRegistrationDao;

	@Resource
	private WxActSpinwinRelationDao wxActSpinwinRelationDao;

	@Override
	public void doAdd(WxActSpinwinRecord wxActSpinwinRecord) {
		wxActSpinwinRecordDao.add(wxActSpinwinRecord);
	}

	@Override
	public void doEdit(WxActSpinwinRecord wxActSpinwinRecord) {
		wxActSpinwinRecordDao.update(wxActSpinwinRecord);
	}

	@Override
	public void doDelete(String id) {
		wxActSpinwinRecordDao.delete(id);
	}

	@Override
	public WxActSpinwinRecord queryById(String id) {
		WxActSpinwinRecord wxActSpinwinRecord  = wxActSpinwinRecordDao.get(id);
		return wxActSpinwinRecord;
	}

	@Override
	public PageList<WxActSpinwinRecord> queryPageList(
		PageQuery<WxActSpinwinRecord> pageQuery) {
		PageList<WxActSpinwinRecord> result = new PageList<WxActSpinwinRecord>();
		Integer itemCount = wxActSpinwinRecordDao.count(pageQuery);
		List<WxActSpinwinRecord> list = wxActSpinwinRecordDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public PageList<WxActSpinwinRecord> queryPageListForJoin(PageQuery<WxActSpinwinRecord> pageQuery) {

		PageList<WxActSpinwinRecord> result = new PageList<WxActSpinwinRecord>();
		Integer itemCount = wxActSpinwinRecordDao.count(pageQuery);
		List<WxActSpinwinRecord> list = wxActSpinwinRecordDao.queryPageListForJoin(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActSpinwinRecord> queryBargainRecordListByOpenidAndActidAndJwid(String openid, String actId,
			String jwid, Date currDate) {
		return wxActSpinwinRecordDao.queryBargainRecordListByOpenidAndActidAndJwid(openid, actId, jwid, currDate);
	}

	@Override
	public List<WxActSpinwinRecord> queryMyAwardsByOpenidAndActidAndJwid(String openid, String actId, String jwid) {
		return wxActSpinwinRecordDao.queryMyAwardsByOpenidAndActidAndJwid(openid, actId, jwid);
	}

	@Override
	public List<WxActSpinwinRecord> queryBargainRecordListByActidAndJwid(String actId, String jwid) {
		return wxActSpinwinRecordDao.queryBargainRecordListByActidAndJwid(actId, jwid);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public WxActSpinwinPrizes creatAwards(WxActSpinwinRecord wxActSpinwinRecord) {

		//获取中奖序号
		Integer maxAwardsSeq = wxActSpinwinRecordDao.getMaxAwardsSeq(wxActSpinwinRecord.getActId());
		Integer nextAwardsSeq = maxAwardsSeq+1;
		wxActSpinwinRecord.setSeq(nextAwardsSeq);
		wxActSpinwinRecordDao.add(wxActSpinwinRecord);
		//查询访问人的信息 
		WxActSpinwinRegistration wxActSpinwinRegistration =  wxActSpinwinRegistrationDao.queryRegistrationByOpenidAndActIdAndJwid(wxActSpinwinRecord.getOpenid(), wxActSpinwinRecord.getActId(), wxActSpinwinRecord.getJwid());
		wxActSpinwinRegistration.setAwardsStatus("1");
		wxActSpinwinRegistration.setAwardsNum(wxActSpinwinRegistration.getAwardsNum()+1);
		wxActSpinwinRegistrationDao.update(wxActSpinwinRegistration);//重置状态	
		if(StringUtils.isEmpty(wxActSpinwinRecord.getAwardsId())){
			return new WxActSpinwinPrizes();
		}else{
			wxActSpinwinRelationDao.updateRemainNum(wxActSpinwinRecord.getActId(), wxActSpinwinRecord.getJwid(), wxActSpinwinRecord.getAwardsId());
			return wxActSpinwinPrizesDao.queryByAwardIdAndActId(wxActSpinwinRecord.getAwardsId(),wxActSpinwinRecord.getActId()).get(0);
		}
	}

	@Override
	public InputStream exportExcel(String actId, String jwid) throws FileNotFoundException {

		List<WxActSpinwinRecord> listUser = wxActSpinwinRecordDao.exportRecordListByActidAndJwid(actId, jwid);
		File file = new File(new Date(0).getTime()+".xls");
		OutputStream outputStream;
		outputStream = new FileOutputStream(file);
		ExcelUtil.exportExcel("导出信息", WxActSpinwinRecord.class, listUser, outputStream);
		InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));
		file.delete();
		return is;
	}
	
}
