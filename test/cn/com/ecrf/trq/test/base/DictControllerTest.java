package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.DictService;
import cn.com.ecrf.trq.vo.dict.StaticDictVo;

public class DictControllerTest extends SpringControllerTest{

	@Autowired
	private DictService dictService;
	@Autowired 
	private CRFService cRFService;
	@Test
	public void testGetShapshot() {
		dictService.getDictSnapshot();
	}
	
	@Test
	public void testGetStaticDict() {
		Map<String, Object> results = cRFService.getStaticDict("口", "way");
		List<StaticDictVo> dictRows = (List<StaticDictVo>) results.get("data");
		Assert.isTrue(dictRows.size() > 0);
		
	}

}
