package cn.com.ecrf.trq.utils;

public class StringUtils {
	public static boolean isNotBlank(String name){
		if (name == null || name.length() == 0 || name.trim().length() == 0)
			return false;
		else
			return true;
	}
}
