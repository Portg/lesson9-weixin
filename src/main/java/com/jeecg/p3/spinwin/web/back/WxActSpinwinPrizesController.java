package com.jeecg.p3.spinwin.web.back;

import java.io.File;

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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.service.WxActSpinwinPrizesService;
import com.jeecg.p3.spinwin.util.ContextHolderUtils;

/**
 * 描述：</b>WxActSpinwinPrizesController<br>奖品
 * @author junfeng.zhou
 * @since：2016年03月03日 10时04分01秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/spinwin/back/wxActSpinwinPrizes")
public class WxActSpinwinPrizesController extends BaseController {

	@Autowired
	private WxActSpinwinPrizesService wxActSpinwinPrizesService;

	/**
	  * 列表页面
	  * @return
	 */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute WxActSpinwinPrizes query,HttpServletResponse response,HttpServletRequest request,
				@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
				@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
		PageQuery<WxActSpinwinPrizes> pageQuery = new PageQuery<WxActSpinwinPrizes>();
		pageQuery.setPageNo(pageNo);
		pageQuery.setPageSize(pageSize);
		VelocityContext velocityContext = new VelocityContext();
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActSpinwinPrizes",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActSpinwinPrizesService.queryPageList(pageQuery)));
		velocityContext.put("jwid",jwid);
		String viewName = "spinwin/back/wxActSpinwinPrizes-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	  * 详情
	  * @return
	 */
	@RequestMapping(value="toDetail",method = RequestMethod.GET)
	public void wxActSpinwinPrizesDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/back/wxActSpinwinPrizes-detail.vm";
		WxActSpinwinPrizes wxActSpinwinPrizes = wxActSpinwinPrizesService.queryById(id);
		velocityContext.put("wxActSpinwinPrizes",wxActSpinwinPrizes);
		String jwid = ContextHolderUtils.getSession().getAttribute("jwid").toString();
		velocityContext.put("jwid",jwid);
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String sessionId = ContextHolderUtils.getSession().getId();
		velocityContext.put("sessionId",sessionId);
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		velocityContext.put("jwid",jwid);
		String viewName = "spinwin/back/wxActSpinwinPrizes-add.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute WxActSpinwinPrizes wxActSpinwinPrizes){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinPrizesService.doAdd(wxActSpinwinPrizes);
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
		WxActSpinwinPrizes wxActSpinwinPrizes = wxActSpinwinPrizesService.queryById(id);
		velocityContext.put("wxActSpinwinPrizes",wxActSpinwinPrizes);
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		velocityContext.put("jwid",jwid);
		String viewName = "spinwin/back/wxActSpinwinPrizes-edit.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute WxActSpinwinPrizes wxActSpinwinPrizes){
		AjaxJson j = new AjaxJson();
		try {
			wxActSpinwinPrizesService.doEdit(wxActSpinwinPrizes);
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
			//判断奖项是否被使用
			Boolean used = wxActSpinwinPrizesService.validUsed(id);
			if(used) {
				j.setSuccess(false);
				j.setMsg("该奖品已经被活动使用，不能删除");
			} else {
				wxActSpinwinPrizesService.doDelete(id);
				j.setMsg("删除成功");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(value = "/doUpload",method ={RequestMethod.POST})
	@ResponseBody
	public AjaxJson doUpload(MultipartHttpServletRequest request,HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		try {
			MultipartFile uploadify = request.getFile("file");
			byte[] bytes = uploadify.getBytes();
			String realFilename=uploadify.getOriginalFilename();
			String fileNoExtension = realFilename.substring(0,realFilename.lastIndexOf("."));
			String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
			String filename=fileNoExtension+System.currentTimeMillis()+fileExtension;
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
			String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/spinwin/"+jwid);
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			String sep = System.getProperty("file.separator");
			File uploadedFile = new File(uploadDir + sep + filename);
			FileCopyUtils.copy(bytes, uploadedFile);  
			j.setObj(filename);
			j.setSuccess(true);
			j.setMsg("保存成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

}
