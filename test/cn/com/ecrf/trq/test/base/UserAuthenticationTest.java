package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.CipherUtil;

public class UserAuthenticationTest extends SpringControllerTest {

	@Autowired
	private UserService userService;
	
	//@Ignore
	@Test
	public void testFindUsers(){
		User user = userService.findUserByLoginName("1");
		Assert.notNull(user);
	}
	//@Ignore
	@Test 
	public void testInsertUser(){
		User user = new User();
		user.setUserName("zhangsan1");
		user.setDisplayName("张三");
		user.setPassword(CipherUtil.generatePassword("123456"));
		user.setStatus(1);
		user.setOrganizationName("XX医院");
		int id = userService.saveUser(user);
		User user2 = userService.getUserById(id);
		System.out.println("id="+id);
		org.junit.Assert.assertEquals(user.getUserName(), user2.getUserName());
	}
	
	//@Ignore
	@Test
	public void testUpdateUser(){
		User user = new User();
		user.setUserName("zhangsan1");
		user.setDisplayName("张三");
		user.setPassword(CipherUtil.generatePassword("123456"));
		user.setStatus(1);
		user.setOrganizationName("XX医院");
		int id = userService.saveUser(user);
		user.setDisplayName("张三");
		userService.saveUser(user);
		User user2 = userService.getUserById(user.getId());
		org.junit.Assert.assertEquals(user.getUserName(), user2.getUserName());
		org.junit.Assert.assertNotEquals("张三", user2.getUserName());
	}
	//@Ignore
	@Test
	public void testDeleteUser(){
		User user = new User();
		user.setUserName("zhangsan1");
		user.setDisplayName("张三");
		user.setPassword(CipherUtil.generatePassword("123456"));
		user.setStatus(1);
		user.setOrganizationName("XX医院");
		userService.saveUser(user);
		userService.deleteUserById(user.getId());
		User user2 = userService.getUserById(user.getId());
		org.junit.Assert.assertNull(user2);
		
	}
	
	//@Ignore
	@Test
	public void testInsertRole(){
		Role role = new Role();
		role.setRoleName("cro");
		role.setRoleDesc("录入员");
		role.setCode("00");
		userService.saveRole(role);
		Role role2 = userService.getRoleById(role.getId());
		org.junit.Assert.assertEquals(role.getRoleName(), role2.getRoleName());
		Role role3 = userService.findRoleByName(role.getRoleName());
		org.junit.Assert.assertEquals(role.getRoleName(), role3.getRoleName());
		userService.deleteRoleById(role.getId());
		
	}
	//@Ignore
	@Test
	public void findUsers(){
		for (int i=0;i<20;i++){
			User user = new User();
			user.setUserName("user"+i);
			user.setPassword("2");
			userService.saveUser(user);
		}
		List<User> users = userService.findUsers(1, 10);
		for (int i=0;i<users.size();i++){
			System.out.println(""+users.get(i).getId()+":"+users.get(i).getUserName());
		}
	}
	
	//@Ignore
	@Test
	public void findUser(){
		try{
			User user = userService.findUserByLoginName("admin");
			List<Role> roles = userService.getRoleByUser("admin");
			
			System.out.println(user.getId());
			System.out.println(roles.get(0));
			System.out.println(user.getRoleName());
			System.out.println(user.getOrganizationName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void findRoleByUser(){
		List<Role> roles = userService.getRoleByUser("admin");
		System.out.println(roles);
	}

}