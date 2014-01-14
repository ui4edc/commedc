package cn.com.ecrf.trq.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

public class JSONUtils<T>{

	private Class<T> clazz;
	
	public JSONUtils(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public  String convertFromList(List<T> list){
		List list2 = new ArrayList<String>();
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	public List<T> convertFromString(String json){
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(json);
		Collection<T> nlist = JSONArray.toCollection(jsonArray, clazz);
		return Arrays.asList((T[])nlist.toArray());
	}
}
