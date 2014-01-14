package cn.com.ecrf.trq.test.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Ignore;
import org.junit.Test;

import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.JSONUtils;

public class JSONConvertor {

	@Ignore
	@Test
	public void testJSON() {
		String jsonStr = "";
		
		JSONArray jsonArray = new JSONArray();
		List<FormEnumObject> list = new ArrayList<FormEnumObject>();
		for (int i=0;i<10;i++){
			FormEnumObject obj = new FormEnumObject(i, "name "+i, "yyks");
			list.add(obj);
		}
		jsonArray = JSONArray.fromObject(list);
		jsonStr = jsonArray.toString();
		System.out.println("jsonstr:"+jsonStr);
		jsonArray = new JSONArray();
		jsonArray = JSONArray.fromObject(jsonStr);
		Collection<FormEnumObject> nlist = jsonArray.toCollection(jsonArray, FormEnumObject.class);
		//List<FormEnumObject> nlist = JSONArray.parseArray(jsonStr, FormEnumObject.class);
		FormEnumObject obj = (FormEnumObject)nlist.toArray()[0];
		System.out.println("nlist:"+obj.getOther());
		//System.out.println("json:"+jsonArray.);
	}
	
	@Test
	public void testGenJson(){
		JSONUtils<FormEnumObject> util = new JSONUtils<FormEnumObject>(FormEnumObject.class);
		List<FormEnumObject> list = new ArrayList<FormEnumObject>();
		for (int i=0;i<10;i++){
			FormEnumObject obj = new FormEnumObject(i, "name "+i, "yyks");
			list.add(obj);
		}
		String json = util.convertFromList(list);
		System.out.println(json);
		list = util.convertFromString(json);
		System.out.println("list:"+((FormEnumObject)list.get(0)).getSeq());
	}

}
