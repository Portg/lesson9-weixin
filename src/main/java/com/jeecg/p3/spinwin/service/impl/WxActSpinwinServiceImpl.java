package com.jeecg.p3.spinwin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.spinwin.dao.WxActSpinwinDao;
import com.jeecg.p3.spinwin.dao.WxActSpinwinRelationDao;
import com.jeecg.p3.spinwin.entity.WxActSpinwin;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;
import com.jeecg.p3.spinwin.service.WxActSpinwinService;

@Service("wxActSpinwinService")
public class WxActSpinwinServiceImpl implements WxActSpinwinService {
	@Resource
	private WxActSpinwinDao wxActSpinwinDao;

	@Resource
	private WxActSpinwinRelationDao wxActSpinwinRelationDao;

	@Autowired
	private SystemActTxtService systemActTxtService;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAdd(WxActSpinwin wxActSpinwin) {
		wxActSpinwinDao.add(wxActSpinwin);
		List<WxActSpinwinRelation> awardsList= wxActSpinwin.getAwarsList();
		if(awardsList!=null){
			for (WxActSpinwinRelation wxActSpinwinRelation : awardsList) {
				wxActSpinwinRelation.setActId(wxActSpinwin.getId());
				if(wxActSpinwinRelation.getProbability()==null){
					wxActSpinwinRelation.setProbability(0d);
				}
			}
			wxActSpinwinRelationDao.batchInsert("insert", awardsList);
		}
		systemActTxtService.doCopyTxt(WeiXinHttpUtil.getLocalValue("Spinwin", WeiXinHttpUtil.TXT_ACTID_KEY), wxActSpinwin.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doEdit(WxActSpinwin wxActSpinwin) {
		wxActSpinwinDao.update(wxActSpinwin);
		List<WxActSpinwinRelation> newAwardsList= wxActSpinwin.getAwarsList();//新的明细配置集合
		List<String> ids=new ArrayList<String>();
		if(newAwardsList!=null){
			for (WxActSpinwinRelation relation : newAwardsList) {
				if(StringUtils.isNotEmpty(relation.getId())){
					ids.add(relation.getId());
				}
			}
			wxActSpinwinRelationDao.bactchDeleteOldAwards(ids, wxActSpinwin.getId());//批量删除不在新的明细配置集合的数据
			for (WxActSpinwinRelation wxActSpinwinRelation : newAwardsList) {
				if(StringUtils.isEmpty(wxActSpinwinRelation.getId())){
					wxActSpinwinRelation.setActId(wxActSpinwin.getId());
					wxActSpinwinRelationDao.add(wxActSpinwinRelation);
				}else{
					wxActSpinwinRelationDao.update(wxActSpinwinRelation);
				}
			}
		}else{
			wxActSpinwinRelationDao.bactchDeleteOldAwards(ids, wxActSpinwin.getId());//批量删除不在新的明细配置集合的数据
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doDelete(String id) {
		wxActSpinwinDao.delete(id);
		wxActSpinwinRelationDao.batchDeleteByActId(id);//同步活动明细配置
		systemActTxtService.batchDeleteByActCode(id);//同步删除系统文本
	}

	@Override
	public WxActSpinwin queryById(String id) {
		WxActSpinwin wxActSpinwin  = wxActSpinwinDao.get(id);
		return wxActSpinwin;
	}

	@Override
	public PageList<WxActSpinwin> queryPageList(
		PageQuery<WxActSpinwin> pageQuery) {
		PageList<WxActSpinwin> result = new PageList<WxActSpinwin>();
		Integer itemCount = wxActSpinwinDao.count(pageQuery);
		List<WxActSpinwin> list = wxActSpinwinDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActSpinwin> queryActs(String jwid) {
		return wxActSpinwinDao.queryActs(jwid);
	}
	
}
