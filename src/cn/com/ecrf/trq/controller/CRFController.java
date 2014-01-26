package cn.com.ecrf.trq.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.StreamConvertorUtils;
import cn.com.ecrf.trq.vo.DiseaseInfoVo;
import cn.com.ecrf.trq.vo.DrugUseVo;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;
import cn.com.ecrf.trq.vo.crf.ParameterType;

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
	
	@RequestMapping(value="/crf/addCRF", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addCRF(HttpServletRequest request) {
		String abbr = request.getParameter("abbr");
		Map<String, Object> result = cRFService.genCRFNo(abbr);
		
		return result;
	}
	
	@RequestMapping(value="/crf/getCRFSumm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCRFSumm(HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.getCRFSumm(id);
		
		return result;
	}
	
	/*@RequestMapping(value="/crf/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCRFBasic(@PathVariable int id, HttpServletRequest request) {
		PatientInfoVo patientInfoVo = cRFService.getPatientInfo(id);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, patientInfoVo);
		return result;
	}*/
	
	
	/**
	 * 
	 * @param patientInfoVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/crf/saveBasicInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveBasicInfo(PatientInfoVo patientInfoVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.savePatientInfo(patientInfoVo);
		return result;
	}
	
	@RequestMapping(value="/crf/getBasicInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBasicInfo(ParameterType parameterType, HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.getBasicInfo(""+parameterType.getId());
		return result;
	}
	
	@RequestMapping(value="/crf/getPersonHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPersonHistory(ParameterType parameterType, HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.getPersonHistory(""+parameterType.getId());
		return result;
	}
	
	@RequestMapping(value="/crf/savePersonHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savePersonHistory(@RequestBody PersonalHistoryVo personalHistoryVo, HttpServletRequest request) {
		//PersonalHistoryVo personalHistoryVo = new PersonalHistoryVo();
		try {
			InputStream in = request.getInputStream();
			String json = StreamConvertorUtils.inputStream2String(in);
			//JSONUtils<PersonalHistoryVo> util = new JSONUtils<PersonalHistoryVo>(PersonalHistoryVo.class);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = cRFService.savePersonHistory(personalHistoryVo);
		return result;
	}
	
	@RequestMapping(value="/crf/getDeseaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDeseaseInfo(ParameterType parameterType, HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.getDeseaseInfo(""+parameterType.getId());
		return result;
	}
	
	@RequestMapping(value="/crf/saveDeseaseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDeseaseInfo(DiseaseInfoVo diseaseInfoVo, HttpServletRequest request) {
		//PersonalHistoryVo personalHistoryVo = new PersonalHistoryVo();
		try {
			InputStream in = request.getInputStream();
			String json = StreamConvertorUtils.inputStream2String(in);
			//JSONUtils<PersonalHistoryVo> util = new JSONUtils<PersonalHistoryVo>(PersonalHistoryVo.class);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = cRFService.saveDeseaseInfo(diseaseInfoVo);
		return result;
	}
	
	@RequestMapping(value="/crf/getDrugUseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDrugUseInfo(ParameterType parameterType, HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.gettDrugUseInfo(""+parameterType.getId());
		return result;
	}
	
	@RequestMapping(value="/crf/saveDrugUseInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDrugUseInfo(@RequestBody DrugUseVo drugUseVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.saveDrugUseInfo(drugUseVo);
		return result;
	}
	
	@RequestMapping(value="/crf/getPastHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPastHistory(ParameterType parameterType, HttpServletRequest request) {
		String id = request.getParameter("id");
		Map<String, Object> result = cRFService.getPastHistory(""+parameterType.getId());
		return result;
	}
	
	@RequestMapping(value="/crf/savePastHistory", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> savePastHistory(PastHistoryVo pastHistoryVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.savePastHistory(pastHistoryVo);
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
