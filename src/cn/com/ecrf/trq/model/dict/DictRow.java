package cn.com.ecrf.trq.model.dict;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.vo.list.ListNotifyVo;

public class DictRow {
	private int id;
	private String abbr;//缩写
	private String name;//条目名称
	private int baseItemId;
	private String baseItemAbbr;
	private String baseItemName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBaseItemId() {
		return baseItemId;
	}
	public void setBaseItemId(int baseItemId) {
		this.baseItemId = baseItemId;
	}
	public String getBaseItemAbbr() {
		return baseItemAbbr;
	}
	public void setBaseItemAbbr(String baseItemAbbr) {
		this.baseItemAbbr = baseItemAbbr;
	}
	public String getBaseItemName() {
		return baseItemName;
	}
	public void setBaseItemName(String baseItemName) {
		this.baseItemName = baseItemName;
	}
	
	
	
}
