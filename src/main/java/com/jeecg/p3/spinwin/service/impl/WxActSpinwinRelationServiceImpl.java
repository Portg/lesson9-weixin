package com.jeecg.p3.spinwin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.spinwin.service.WxActSpinwinRelationService;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;

@Service("wxActSpinwinRelationService")
public class WxActSpinwinRelationServiceImpl implements WxActSpinwinRelationService {
	@Resource
	private WxActSpinwinRelationDao wxActSpinwinRelationDao;

	@Override
	public void doAdd(WxActSpinwinRelation wxActSpinwinRelation) {
		wxActSpinwinRelationDao.add(wxActSpinwinRelation);
	}

	@Override
	public void doEdit(WxActSpinwinRelation wxActSpinwinRelation) {
		wxActSpinwinRelationDao.update(wxActSpinwinRelation);
	}

	@Override
	public void doDelete(String id) {
		wxActSpinwinRelationDao.delete(id);
	}

	@Override
	public WxActSpinwinRelation queryById(String id) {
		WxActSpinwinRelation wxActSpinwinRelation  = wxActSpinwinRelationDao.get(id);
		return wxActSpinwinRelation;
	}

	@Override
	public PageList<WxActSpinwinRelation> queryPageList(
		PageQuery<WxActSpinwinRelation> pageQuery) {
		PageList<WxActSpinwinRelation> result = new PageList<WxActSpinwinRelation>();
		Integer itemCount = wxActSpinwinRelationDao.count(pageQuery);
		List<WxActSpinwinRelation> list = wxActSpinwinRelationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActSpinwinRelation> queryByActIdAndJwid(String actid, String jwid) {
		return wxActSpinwinRelationDao.queryByActIdAndJwid(actid, jwid);
	}
	
}
