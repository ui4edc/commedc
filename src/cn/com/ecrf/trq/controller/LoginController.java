package cn.com.ecrf.trq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.AjaxReturnValue;
import cn.com.ecrf.trq.utils.CipherUtil;
import cn.com.ecrf.trq.utils.StringUtils;


@Controller
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated())
			return "/index";
		else
			return "/login";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Map<String, Object> model) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser = SecurityUtils.getSubject();  
			String userName = (String)currentUser.getPrincipal();
			User user = userService.findUserByLoginName(userName);
			model.put("user", userName);
			model.put("userId", user.getId());
			Organization organization = userService.findUserOrganization(user.getId());
			model.put("organization", organization.getName());
			List<Role> roles = userService.getRoleByUser(userName);
			if (roles != null && roles.size() > 0){
				model.put("role", roles.get(0).getDescription());
				model.put("roleId", roles.get(0).getId());
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
		
		
		
	}
	
	@RequestMapping("/login")
	public String tologin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model/*Model model*/){
		logger.debug("来自IP[" + request.getRemoteHost() + "]的访问");
		String errorId = request.getParameter("errorId");
		String errorMsg = "";
		if ("1".equals(errorId))
			errorMsg = "用户名不能为空";
		if ("2".equals(errorId))
			errorMsg = "密码不能为空";
		if ("3".equals(errorId))
			errorMsg = "用户或密码错误";
		if (StringUtils.isNotBlank(errorMsg))
			model.put("errorMsg", errorMsg);
		return "login";
	}
	
	/**
     * 登出
     * @return
     */
    @RequestMapping(value = "/logout")   
    public String logout() {  
  
        Subject currentUser = SecurityUtils.getSubject();  
        
        currentUser.logout();  
        return "redirect:/login.do";
    }  
    
    /**
     * 检查
     * @return
     */
    @RequestMapping(value = "/chklogin", method = RequestMethod.POST)  
    @ResponseBody  
    public String chkLogin() {  
        Subject currentUser = SecurityUtils.getSubject();  
        if (!currentUser.isAuthenticated()) {  
            return "false";  
        }  
        return "true";  
    }  
    
    @RequestMapping("/tologin")
  	public String login(HttpServletRequest request) {
  		String result = "login";
  		// 此处默认有值
  		String errorId = "";
  		
  		try {
  			String username = request.getParameter("username");
  	  		if (!StringUtils.isNotBlank(username))
  	  			errorId = "1";
  	  		//MD5加密
  	  		String plainPassword = request.getParameter("password");
  	  		if (!StringUtils.isNotBlank(plainPassword))
  	  			errorId = "2";
  	  		String password = CipherUtil.generatePassword(plainPassword);
  	  		//String password = request.getParameter("password");
  	  		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
  	  		
  	  		Subject currentUser = SecurityUtils.getSubject();
  			System.out.println("----------------------------");
  			if (!currentUser.isAuthenticated()){
  				token.setRememberMe(true);
  				currentUser.login(token);
  				currentUser = SecurityUtils.getSubject();
  				if (!currentUser.isAuthenticated()){
  					System.out.println("failed");
  				}else{
  					System.out.println("success");
  				}
  			}
  			System.out.println("result: " + result);
  			return "redirect:/index.do";
  		} catch (Exception e) {
  			logger.error(e.getMessage());
  			errorId = "3";
  		}
  		return "redirect:/login.do?errorId="+errorId;
  	}
	
}
