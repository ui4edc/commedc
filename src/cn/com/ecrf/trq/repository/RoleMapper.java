package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;


public interface RoleMapper {

	int insertRole(Role role);
	
	int updateRole(Role role);
	
	int deleteRoleById(Integer id);
	
	Role getRoleById(Integer id);

	Role findRoleByName(String roleName);

	List<Role> findRoles(Map<String, Object> condition);
	
	List<Role> getRoleByUserName(String userName);
}