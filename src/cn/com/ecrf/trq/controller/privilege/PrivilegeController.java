package cn.com.ecrf.trq.controller.privilege;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.service.UserService;

@Controller
public class PrivilegeController {
	private static Logger logger = LoggerFactory.getLogger(PrivilegeController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/privilege/user/find")
	@ResponseBody
	public String listUser(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.debug("client ip" + request.getRemoteHost() + "]");
		
		return "login";
	}
}
