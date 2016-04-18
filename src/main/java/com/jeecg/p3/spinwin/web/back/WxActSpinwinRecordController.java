package com.jeecg.p3.spinwin.web.back;

import java.io.InputStream;
import java.io.OutputStream;
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
import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;
import com.jeecg.p3.spinwin.service.WxActSpinwinAwardsService;
import com.jeecg.p3.spinwin.service.WxActSpinwinRecordService;
import com.jeecg.p3.spinwin.service.WxActSpinwinService;
import com.jeecg.p3.spinwin.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActSpinwinRecordController<br>奖品记录
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分02秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/spinwin/back/wxActSpinwinRecord")
public class WxActSpinwinRecordController extends BaseController{
	
	@Autowired
	private WxActSpinwinRecordService wxActSpinwinRecordService;

	@Autowired
	private WxActSpinwinAwardsService wxActSpinwinAwardsService;
	@Autowired
	
	private WxActSpinwinService wxActSpinwinService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxActSpinwinRecord query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		PageQuery<WxActSpinwinRecord> pageQuery = new PageQuery<WxActSpinwinRecord>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActSpinwinRecord",query);
		List<WxActSpinwin> acts = (List<WxActSpinwin>) wxActSpinwinService.queryActs(jwid);//活动
		velocityContext.put("acts",acts);
		List<WxActSpinwinAwards> awards = wxActSpinwinAwardsService.queryAwards(jwid);//奖项
		velocityContext.put("awards",awards);
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActSpinwinRecordService.queryPageList(pageQuery)));
		String viewName = "spinwin/back/wxActSpinwinRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 详情
	 * @return
	 */
	@RequestMapping(value="toDetail",method = RequestMethod.GET)
	public void wxActSpinwinRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/back/wxActSpinwinRecord-detail.vm";
		WxActSpinwinRecord wxActSpinwinRecord = wxActSpinwinRecordService.queryById(id);
		velocityContext.put("wxActSpinwinRecord",wxActSpinwinRecord);
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	*/
	@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/back/wxActSpinwinRecord-add.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WxActSpinwinRecord wxActSpinwinRecord){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinRecordService.doAdd(wxActSpinwinRecord);
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
		WxActSpinwinRecord wxActSpinwinRecord = wxActSpinwinRecordService.queryById(id);
		velocityContext.put("wxActSpinwinRecord",wxActSpinwinRecord);
		String viewName = "spinwin/back/wxActSpinwinRecord-edit.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WxActSpinwinRecord wxActSpinwinRecord){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinRecordService.doEdit(wxActSpinwinRecord);
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
			wxActSpinwinRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

	/** 
	 * 导出Excel 
	 * @return 
	 *  
	 */ 
	@RequestMapping(value = "/exportExcel")
	public AjaxJson exportExcel(HttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		String fileName = "导出信息.xls";  
		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			String actId =  ContextHolderUtils.getRequest().getParameter("actId");//返回时的url
			InputStream inputStream = wxActSpinwinRecordService.exportExcel(actId,jwid); 
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
			j.setMsg("导出成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("导出失败");
		}
		return j;
	}

}
