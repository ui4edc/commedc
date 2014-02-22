package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertUser(User user);
    
    int updateUser(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

	User findUserByLoginName(String username);

	int insertRole(Role role);
	
	int updateRole(Role role);
	
	int deleteRoleById(Integer id);
	
	Role getRoleById(Integer id);

	Role findRoleByName(String roleName);

	void addUser(User user);

	void addUserRole(Map<String, Object> condition);

	void deleteUserById(int userId);

	User getUserById(int userId);

	List<User> findUsers(Map<String, Object> condition);

	void updatePassword(User user);

	int getUserNum();

	List<User> findAdminUsers();

	List<User> findCRMUsers();

	List<User> getAdminUserByOrganizationId(int id);

}