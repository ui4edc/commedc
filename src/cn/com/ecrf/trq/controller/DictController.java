package cn.com.ecrf.trq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.DictService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.list.ListNotifyVo;

@Controller
public class DictController {

	private static Logger logger = LoggerFactory.getLogger(DictController.class);

	
	@Autowired
	private DictService dictService;
	
	@RequestMapping(value="/dict/getSnapshot", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listDictSnapshot(HttpServletRequest request) {
		List<DictSnapshot> snapshots = dictService.getDictSnapshot();
		int total = 0;
		if (snapshots != null)
			total = snapshots.size();		
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, snapshots, total);
		return result;
	}
	
	@RequestMapping(value="/dict/getItemList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listItemDict(HttpServletRequest request) {
		String id = request.getParameter("id");
		List<DictRow> items = dictService.getItemDict(Integer.parseInt(id));
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, items, items.size());
		return result;
	}
	
	@RequestMapping(value="/dict/getBaseList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listBaseDict(HttpServletRequest request) {
		String dictName = request.getParameter("keyword");
		List<DictRow> bases = dictService.getBaseDict(dictName);
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, bases, bases.size());
		return result;
	}
	
	@RequestMapping(value="/dict/addItemToBase", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addItemToBase(HttpServletRequest request) {
		Map<String, Object> result;
		try{
			String selectedItemId = request.getParameter("selectedItemId");
			String baseItemId = request.getParameter("baseItemId");
			if (!StringUtils.isNotBlank(baseItemId))
				return AjaxReturnUtils.generateAjaxReturn(false, "请选择底层字典");
			if (!StringUtils.isNotBlank(baseItemId))
				return AjaxReturnUtils.generateAjaxReturn(false, "请选择条目");
			dictService.addItemToBase(Integer.parseInt(selectedItemId), Integer.parseInt(baseItemId));
			 result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			logger.error(e.getMessage());
			result = AjaxReturnUtils.generateAjaxReturn(false, "关联条目到底层数据字典失败");
		}
		
		return result;
	}
}
