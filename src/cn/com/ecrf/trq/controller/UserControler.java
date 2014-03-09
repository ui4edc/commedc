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
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.account.UserDescriptionVo;

@Controller
public class UserControler {
	private static Logger logger = LoggerFactory.getLogger(UserControler.class);
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/test")
	public String createForm(HttpServletRequest request) {
		
		return "test";
	}
	
	
	//@RequestMapping("/index")
	public String showIndex(HttpServletRequest request, Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>();
		String username = request.getParameter("username");
		//MD5鍔犲瘑
		String password = CipherUtil.generatePassword(request.getParameter("password"));
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		String errorMsg = "";
		/*if (username == "")
			errorMsg = "请输入用户名";
		if (password == "")
			errorMsg = "请输入密码";*/
		Subject currentUser = SecurityUtils.getSubject();
		try {
			if (!currentUser.isAuthenticated()){
				return "redirect:/login.do";
			}else{
				result.put(AjaxReturnValue.success, true);
				currentUser = SecurityUtils.getSubject();  
				String userName = (String)currentUser.getPrincipal();
				User user = userService.findUserByLoginName(userName);
				model.put("user", userName);
				model.put("userId", user.getId());
				model.put("organization", user.getOrganizationName());
				List<Role> roles = userService.getRoleByUser(userName);
				if (roles != null && roles.size() > 0){
					model.put("role", roles.get(0).getRoleName());
					model.put("roleId", roles.get(0).getId());
				}
				return "index";
			}

		} catch (Exception e) {
			result.put(AjaxReturnValue.success, false);
			result.put(AjaxReturnValue.errorMsg, e.getMessage());
			errorMsg = "用户或密码错误";
		}
		//model.put("errorMsg", errorMsg);
		return "/login";
		
		
		
	}
	
	/**
	 * 娴嬭瘯springmvc涓巑ybatis鏁村悎鎴愬姛
	 * @param id
	 * @param request
	 * @return
	 */
	//@RequestMapping("/{id}/showUser")
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
	//@RequestMapping("/login")
	public String tologin(HttpServletRequest request, HttpServletResponse response, Model model){
		logger.debug("client ip" + request.getRemoteHost() + "]");
		return "login";
	}
	
	/**
	 * 鐧诲綍绀轰緥
	 * @param request
	 * @return
	 */
	//@RequestMapping("/tologin")
	//@ResponseBody
	public String login(HttpServletRequest request) {
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
			return "redirect:/index.do";
		} catch (Exception e) {
			result.put(AjaxReturnValue.success, false);
			result.put(AjaxReturnValue.errorMsg, e.getMessage());
		}
		return "redirect:/login.do";
		//return result;
	}
  
    /**
     * logout
     * @return
     */
    //@RequestMapping(value = "/logout")  
    //@ResponseBody  
    public Map<String, Object> logout() {  
		Map<String, Object> result = new HashMap<String, Object>();
        Subject currentUser = SecurityUtils.getSubject();  
        currentUser.logout();  
        result.put(AjaxReturnValue.success, true);
        return result;  
    }  
    
    /**
     * change password
     * @return
     */
    @RequestMapping(value = "/password")  
    @ResponseBody  
    public Map<String, Object> changePassword(HttpServletRequest request) {  
		Map<String, Object> result = new HashMap<String, Object>();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        result = userService.changePassword(oldPassword, newPassword);
        return result;  
    }
    
    /**
     * 妫�煡
     * @return
     */
    //@RequestMapping(value = "/chklogin", method = RequestMethod.GET)  
    //@ResponseBody  
    public String chkLogin() {  
        Subject currentUser = SecurityUtils.getSubject();  
        if (!currentUser.isAuthenticated()) {  
            return "false";  
        }  
        return "true";  
    }  
    
    @RequestMapping(value = "/account/getUserList", method = RequestMethod.POST)
    @ResponseBody  
    public Map<String, Object> getUserList(HttpServletRequest request){
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String type = request.getParameter("type");
		Map<String, Object> result = userService.getUserList(pageNo,  pageSize, type);
    	return result;
    }
    
    @RequestMapping(value = "/account/getAdminList", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getAdminList(HttpServletRequest request){
		List<UserDescriptionVo> users = userService.getAdminUserList();
		int total = 0;
		if (users != null)
			total = users.size();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, users, total);
    	return result;
    }
    
    @RequestMapping(value = "/account/getCRMList", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getCRMList(HttpServletRequest request){
		List<UserDescriptionVo> users = userService.getCRMList();
		int total = 0;
		if (users != null)
			total = users.size();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, users, total);
    	return result;
    }
    
    @RequestMapping(value = "/account/saveUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> saveUser(User user){
    	Map<String, Object> result;
    	try{
    		userService.saveUser(user);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加用户失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/deleteUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteUser(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String ids = request.getParameter("id");
    		int[] iArray = StringUtils.spiltIdArray(ids);
    		userService.deleteUser(iArray);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除用户失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/getUser", method = RequestMethod.POST)  
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
    
    @RequestMapping(value = "/account/saveRole", method = RequestMethod.POST)  
    @ResponseBody  
   public Map<String, Object> saveRole(Role role){
    	Map<String, Object> result;
    	try{
    		userService.saveRole(role);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加角色失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/deleteRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteRole(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String roleId = request.getParameter("id");
    		userService.deleteRole(Integer.parseInt(roleId));
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除角色失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/getRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getRole(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String roleId = request.getParameter("id");
    		Role role = userService.getRole(Integer.parseInt(roleId));
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, role);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/getRoleList", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> findRole(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		List<Role> roles = userService.findRoles();
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, roles);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构列表失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/saveOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> saveOrganization(Organization organization){
    	Map<String, Object> result;
    	try{
    		userService.saveOrganization(organization);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "添加机构失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/deleteOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteOrganization(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String organizationIds = request.getParameter("id");
    		int[] iArray = StringUtils.spiltIdArray(organizationIds);
    		userService.deleteOrganization(iArray);
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除机构失败");

    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/getOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> getOrganization(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String organizationId = request.getParameter("id");
    		Organization organization = userService.getOrganization(Integer.parseInt(organizationId));
    		result = AjaxReturnUtils.generateAjaxReturn(true, null, organization);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/getOrganizationList", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> findOrganization(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		String pageNoStr = request.getParameter("pageNo");
    		String pageSizeStr = request.getParameter("pageSize");
    		int pageNo = 0, pageSize = 0;
    		if (StringUtils.isNotBlank(pageNoStr) && StringUtils.isNotBlank(pageSizeStr)){
    			pageNo = Integer.parseInt(pageNoStr);
        		pageSize = Integer.parseInt(pageSizeStr);
    		}
    		result = userService.findOrganizations(pageNo, pageSize);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "获取机构列表失败");
    		
    	}
    	return result;
    }
    
    @RequestMapping(value = "/account/deleteOrganizationList", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> deleteOrganizationList(HttpServletRequest request){
    	Map<String, Object> result;
    	try{
    		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    		result = AjaxReturnUtils.generateAjaxReturn(true, null);
    	}catch(Exception e){
    		logger.error(e.getMessage());
    		result = AjaxReturnUtils.generateAjaxReturn(false, "删除机构列表失败");
    		
    	}
    	return result;
    }
}
