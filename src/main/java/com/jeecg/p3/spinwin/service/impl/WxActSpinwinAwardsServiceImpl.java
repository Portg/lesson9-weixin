package com.jeecg.p3.spinwin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.spinwin.dao.WxActSpinwinAwardsDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;
import com.jeecg.p3.spinwin.service.WxActSpinwinAwardsService;

@Service("wxActSpinwinAwardsService")
public class WxActSpinwinAwardsServiceImpl implements WxActSpinwinAwardsService {
	@Resource
	private WxActSpinwinAwardsDao wxActSpinwinAwardsDao;

	@Resource
	private WxActSpinwinRelationDao wxActSpinwinRelationDao;

	@Override
	public void doAdd(WxActSpinwinAwards wxActSpinwinAwards) {
		wxActSpinwinAwardsDao.add(wxActSpinwinAwards);
	}

	@Override
	public void doEdit(WxActSpinwinAwards wxActSpinwinAwards) {
		wxActSpinwinAwardsDao.update(wxActSpinwinAwards);
	}

	@Override
	public void doDelete(String id) {
		wxActSpinwinAwardsDao.delete(id);
	}

	@Override
	public WxActSpinwinAwards queryById(String id) {
		WxActSpinwinAwards wxActSpinwinAwards  = wxActSpinwinAwardsDao.get(id);
		return wxActSpinwinAwards;
	}

	@Override
	public PageList<WxActSpinwinAwards> queryPageList(
		PageQuery<WxActSpinwinAwards> pageQuery) {
		PageList<WxActSpinwinAwards> result = new PageList<WxActSpinwinAwards>();
		Integer itemCount = wxActSpinwinAwardsDao.count(pageQuery);
		List<WxActSpinwinAwards> list = wxActSpinwinAwardsDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActSpinwinAwards> queryAwards(String jwid) {

		return wxActSpinwinAwardsDao.queryAwards(jwid);
	}

	@Override
	public Boolean validUsed(String id) {

		return wxActSpinwinRelationDao.validUsed(id,null);
	}

	@Override
	public Boolean validReat(int value, String jwid) {

		return wxActSpinwinAwardsDao.validReat(value, jwid);
	}

	@Override
	public Boolean validReat(String id, int value, String jwid) {

		return wxActSpinwinAwardsDao.validReat(id, value, jwid);
	}
	
}
