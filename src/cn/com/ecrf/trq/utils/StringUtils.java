package cn.com.ecrf.trq.utils;

public class StringUtils {
	public static boolean isNotBlank(String name){
		if (name == null || name.length() == 0 || name.trim().length() == 0)
			return false;
		else
			return true;
	}
	
	public static int[] spiltIdArray(String idStr){
		if (!isNotBlank(idStr)){
			return null;
		}
		String[] sArray = idStr.split(",");
		int[] iArray = new int[sArray.length];
		for (int i=0;i<sArray.length;i++){
			if (isNotBlank(sArray[i]))
				iArray[i] = Integer.parseInt(sArray[i]);
		}
		return iArray;
	}
}
