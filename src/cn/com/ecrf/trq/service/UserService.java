package cn.com.ecrf.trq.service;

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
		return userMapper.findUserByLoginName(username);
	}
	
	public int saveUser(User user){
		int id = 0;
		int organizaitonId = user.getOrganizationId();
		if (organizaitonId > 0){
			Organization organization = organizationMapper.getOrganizationById(id);
			user.setOrganizationName(organization.getName());
		}
		if (user.getId() <= 0){
			userMapper.insertUser(user);
		}else{
			userMapper.updateUser(user);
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
		condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize);
		condition.put(AjaxReturnValue.limitSize, pageSize);
		List<Organization> organizations =  organizationMapper.findOrganizations(condition);
		int total = organizationMapper.getNum();
		Map<String, Object> result = AjaxReturnUtils.generateAjaxReturn(true, null, organizations, total);
		return result;
	}

	public Organization getOrganization(int id) {
		// TODO Auto-generated method stub
		Organization organization = organizationMapper.getOrganizationById(id);
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
		if (organization.getId() > 0){
			organizationMapper.updateOrganization(organization);
		}else{
			organizationMapper.insertOrganization(organization);
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
		return roleMapper.findRoles(condition);
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

	public List<User> getAdminUserList() {
		// TODO Auto-generated method stub
		List<User> users = userMapper.findAdminUsers();
		return users;
	}

}
