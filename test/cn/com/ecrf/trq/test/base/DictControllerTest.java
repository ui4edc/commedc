package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.StaticDict;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.DictService;
import cn.com.ecrf.trq.vo.dict.StaticDictVo;

public class DictControllerTest extends SpringControllerTest{

	@Autowired
	private DictService dictService;
	@Autowired 
	private CRFService cRFService;
	@Autowired 
	private DictMapper dictMapper;
	@Test
	public void testGetShapshot() {
		dictService.getDictSnapshot();
	}
	
	@Test
	public void testFieldDict() {
		
	}
	
	@Test
	public void testGetStaticDict() {
		Map<String, Object> results = cRFService.getStaticDict("口", "way");
		StaticDict staticDict = new StaticDict();
		staticDict.setName("口服");
		staticDict.setTypeabbr("way");
		List<StaticDict> staticDicts = dictMapper.getStaticDict(staticDict);
		for (int i=0;i<staticDicts.size();i++){
			System.out.println(staticDicts.get(i).getName());
			if ("口服".equals(staticDicts.get(i).getName())){
				System.out.println("find:"+staticDicts.get(i).getName());
			}
		}
		//List<StaticDictVo> dictRows = (List<StaticDictVo>) results.get("data");
		//Assert.isTrue(dictRows.size() > 0);
		
	}

}
