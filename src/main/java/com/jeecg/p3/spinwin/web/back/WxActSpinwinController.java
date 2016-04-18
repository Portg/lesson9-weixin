package com.jeecg.p3.spinwin.web.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.spinwin.entity.WxActSpinwin;
import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;
import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRelation;
import com.jeecg.p3.spinwin.service.WxActSpinwinAwardsService;
import com.jeecg.p3.spinwin.service.WxActSpinwinPrizesService;
import com.jeecg.p3.spinwin.service.WxActSpinwinRelationService;
import com.jeecg.p3.spinwin.service.WxActSpinwinService;
import com.jeecg.p3.spinwin.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActSpinwinController<br>幸运大转盘活动配置
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/spinwin/back/wxActSpinwin")
public class WxActSpinwinController extends BaseController{
	
	@Autowired
	private WxActSpinwinService wxActSpinwinService;

	@Autowired
	private WxActSpinwinAwardsService wxActSpinwinAwardsService;

	@Autowired
	private WxActSpinwinPrizesService wxActSpinwinPrizesService;

	@Autowired
	private WxActSpinwinRelationService wxActSpinwinRelationService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxActSpinwin query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		PageQuery<WxActSpinwin> pageQuery = new PageQuery<WxActSpinwin>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActSpinwin",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActSpinwinService.queryPageList(pageQuery)));
		String viewName = "spinwin/back/wxActSpinwin-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 详情
	 * @return
	  */
	@RequestMapping(value="toDetail",method = RequestMethod.GET)
	public void wxActSpinwinDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/back/wxActSpinwin-detail.vm";
		WxActSpinwin wxActSpinwin = wxActSpinwinService.queryById(id);
		velocityContext.put("wxActSpinwin",wxActSpinwin);
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();

		List<WxActSpinwinRelation> awarsDetailList= wxActSpinwinRelationService.queryByActIdAndJwid(id,jwid);
		velocityContext.put("awarsDetailList",awarsDetailList);
		List<WxActSpinwinAwards> awards = wxActSpinwinAwardsService.queryAwards(jwid);
		velocityContext.put("awards",awards);
		List<WxActSpinwinPrizes> prizes = wxActSpinwinPrizesService.queryPrizes(jwid);
		velocityContext.put("prizes",prizes);

		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		List<WxActSpinwinAwards> awards = wxActSpinwinAwardsService.queryAwards(jwid);
		velocityContext.put("awards",awards);
		List<WxActSpinwinPrizes> prizes = wxActSpinwinPrizesService.queryPrizes(jwid);
		velocityContext.put("prizes",prizes);
		String viewName = "spinwin/back/wxActSpinwin-add.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WxActSpinwin wxActSpinwin){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinService.doAdd(wxActSpinwin);
			j.setMsg("保存成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(value="toEdit",method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		WxActSpinwin wxActSpinwin = wxActSpinwinService.queryById(id);
		velocityContext.put("wxActSpinwin",wxActSpinwin);

		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		List<WxActSpinwinRelation> awarsDetailList = wxActSpinwinRelationService.queryByActIdAndJwid(id,jwid);
		velocityContext.put("awarsDetailList",awarsDetailList);

		List<WxActSpinwinAwards> awards = wxActSpinwinAwardsService.queryAwards(jwid);
		velocityContext.put("awards",awards);

		List<WxActSpinwinPrizes> prizes = wxActSpinwinPrizesService.queryPrizes(jwid);
		velocityContext.put("prizes",prizes);

		String viewName = "spinwin/back/wxActSpinwin-edit.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WxActSpinwin wxActSpinwin){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinService.doEdit(wxActSpinwin);
			j.setMsg("编辑成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}

	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(value="doDelete",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

}
