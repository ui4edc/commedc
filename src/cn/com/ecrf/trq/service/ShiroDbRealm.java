package cn.com.ecrf.trq.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;


public class ShiroDbRealm extends AuthorizingRealm {
	private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);
	private static final String ALGORITHM = "MD5";
	
	@Autowired
	private UserService userService;

	public ShiroDbRealm() {
		super();
	}
	
	/**
	 * 璁よ瘉鍥炶皟鍑芥暟, 鐧诲綍鏃惰皟鐢�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token.getUsername());
		User user = userService.findUserByLoginName(token.getUsername());
		System.out.println(user);
		if (user != null) {
			SimpleAuthenticationInfo simpleAuthenticationInfo = null;
			try{
				simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
			}catch(Exception e){
				e.printStackTrace();
			}
			return simpleAuthenticationInfo;
		}else{
			throw new AuthenticationException();
		}
	}

	/**
	 * 鎺堟潈鏌ヨ鍥炶皟鍑芥暟, 杩涜閴存潈浣嗙紦瀛樹腑鏃犵敤鎴风殑鎺堟潈淇℃伅鏃惰皟鐢�
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/* 杩欓噷缂栧啓鎺堟潈浠ｇ爜 */
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		try{
			String userName = (String) principals.getPrimaryPrincipal();
			List<Role> roleNames = userService.getRoleByUser(userName);
			//Set<String> roleNames = new HashSet<String>();
		    Set<String> permissions = new HashSet<String>();
	/*	    roleNames.add("admin");
		    roleNames.add("zhangsan");
		    permissions.add("user.do?myjsp");
		    permissions.add("login.do?main");
		    permissions.add("login.do?logout");*/
			info.addRole("BASIC");
			for (int i=0;roleNames!=null && i<roleNames.size();i++){
				info.addRole(roleNames.get(i).getRoleName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	    //info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * 鏇存柊鐢ㄦ埛鎺堟潈淇℃伅缂撳瓨.
	 */
	/*public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}*/

	/**
	 * 娓呴櫎鎵�湁鐢ㄦ埛鎺堟潈淇℃伅缂撳瓨.
	 */
	/*public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}*/

//	@PostConstruct
//	public void initCredentialsMatcher() {//MD5鍔犲瘑
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
//		setCredentialsMatcher(matcher);
//	}
}
