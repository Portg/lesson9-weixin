package com.jeecg.p3.spinwin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.spinwin.entity.WxActSpinwin;
import com.jeecg.p3.spinwin.entity.WxActSpinwinAwards;
import com.jeecg.p3.spinwin.entity.WxActSpinwinPrizes;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRecord;
import com.jeecg.p3.spinwin.entity.WxActSpinwinRegistration;
import com.jeecg.p3.spinwin.exception.SpinwinException;
import com.jeecg.p3.spinwin.exception.SpinwinExceptionEnum;
import com.jeecg.p3.spinwin.service.WxActSpinwinAwardsService;
import com.jeecg.p3.spinwin.service.WxActSpinwinPrizesService;
import com.jeecg.p3.spinwin.service.WxActSpinwinRecordService;
import com.jeecg.p3.spinwin.service.WxActSpinwinRegistrationService;
import com.jeecg.p3.spinwin.service.WxActSpinwinService;
import com.jeecg.p3.spinwin.util.EmojiFilter;
import com.jeecg.p3.spinwin.util.LotteryUtil;

/**
 * 描述：幸运大转盘
 * 
 * @author junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四
 * @version:1.0
 */
@Controller
@RequestMapping("/spinwin")
public class SpinwinController extends BaseController {

	public final static Logger LOG = LoggerFactory
			.getLogger(SpinwinController.class);

	@Autowired
	private WxActSpinwinService wxActSpinwinService;

	@Autowired
	private WxActSpinwinPrizesService wxActSpinwinPrizesService;

	@Autowired
	private WxActSpinwinRegistrationService wxActSpinwinRegistrationService;

	@Autowired
	private WxActSpinwinRecordService wxActSpinwinRecordService;

	@Autowired
	private SystemActTxtService systemActTxtService;

