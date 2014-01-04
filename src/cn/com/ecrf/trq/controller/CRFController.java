package cn.com.ecrf.trq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.utils.AjaxReturnValue;

@Controller
public class CRFController {

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
}
