package com.jeecg.p3.spinwin.web.back;

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

import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;
import com.jeecg.p3.spinwin.service.WxActSpinwinAwardsService;
import com.jeecg.p3.spinwin.util.ContextHolderUtils;

/**
    * 描述：</b>WxActSpinwinAwardsController<br>奖项
    * @author junfeng.zhou
    * @since：2016年03月03日 10时04分02秒 星期四 
    * @version:1.0
*/
@Controller
@RequestMapping("/spinwin/back/wxActSpinwinAwards")
public class WxActSpinwinAwardsController extends BaseController {

	@Autowired
	private WxActSpinwinAwardsService wxActSpinwinAwardsService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxActSpinwinAwards query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		PageQuery<WxActSpinwinAwards> pageQuery = new PageQuery<WxActSpinwinAwards>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActSpinwinAwards",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActSpinwinAwardsService.queryPageList(pageQuery)));
		String viewName = "spinwin/back/wxActSpinwinAwards-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 详情
	 * @return
	 */
	@RequestMapping(value="toDetail",method = RequestMethod.GET)
	public void wxActSpinwinAwardsDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/back/wxActSpinwinAwards-detail.vm";
		WxActSpinwinAwards wxActSpinwinAwards = wxActSpinwinAwardsService.queryById(id);
		velocityContext.put("wxActSpinwinAwards",wxActSpinwinAwards);
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "spinwin/back/wxActSpinwinAwards-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WxActSpinwinAwards wxActSpinwinAwards){
		AjaxJson j = new AjaxJson();
		try {
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			//奖项值验重，统一jwid下不能重复
			Boolean repeat = wxActSpinwinAwardsService.validReat(wxActSpinwinAwards.getAwardsValue(),jwid);
			if(repeat){
				j.setSuccess(false);
				j.setMsg("奖项值重复，请重新设置");
			} else { 
				wxActSpinwinAwardsService.doAdd(wxActSpinwinAwards);
				j.setMsg("保存成功");
			}
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
		WxActSpinwinAwards wxActSpinwinAwards = wxActSpinwinAwardsService.queryById(id);
		velocityContext.put("wxActSpinwinAwards",wxActSpinwinAwards);
		String viewName = "spinwin/back/wxActSpinwinAwards-edit.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WxActSpinwinAwards wxActSpinwinAwards){
		AjaxJson j = new AjaxJson();
		try {
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			//奖项值验重，统一jwid下不能重复
			Boolean repeat = wxActSpinwinAwardsService.validReat(wxActSpinwinAwards.getAwardsValue(),jwid);
			if(repeat){
				j.setSuccess(false);
				j.setMsg("奖项值重复，请重新设置");
			} else {
				wxActSpinwinAwardsService.doEdit(wxActSpinwinAwards);
				j.setMsg("编辑成功");
			}
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
				//判断奖项是否被使用
				Boolean used = wxActSpinwinAwardsService.validUsed(id);
				if(used){
					j.setSuccess(false);
					j.setMsg("该奖项已经被活动使用，不能删除");
				}else{
					wxActSpinwinAwardsService.doDelete(id);
					j.setMsg("删除成功");
				}
			} catch (Exception e) {
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}

}

