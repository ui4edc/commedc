package cn.com.ecrf.trq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.vo.ListConditionVo;
import cn.com.ecrf.trq.vo.ListNotifyVo;
import cn.com.ecrf.trq.vo.ListReturnVo;

@Controller
public class ListController {

	@Autowired
	private CRFService cRFService;
	
	@RequestMapping(value="/list/notify", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> notify(HttpServletRequest request) {
		ListNotifyVo notify = cRFService.getNotifyInfo();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null);
		result.put("doubtNumber", notify.getQuestionNum());
		result.put("toDoNumber", notify.getDeadlineNum());
		return result;
	}
	
	@RequestMapping(value="/list/getPatientList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPatientList(ListConditionVo condition, HttpServletRequest request) {
		List<ListReturnVo> list = null;
		int total = 0;
		if (condition.isCrf()){
			list = cRFService.getCRFList(condition);
			total = cRFService.getCRFTotal(condition);
		}
		
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, list, total);
		return result;
	}
}
