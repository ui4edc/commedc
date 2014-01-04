package cn.com.ecrf.trq.controller;

import java.util.HashMap;
import java.util.List;
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

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.CipherUtil;
import cn.com.ecrf.trq.utils.AjaxReturnValue;

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
	
	@RequestMapping("/test")
	public String createForm(HttpServletRequest request) {
		
		return "test";
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
			result.put(AjaxReturnValue.success, true);
		} catch (Exception e) {
			result.put(AjaxReturnValue.success, false);
			result.put(AjaxReturnValue.errorMsg, e.getMessage());
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
    
    @RequestMapping(value = "/accout/addUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> addUser(User user){
    	Map<String, Object> result;
    	try{
    		userService.addUser(user);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加用户失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/updateUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> updateUser(User user){
    	Map<String, Object> result;
    	try{
    		userService.updateUser(user);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "修改用户失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/deleteUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteUser(User user){
    	Map<String, Object> result;
    	try{
    		userService.deleteUser(user);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除用户失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/getUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getUser(User user){
    	Map<String, Object> result;
    	try{
    		User user2 = userService.getUser(user);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, user2);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取用户失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/findUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> findUser(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    		List<User> users = userService.findUsers(pageNo, pageSize);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, users);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取用户列表失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/addRole", method = RequestMethod.POST)  
    @ResponseBody  
   public Map<String, Object> addRole(Role role){
    	Map<String, Object> result;
    	try{
    		userService.addRole(role);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加角色失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/updateRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> updateRole(Role role){
    	Map<String, Object> result;
    	try{
    		userService.updateRole(role);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "修改角色失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/deleteRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteRole(Role role){
    	Map<String, Object> result;
    	try{
    		userService.deleteRole(role);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除角色失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/getRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getRole(Role role){
    	Map<String, Object> result;
    	try{
    		Role role2 = userService.getRole(role);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, role2);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/findRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> findRole(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    		List<Role> roles = userService.findRoles(pageNo, pageSize);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, roles);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构列表失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/addOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> addOrganization(Organization organization){
    	Map<String, Object> result;
    	try{
    		userService.addOrganization(organization);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加机构失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/updateOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> updateOrganization(Organization organization){
    	Map<String, Object> result;
    	try{
    		userService.updateOrganization(organization);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "修改机构失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/deleteOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteOrganization(Organization organization){
    	Map<String, Object> result;
    	try{
    		userService.deleteOrganization(organization);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除机构失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/getOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getOrganization(Organization organization){
    	Map<String, Object> result;
    	try{
    		Organization organization2 = userService.getOrganization(organization);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, organization2);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/accout/findOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> findOrganization(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    		List<Organization> organizations = userService.findOrganizations(pageNo, pageSize);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, organizations);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构列表失败");
    		
    	}
    	return result;
    }
}