	@Autowired
	private WxActSpinwinAwardsService wxActSpinwinAwardsService;
	/**
	 * 跳转到活动首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toIndex", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toIndex(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "toIndex parameter WeixinDto={}.",
				new Object[] { weixinDto });
		long start = System.currentTimeMillis();
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		String openId = weixinDto.getOpenid();
		if (weixinDto.getOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(openId, jwid);
			weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
		}
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/vm/index.vm";
		WxActSpinwin wxActSpinwin=null;
		try {
			// 参数验证
			validateWeixinDtoParam(weixinDto);
			// 获取活动信息
			wxActSpinwin = wxActSpinwinService
					.queryById(actId);
			if (wxActSpinwin == null) {
				throw new SpinwinException(
						SpinwinExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			if (!jwid.equals(wxActSpinwin.getJwid())) {
				throw new SpinwinException(
						SpinwinExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}
			velocityContext.put("spinwin", wxActSpinwin);
			// 有效期内可参与
			Date currDate = new Date();
			if (currDate.before(wxActSpinwin.getStarttime())) {
				String begainTime = DateUtil.convertToShowTime(wxActSpinwin
						.getStarttime());
				throw new SpinwinException(
						SpinwinExceptionEnum.ACT_SPINWIN_NO_START,
						"活动未开始,开始时间为" + begainTime + ",请耐心等待！");
			}
			if (currDate.after(wxActSpinwin.getEndtime())) {
				throw new SpinwinException(
						SpinwinExceptionEnum.ACT_SPINWIN_END, "活动已结束");
			}
			// 活动奖品
			List<WxActSpinwinPrizes> wxActSpinwinPrizesList = wxActSpinwinPrizesService
					.queryByActId(actId);
			Map<String,String> prizeMap = Maps.newConcurrentMap();
			int i=1;
			for (WxActSpinwinPrizes wxActSpinwinPrizes : wxActSpinwinPrizesList) {
				prizeMap.put("prizeImg"+i, wxActSpinwinPrizes.getImg());
				i++;
			}
			velocityContext.put("prizeMap", prizeMap);
			velocityContext.put("prizeList", wxActSpinwinPrizesList);
			if(wxActSpinwin.getNumPerDay()==0){//每天次数设置为0，代表不限制每天抽奖次数
				velocityContext.put("perday", 0);
			}
			// 根据访问人openid查询访问人的信息
			WxActSpinwinRegistration wxActSpinwinRegistration = wxActSpinwinRegistrationService
					.queryRegistrationByOpenidAndActIdAndJwid(
							openId, actId, jwid);
			if (wxActSpinwinRegistration == null) {
				wxActSpinwinRegistration = new WxActSpinwinRegistration();
				wxActSpinwinRegistration.setId(RandomUtils.generateID());
				wxActSpinwinRegistration.setActId(actId);
				wxActSpinwinRegistration.setOpenid(openId);
				wxActSpinwinRegistration.setNickname(weixinDto.getNickname());
				wxActSpinwinRegistration.setCreateTime(new Date());
				wxActSpinwinRegistration.setAwardsStatus("0");
				wxActSpinwinRegistration.setAwardsNum(0);
				wxActSpinwinRegistration.setJwid(jwid);
				wxActSpinwinRegistrationService
						.add(wxActSpinwinRegistration);// 如果当前访问人员不在参与活动的人员表中，则记录到参与活动人员表中
			}

			velocityContext.put("registration", wxActSpinwinRegistration);
			velocityContext.put("weixinDto", weixinDto);
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl", wxActSpinwin.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
			LOG.info(request, "toIndex time={}ms.",
					new Object[] { System.currentTimeMillis() - start });
		} catch (SpinwinException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("spinwin", wxActSpinwin);
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toIndex error:{}", e);
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("errCode", SpinwinExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg", SpinwinExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	private void validateWeixinDtoParam(WeixinDto weixinDto) {
		if (StringUtils.isEmpty(weixinDto.getActId())) {
			throw new SpinwinException(SpinwinExceptionEnum.ARGUMENT_ERROR,
					"活动ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getOpenid())) {
			throw new SpinwinException(SpinwinExceptionEnum.ARGUMENT_ERROR,
					"参与人openid不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getJwid())) {
			throw new SpinwinException(SpinwinExceptionEnum.ARGUMENT_ERROR,
					"微信ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getSubscribe())) {
			throw new SpinwinException(SpinwinExceptionEnum.ARGUMENT_ERROR,
					"关注状态不能为空");
		}
	}

	/**
	 * 抽奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAwards", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson getAwards(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "getAwards parameter WeixinDto={}.",
				new Object[] { weixinDto });
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String actId = weixinDto.getActId();
		String openId = weixinDto.getOpenid();
		
		int lostDeg[] =  new int[] {36, 66, 96, 156, 186, 216, 276, 306, 336};
		int prizeDeg[] = new int[] {6, 126, 246};
		int angle = 0;
		try {

			// 参数验证
			validateWeixinDtoParam(weixinDto);
			if (openId != null) {
				String nickname = WeiXinHttpUtil.getNickName(openId, jwid);
				weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
			}
			// 获取活动信息
			WxActSpinwin wxActspinwin = wxActSpinwinService
					.queryById(actId);
			if("1".equals(wxActspinwin.getFoucsUserCanJoin())){//如果活动设置了需要关注用户才能参加	
				//未关注
				 if("0".equals(weixinDto.getSubscribe())){
					j.setSuccess(false);
					j.setObj("isNotFoucs");
					return j;
				}
			}
			if("1".equals(wxActspinwin.getBindingMobileCanJoin())){//如果活动设置了需要绑定手机号才能参加				
				// 获取绑定手机号
				String bindPhone = getBindPhone(openId,jwid);
				// 判断是否绑定了手机号
				if (StringUtils.isEmpty(bindPhone)) {
					j.setSuccess(false);
					j.setObj("isNotBind");
					return j;
				}
			}
			// 判断总抽奖次数是否用完
			Date currDate = new Date();
			List<WxActSpinwinRecord> spinwinRecordList = wxActSpinwinRecordService
			.queryBargainRecordListByOpenidAndActidAndJwid(
					openId, actId, jwid, null);
			if (spinwinRecordList != null
					&& spinwinRecordList.size() >= wxActspinwin.getCount()) {
				throw new SpinwinException(
						SpinwinExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
								"controller.exception.nocount", actId));
			}
			if(wxActspinwin.getNumPerDay()!= 0){	//每天次数设置为0，代表不限制每天抽奖次数，如果不等于0代表限制了每天抽奖次数
				spinwinRecordList = wxActSpinwinRecordService
				.queryBargainRecordListByOpenidAndActidAndJwid(
						openId, actId, jwid, currDate);
				if (spinwinRecordList != null
						&& spinwinRecordList.size() >= wxActspinwin
						.getNumPerDay()) {
					throw new SpinwinException(
							SpinwinExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
									"controller.exception.nownocount",
									actId));
				}
			}
			//生成用户的抽奖记录
			WxActSpinwinRecord wxActSpinwinRecord = new WxActSpinwinRecord();
			wxActSpinwinRecord.setId(RandomUtils.generateID());
			wxActSpinwinRecord.setActId(actId);
			wxActSpinwinRecord.setNickname(weixinDto.getNickname());
			wxActSpinwinRecord.setOpenid(openId);
			wxActSpinwinRecord.setJwid(jwid);
			wxActSpinwinRecord.setRecieveTime(new Date());
			Map<String,Object> map = new HashMap<String,Object>();
			//为用户抽取活动奖品
			if("0".equals(wxActspinwin.getPrizeStatus())){//中奖可继续参与		
				//活动奖品
				List<WxActSpinwinPrizes> awards = wxActSpinwinPrizesService
				.queryRemainAwardsByActId(actId);
				//得到各奖品的概率列表
				List<Double> orignalRates = new ArrayList<Double>(awards.size());
				for (WxActSpinwinPrizes award : awards) {
					int remainNum = award.getRemainNum();
					double probability = award.getProbability();
					if (remainNum <= 0) {//剩余数量为零，需使它不能被抽到
						probability = 0;
					}
					orignalRates.add(probability);
				}
				//根据概率产生奖品
				WxActSpinwinPrizes tuple = new WxActSpinwinPrizes();
				int index = LotteryUtil.lottery(orignalRates);
				if (index>=0) {//中奖啦
					tuple= awards.get(index);
					wxActSpinwinRecord.setAwardsId(tuple.getAwardId());
					angle = prizeDeg[index];
					map.put("angle", angle);
				} else {
					angle = lostDeg[new Random().nextInt(lostDeg.length)];
					map.put("angle", angle);
				}
			} else {//一旦中奖不可继续参与
				// 中奖记录
				spinwinRecordList  = wxActSpinwinRecordService
				.queryMyAwardsByOpenidAndActidAndJwid(openId, actId, jwid);
				if (spinwinRecordList.size()==0) {//未曾中过奖项可继续正常参与抽奖
					//活动奖品
					List<WxActSpinwinPrizes> awards = wxActSpinwinPrizesService
					.queryRemainAwardsByActId(actId);
					//得到各奖品的概率列表
					List<Double> orignalRates = new ArrayList<Double>(awards.size());
					for (WxActSpinwinPrizes award : awards) {
						int remainNum = award.getRemainNum();
						double probability = award.getProbability();
						if (remainNum <= 0) {//剩余数量为零，需使它不能被抽到
							probability = 0;
						}
						orignalRates.add(probability);
					}
					//根据概率产生奖品
					WxActSpinwinPrizes tuple = new WxActSpinwinPrizes();
					int index = LotteryUtil.lottery(orignalRates);
					if (index>=0) {//中奖啦
						tuple= awards.get(index);
						wxActSpinwinRecord.setAwardsId(tuple.getAwardId());
						angle = prizeDeg[index];
						map.put("angle", angle);
					} else {
						angle = lostDeg[new Random().nextInt(lostDeg.length)];
						map.put("angle", angle);
					}
				} else {
					wxActSpinwinRecord = spinwinRecordList.get(0);
					j.setSuccess(false);
					j.setObj("yzj");
					map.put("wxActSpinwinRecord",wxActSpinwinRecord);

					WxActSpinwinAwards wxActSpinwinAwards = wxActSpinwinAwardsService
							.queryById(wxActSpinwinRecord.getAwardsId());
					int index = wxActSpinwinAwards.getAwardsValue();
					angle = prizeDeg[index - 1];
					map.put("angle", angle);
					j.setAttributes(map);
					return j;
				}
			}

			WxActSpinwinPrizes wxActSpinwinPrize = wxActSpinwinRecordService.creatAwards(wxActSpinwinRecord);
			j.setSuccess(true);
			String basePath = request.getContextPath();
			map.put("basePath",basePath);
			map.put("wxActSpinwinRecord",wxActSpinwinRecord);
			map.put("wxActSpinwinPrize", wxActSpinwinPrize);
			
			j.setAttributes(map);
			
			j.setObj(wxActSpinwinPrize);
		} catch (SpinwinException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("SPINWIN error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("抽奖失败!");
			LOG.error("SPINWIN error:{}", e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到我的奖品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myprizes", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void myprizes(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "myawardrecord parameter WeixinDto={}.",
				new Object[] { weixinDto });
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/vm/myprizes.vm";
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {
			// 我的中奖记录
			List<WxActSpinwinRecord> recordList = new ArrayList<WxActSpinwinRecord>();
			recordList = wxActSpinwinRecordService
					.queryMyAwardsByOpenidAndActidAndJwid(
							weixinDto.getOpenid(), weixinDto.getActId(),
							weixinDto.getJwid());
			velocityContext.put("recordList", recordList);
			// 获取活动信息
			WxActSpinwin wxActspinwin = wxActSpinwinService
					.queryById(actId);
			velocityContext.put("spinwin", wxActspinwin);
			velocityContext.put("weixinDto", weixinDto);
			
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",wxActspinwin.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
		} catch (SpinwinException e) {
			LOG.error("myawardrecord error:{}", e.getMessage());
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("myawardrecord error:{}", e);
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("errCode",
					SpinwinExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					SpinwinExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到获奖名单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/winners", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void winners(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "winners parameter WeixinDto={}.",
				new Object[] { weixinDto });
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "spinwin/vm/winners.vm";
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {
			// 获取活动信息
			WxActSpinwin wxActSpinwin = wxActSpinwinService
					.queryById(weixinDto.getActId());
			// 我的中奖记录
			List<WxActSpinwinRecord> recordList = new ArrayList<WxActSpinwinRecord>();
			velocityContext.put("spinwin", wxActSpinwin);
			velocityContext.put("weixinDto", weixinDto);
			recordList = wxActSpinwinRecordService.queryBargainRecordListByActidAndJwid(actId, jwid);
			velocityContext.put("recordList", recordList);
			
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",wxActSpinwin.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
		} catch (SpinwinException e) {
			LOG.error("winners error:{}", e.getMessage());
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("winners error:{}", e);
			viewName = "spinwin/vm/error.vm";
			velocityContext.put("errCode",
					SpinwinExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					SpinwinExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
	

	/**
	 * 领奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateRecord", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson updateRecord(@ModelAttribute WxActSpinwinRecord wxActSpinwinRecord,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "updateRecord parameter wxActSpinwinRecord={}.",
				new Object[] { wxActSpinwinRecord });
		try {
			wxActSpinwinRecordService.doEdit(wxActSpinwinRecord);
			j.setSuccess(true);
		} catch (SpinwinException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("SPINWIN error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("领奖失败!");
			LOG.error("SPINWIN error:{}", e.getMessage());
		}
		return j;
	}

	private String getBindPhone(String openid, String jwid) {
		String bindPhine = "";
		try {
			JSONObject jsonObj = WeiXinHttpUtil.getUserInfo(openid, jwid);
			LOG.info("getBindPhine json{}.", new Object[] { jsonObj });
			if (jsonObj.containsKey("bindPhoneStatus")) {
				if ("Y".equals(jsonObj.getString("bindPhoneStatus"))) {
					if (jsonObj.containsKey("phone")) {
						bindPhine = jsonObj.getString("phone");
					}
				}
			}
		} catch (Exception e) {
		}
		return bindPhine;
	}
}
