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
import cn.com.ecrf.trq.service.DictService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.vo.ListNotifyVo;
import cn.com.ecrf.trq.vo.dict.DictRow;
import cn.com.ecrf.trq.vo.dict.DictSnapshot;

@Controller
public class DictController {

	@Autowired
	private DictService dictService;
	
	@RequestMapping(value="/dict/getSnapshot", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listDictSnapshot(HttpServletRequest request) {
		List<DictSnapshot> snapshots = dictService.getDictSnapshot();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, snapshots, snapshots.size());
		return result;
	}
	
	@RequestMapping(value="/dict/getItemList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listItemDict(HttpServletRequest request) {
		String dictName = request.getParameter("name");
		List<DictRow> items = dictService.getItemDict(dictName);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, items, items.size());
		return result;
	}
	
	@RequestMapping(value="/dict/getBaseList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listBaseDict(HttpServletRequest request) {
		String dictName = request.getParameter("name");
		List<DictRow> bases = dictService.getBaseDict(dictName);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, bases, bases.size());
		return result;
	}
	
	@RequestMapping(value="/dict/addItemToBase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addItemToBase(HttpServletRequest request) {
		
		
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null);
		return result;
	}
}
