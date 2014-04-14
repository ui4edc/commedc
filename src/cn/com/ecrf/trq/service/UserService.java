package cn.com.ecrf.trq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.repository.OrganizationMapper;
import cn.com.ecrf.trq.repository.RoleMapper;
import cn.com.ecrf.trq.repository.UserMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.AjaxReturnValue;
import cn.com.ecrf.trq.utils.CipherUtil;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.account.UserDescriptionVo;



@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private OrganizationMapper organizationMapper;

	public User getUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	public User findUserByLoginName(String username) {
		System.out.println("findUserByLoginName call!");
		User user = null;
		try{
			user = userMapper.findUserByLoginName(username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public int saveUser(User user) throws Exception{
		int id = 0;
		/*int organizaitonId = user.getOrganizationId();
		if (organizaitonId > 0){
			Organization organization = organizationMapper.getOrganizationById(id);
			user.setOrganizationName(organization.getName());
		}*/
		if (user.getRoleId() == 0){
			throw new Exception("请选择权限");
		}
		Role role = roleMapper.getRoleById(user.getRoleId());
		if ("LCRO".equalsIgnoreCase(role.getRoleName()) || "CRO".equalsIgnoreCase(role.getRoleName())){
			if (user.getOrganizationId() == 0){
				throw new Exception("录入员和分中心管理员必须制定中心名称");
			}
		}
		if ("LCRO".equalsIgnoreCase(role.getRoleName())){
			if (user.getAdminUserId() > 0){
				throw new Exception("分中心管理员不需要直属管理员，请去掉直属管理员选择");
			}
			List<User> adminUsers = userMapper.getAdminUserByOrganizationId(user.getOrganizationId());
			if (adminUsers != null && adminUsers.size() > 0){
				if (adminUsers.get(0).getId() != user.getId()){
					throw new Exception("已经有一个医院管理员，如欲替换，请先修改 "+adminUsers.get(0).getUserName()+" 的权限");
				}
			}
		}
		if ("CRM".equalsIgnoreCase(role.getRoleName()) || "DM".equalsIgnoreCase(role.getRoleName())){
			if (user.getAdminUserId() > 0){
				throw new Exception("监察员和管理员不需要直属管理员，请去掉直属管理员选择");
			}
			if (user.getOrganizationId() > 0){
				throw new Exception("监察员和管理员不隶属于某个中心，请去掉中心名称选择");
			}
		}
		if (user.getId() <= 0){
			user.setPassword(CipherUtil.generatePassword(user.getPassword()));
			userMapper.insertUser(user);
		}else{
			User user2 = userMapper.getUserById(user.getId());
			userMapper.updateUser(user);
			if (!user2.getPassword().equalsIgnoreCase(user.getPassword())){
				user.setPassword(CipherUtil.generatePassword(user.getPassword()));
				userMapper.updatePassword(user);
			}
				
		}
		return user.getId();
	}
	
	public void deleteUserById(int id){
		userMapper.deleteByPrimaryKey(id);
	}

	public int saveRole(Role role) {
		// TODO Auint id = 0;
		if (role.getId() <= 0){
			roleMapper.insertRole(role);
		}else{
			roleMapper.updateRole(role);
		}
		return role.getId();
		
	}

	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(id);
	}

	public Role findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleByName(roleName);
	}

	public void deleteRoleById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		String password = CipherUtil.generatePassword(user.getPassword());
		user.setPassword(password);
		userMapper.addUser(user);
		Role role = userMapper.findRoleByName(user.getRoleName());
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", user.getId());
		condition.put("roleId", role.getId());
		userMapper.addUserRole(condition);
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String password = CipherUtil.generatePassword(user.getPassword());
		user.setPassword(password);
		userMapper.updateUser(user);
	}

	public void deleteUser(int[] idArray) {
		// TODO Auto-generated method stub
		if (idArray != null && idArray.length > 0){
			for (int id : idArray){
				userMapper.deleteUserById(id);
			}	
		}
	}

	public User getUser(User user) {
		// TODO Auto-generated method stub
		user = userMapper.getUserById(user.getId());
		return user;
	}

	public List<User> findUsers(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize);
		condition.put(AjaxReturnValue.limitSize, pageSize);
		List<User> users = userMapper.findUsers(condition);
		return users;
	}

	public Map<String, Object> findOrganizations(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		if (pageNo > 0 && pageSize > 0){
			condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize);
			condition.put(AjaxReturnValue.limitSize, pageSize);
		}
		String roleName = getRoleName();
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		condition.put("userName", userName);
		condition.put("roleName", roleName);
		List<Organization> organizations =  organizationMapper.findOrganizations(condition);
		if (organizations != null){
			for (Organization organization : organizations){
				organization.setAdminUserName(organization.getCrmUserName());
			}
		}
		int total = organizationMapper.getNum();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, organizations, total);
		return result;
	}

	public Organization getOrganization(int id) {
		// TODO Auto-generated method stub
		Organization organization = organizationMapper.getOrganizationById(id);
		organization.setAdminUserId(organization.getCrmUserId());
		return organization;
	}

	public void deleteOrganization(int[] idArray) {
		// TODO Auto-generated method stub
		if (idArray != null && idArray.length > 0){
			for (int id : idArray){
				organizationMapper.deleteOrganizationById(id);
			}	
		}
		
	}

	public void saveOrganization(Organization organization) {
		// TODO Auto-generated method stub
		organization.setCrmUserId(organization.getAdminUserId());
		if (organization.getId() > 0){
			organizationMapper.updateOrganization(organization);
		}else{
			organizationMapper.insertOrganization(organization);
			organizationMapper.insertCRFNO(organization.getId());
		}
	}

	public void deleteRole(int id) {
		// TODO Auto-generated method stub
		roleMapper.deleteRoleById(id);
	}

	public Role getRole(int id) {
		// TODO Auto-generated method stub
		Role role = roleMapper.getRoleById(id);
		return role;
	}

	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
