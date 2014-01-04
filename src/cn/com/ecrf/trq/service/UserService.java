package cn.com.ecrf.trq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.repository.OrganizationMapper;
import cn.com.ecrf.trq.repository.RoleMapper;
import cn.com.ecrf.trq.repository.UserMapper;
import cn.com.ecrf.trq.utils.AjaxReturnValue;



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
			userMapper.insertRole(role);
		}else{
			userMapper.updateRole(role);
		}
		return role.getId();
		
	}

	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getRoleById(id);
	}

	public Role findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return userMapper.findRoleByName(roleName);
	}

	public void deleteRoleById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
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

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userMapper.deleteUserById(user.getId());
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

	public List<Organization> findOrganizations(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize+1);
		condition.put(AjaxReturnValue.limitSize, pageSize);
		organizationMapper.findOrganizations(condition);
		return null;
	}

	public Organization getOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteOrganization(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	public void updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	public void addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	public void addRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.insertRole(role);
	}

	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.updateRole(role);
	}

	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.deleteRoleById(role.getId());
	}

	public Role getRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.getRoleById(role.getId());
		return null;
	}

	public List<Role> findRoles(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put(AjaxReturnValue.limitStart, (pageNo-1)*pageSize+1);
		condition.put(AjaxReturnValue.limitSize, pageSize);
		return roleMapper.findRoles(condition);
	}

}
