package com.jeecg.p3.spinwin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.spinwin.dao.WxActSpinwinPrizesDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.service.WxActSpinwinPrizesService;

@Service("wxActSpinwinPrizesService")
public class WxActSpinwinPrizesServiceImpl implements WxActSpinwinPrizesService {
	@Resource
	private WxActSpinwinPrizesDao wxActSpinwinPrizesDao;

	@Resource
	private WxActSpinwinRelationDao wxActSpinwinRelationDao;

	@Override
	public void doAdd(WxActSpinwinPrizes wxActSpinwinPrizes) {
		wxActSpinwinPrizesDao.add(wxActSpinwinPrizes);
	}

	@Override
	public void doEdit(WxActSpinwinPrizes wxActSpinwinPrizes) {
		wxActSpinwinPrizesDao.update(wxActSpinwinPrizes);
	}

	@Override
	public void doDelete(String id) {
		wxActSpinwinPrizesDao.delete(id);
	}

	@Override
	public WxActSpinwinPrizes queryById(String id) {
		WxActSpinwinPrizes wxActSpinwinPrizes  = wxActSpinwinPrizesDao.get(id);
		return wxActSpinwinPrizes;
	}

	@Override
	public PageList<WxActSpinwinPrizes> queryPageList(
		PageQuery<WxActSpinwinPrizes> pageQuery) {
		PageList<WxActSpinwinPrizes> result = new PageList<WxActSpinwinPrizes>();
		Integer itemCount = wxActSpinwinPrizesDao.count(pageQuery);
		List<WxActSpinwinPrizes> list = wxActSpinwinPrizesDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActSpinwinPrizes> queryByActId(String actid) {

		return wxActSpinwinPrizesDao.queryByActId(actid);
	}

	@Override
	public List<WxActSpinwinPrizes> queryRemainAwardsByActId(String actid) {

		return wxActSpinwinPrizesDao.queryRemainAwardsByActId(actid);
	}

	@Override
	public List<WxActSpinwinPrizes> queryByAwardIdAndActId(String awardid, String actId) {

		return wxActSpinwinPrizesDao.queryByAwardIdAndActId(awardid, actId);
	}

	@Override
	public List<WxActSpinwinPrizes> queryPrizes(String jwid) {

		return wxActSpinwinPrizesDao.queryPrizes(jwid);
	}

	@Override
	public Boolean validUsed(String id) {
		return wxActSpinwinRelationDao.validUsed(null, id);
	}
	
}
