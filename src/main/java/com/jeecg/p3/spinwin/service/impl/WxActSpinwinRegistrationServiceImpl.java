package com.jeecg.p3.spinwin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.spinwin.dao.WxActSpinwinRegistrationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;
import com.jeecg.p3.spinwin.service.WxActSpinwinRegistrationService;

@Service("wxActSpinwinRegistrationService")
public class WxActSpinwinRegistrationServiceImpl implements WxActSpinwinRegistrationService {
	@Resource
	private WxActSpinwinRegistrationDao wxActSpinwinRegistrationDao;

	@Override
	public void doAdd(WxActSpinwinRegistration wxActSpinwinRegistration) {
		wxActSpinwinRegistrationDao.add(wxActSpinwinRegistration);
	}

	@Override
	public void doEdit(WxActSpinwinRegistration wxActSpinwinRegistration) {
		wxActSpinwinRegistrationDao.update(wxActSpinwinRegistration);
	}

	@Override
	public void doDelete(String id) {
		wxActSpinwinRegistrationDao.delete(id);
	}

	@Override
	public WxActSpinwinRegistration queryById(String id) {
		WxActSpinwinRegistration wxActSpinwinRegistration  = wxActSpinwinRegistrationDao.get(id);
		return wxActSpinwinRegistration;
	}

	@Override
	public PageList<WxActSpinwinRegistration> queryPageList(
		PageQuery<WxActSpinwinRegistration> pageQuery) {
		PageList<WxActSpinwinRegistration> result = new PageList<WxActSpinwinRegistration>();
		Integer itemCount = wxActSpinwinRegistrationDao.count(pageQuery);
		List<WxActSpinwinRegistration> list = wxActSpinwinRegistrationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActSpinwinRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid, String actId, String jwid) {

		return wxActSpinwinRegistrationDao.queryRegistrationByOpenidAndActIdAndJwid(openid, actId, jwid);
	}

	@Override
	public void add(WxActSpinwinRegistration wxActSpinwinRegistration) {
		wxActSpinwinRegistrationDao.add(wxActSpinwinRegistration);
	}
	
}