/*		condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize+1);
		condition.put(AjaxReturnValue.limitSize, pageSize);*/
		String roleName = getRoleName();
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		condition.put("userName", userName);
		condition.put("roleName", roleName);
		List<Role> roles = roleMapper.findRoles(condition);
		return roles;
	}

	public Map<String, Object> changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
        Subject currentUser = SecurityUtils.getSubject();  
        String username = (String)currentUser.getPrincipal();
		User user = userMapper.findUserByLoginName(username);
		if (oldPassword == null || newPassword == null){
			result = AjaxReturnUtils.generateAjaxReturn(false, "密码不能为空");
			return result;
		}else{
			oldPassword = CipherUtil.generatePassword(oldPassword);
			if (!oldPassword.equals(user.getPassword())){
				result = AjaxReturnUtils.generateAjaxReturn(false, "原始密码错误");
			}else{
				newPassword = CipherUtil.generatePassword(newPassword);
				user.setPassword(newPassword);
				userMapper.updatePassword(user);
				result = AjaxReturnUtils.generateAjaxReturn(true, null);
			}	
		}
		return result;
	}

	public List<Role> getRoleByUser(String userName) {
		// TODO Auto-generated method stub
		List<Role> roles = roleMapper.getRoleByUserName(userName);
		return roles;
	}

	public Map<String, Object> getUserList(String pageNoStr, String pageSizeStr,
			String typeStr) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(pageNoStr) && StringUtils.isNotBlank(pageSizeStr)){
			int pageNo = Integer.parseInt(pageNoStr);
			int pageSize = Integer.parseInt(pageSizeStr);
			condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize);
			condition.put(AjaxReturnValue.limitSize, pageSize);
		}
		List<User> users = userMapper.findUsers(condition);
		int total = userMapper.getUserNum();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, users, total);
		return result;
	}

	public List<UserDescriptionVo> getAdminUserList() {
		// TODO Auto-generated method stub
		String roleName = getRoleName();
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userName", userName);
		condition.put("roleName", roleName);
		List<User> users = userMapper.findAdminUsers(condition);
		List<UserDescriptionVo> userVos = new ArrayList<UserDescriptionVo>();
		if (users != null){
			for (User user : users){
				UserDescriptionVo desc = new UserDescriptionVo();
				desc.setId(user.getId());
				desc.setDescription(user.getDisplayName());
				userVos.add(desc);
			}
		}
		return userVos;
	}

	public List<UserDescriptionVo> getCRMList() {
		// TODO Auto-generated method stub
		List<User> users = userMapper.findCRMUsers();
		List<UserDescriptionVo> userVos = new ArrayList<UserDescriptionVo>();
		if (users != null){
			for (User user : users){
				UserDescriptionVo desc = new UserDescriptionVo();
				desc.setId(user.getId());
				desc.setDescription(user.getDisplayName());
				userVos.add(desc);
			}
		}
		return userVos;
	}

	public Organization findUserOrganization(int userId) {
		// TODO Auto-generated method stub
		Organization organization = organizationMapper.findUserOrganization(userId);
		return organization;
	}
	
	private String getRoleName(){
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		List<Role> roles = roleMapper.getRoleByUserName(userName);
		String roleName = null;
		if (roles != null && roles.size() > 0){
			roleName = roles.get(0).getRoleName();
		}
		return roleName;
	}

}
