package cn.com.ecrf.trq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.ConvertorService;
import cn.com.ecrf.trq.service.StatService;
import cn.com.ecrf.trq.utils.AjaxReturnValue;

@Controller
public class StatController {

	@Autowired
	private StatService statService;
	
	@RequestMapping(value="/stat/getAgeStat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAgeStat(HttpServletRequest request) {
		Map<String, Object> result = statService.getAgeStat();
		return result;
	}
	
	@RequestMapping(value="/stat/getSexStat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSexStat(HttpServletRequest request) {
		Map<String, Object> result = statService.getSexStat();
		return result;
	}
	
	@RequestMapping(value="/stat/getHospitalStat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getHospitalStat(HttpServletRequest request) {
		Map<String, Object> result = statService.getHospitalStat();
		return result;
	}
	
	@RequestMapping(value="/stat/getADEStat", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getADEStat(HttpServletRequest request) {
		Map<String, Object> result = statService.getADEStat();
		return result;
	}
}
