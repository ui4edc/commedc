package cn.com.ecrf.trq.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.CipherUtil;
import cn.com.ecrf.trq.utils.ReturnUtils;

@Controller
public class UserControler {
	private static Logger logger = LoggerFactory.getLogger(UserControler.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated())
			return "index";
		else
			return "login";
	}
	
	@RequestMapping("/form")
	public String createForm(HttpServletRequest request) {
		
		return "form";
	}
	
	@RequestMapping("/index")
	public String showIndex(HttpServletRequest request) {
		
		return "index";
	}
	
	/**
	 * 娴嬭瘯springmvc涓巑ybatis鏁村悎鎴愬姛
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}/showUser")
	public String showUser(@PathVariable int id, HttpServletRequest request) {
		User user = userService.getUserById(id);
		System.out.println(user.getUserName());
		request.setAttribute("user", user);
		return "showUser";
	}
	
	/**
	 * 璺宠浆鑷崇櫥褰曢〉
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String tologin(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.debug("client ip" + request.getRemoteHost() + "]");
		return "login";
	}
	
	/**
	 * 鐧诲綍绀轰緥
	 * @param request
	 * @return
	 */
	@RequestMapping("/tologin")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) {
		//String result = "login";
		Map<String, Object> result = new HashMap<String, Object>();
		// 姝ゅ榛樿鏈夊�
		String username = request.getParameter("username");
		//MD5鍔犲瘑
		String password = CipherUtil.generatePassword(request.getParameter("password"));
		//String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			System.out.println("----------------------------");
			if (!currentUser.isAuthenticated()){
				token.setRememberMe(true);
				currentUser.login(token);
			}
			result.put(ReturnUtils.success, true);
		} catch (Exception e) {
			result.put(ReturnUtils.success, false);
			result.put(ReturnUtils.errorMsg, e.getMessage());
		}
		return result;
	}
  
    /**
     * 鐧诲嚭
     * @return
     */
    @RequestMapping(value = "/logout")  
    @ResponseBody  
    public String logout() {  
  
        Subject currentUser = SecurityUtils.getSubject();  
        String result = "logout";  
        currentUser.logout();  
        return result;  
    }  
    
    /**
     * 妫�煡
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
}
