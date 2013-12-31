package cn.com.ecrf.trq.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjaxReturnUtils {
	
	public static Map<String, Object> generateAjaxReturn(boolean success, String errorMsg){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxReturnValue.success, success);
		result.put(AjaxReturnValue.errorMsg, errorMsg == null ? "" : errorMsg);
		return result;
	}
	
	public static Map<String, Object> generateAjaxReturn(boolean success, String errorMsg, Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxReturnValue.success, success);
		result.put(AjaxReturnValue.errorMsg, errorMsg == null ? "" : errorMsg);
		result.put(AjaxReturnValue.data, data);
		return result;
	}
	
	public static Map<String, Object> generateAjaxReturn(boolean success, String errorMsg, List list, int total){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(AjaxReturnValue.success, success);
		result.put(AjaxReturnValue.errorMsg, errorMsg == null ? "" : errorMsg);
		result.put(AjaxReturnValue.data, list);
		result.put(AjaxReturnValue.total, total);
		return result;
	}
}
