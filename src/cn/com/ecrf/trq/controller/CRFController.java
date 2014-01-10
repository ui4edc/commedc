package cn.com.ecrf.trq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.model.PhaseSignPage;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.AjaxReturnValue;
import cn.com.ecrf.trq.vo.PatientInfoVo;

@Controller
public class CRFController {

	@Autowired
	private CRFService cRFService;
	
	@RequestMapping("/rest/crf/getPatientList")
	@ResponseBody
	public Map<String, Object> getPatientList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxReturnValue.success, true);
		result.put(AjaxReturnValue.errorMsg, "");
		result.put(AjaxReturnValue.total, 100);
		List<User> users = new ArrayList<User>();
		for (int i=0;i<20;i++){
			User user = new User();
			user.setUserName("username"+i);
			users.add(user);
			result.put(AjaxReturnValue.data, users);
		}
		return result;
	}
	
	@RequestMapping(value="/crf/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCRFBasic(@PathVariable int id, HttpServletRequest request) {
		PatientInfoVo patientInfoVo = cRFService.getPatientInfo(id);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, patientInfoVo);
		return result;
	}
	
	
	/**
	 * 
	 * @param patientInfoVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/crf/saveBasicInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBasicInfo(@RequestBody PatientInfoVo patientInfoVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.savePatientInfo(patientInfoVo);
		return result;
	}
	
/*	@RequestMapping(value="/crf/saveBasicInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBasicInfo(@RequestBody PatientInfoVo patientInfoVo, HttpServletRequest request) {
		PatientInfoVo patientInfoVo = cRFService.getPatientInfo(id);
		PhaseSignPage phaseSignPage = cRFService.getPhaseSignInfo(id);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, patientInfoVo);
		return result;
	}*/
}
