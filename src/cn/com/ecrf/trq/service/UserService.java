package cn.com.ecrf.trq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.repository.UserMapper;



@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

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

}
