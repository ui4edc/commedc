package cn.com.ecrf.trq.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.vo.DoubtRecordSubmitVo;
import cn.com.ecrf.trq.vo.crf.ParameterType;

@Controller
public class SuperviseController {

	@Autowired
	private CRFService cRFService;
	
	//@RequestMapping(value="/crf/getDoubtRecord", method = RequestMethod.POST)
	@RequestMapping(value="/supervise/getDoubtRecord", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDoubtRecord(ParameterType parameterType, HttpServletRequest request) {
		Map<String, Object> result = cRFService.getDoubtRecord(parameterType.getId(), parameterType.getMenu());
		return result;
	}
	
	@RequestMapping(value="/supervise/saveDoubtColumn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDoubtColumn(DoubtRecordSubmitVo doubtRecordSubmitVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.saveDoubtRecord(doubtRecordSubmitVo);
		return result;
	}
	
	@RequestMapping(value="/supervise/getDoubtDict", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDoubtDict(ParameterType parameterType, HttpServletRequest request) {
		Map<String, Object> result = cRFService.getDoubtDict(parameterType.getMenu());
		return result;
	}
	
	@RequestMapping(value="/supervise/commitDoubtColumn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commitDoubtColumn(DoubtRecordSubmitVo doubtRecordSubmitVo, HttpServletRequest request) {
		Map<String, Object> result = cRFService.commitDoubtColumn(doubtRecordSubmitVo);
		return result;
	}
		
		
}
