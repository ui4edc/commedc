package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.service.DictService;

public class DictControllerTest {

	@Autowired
	private DictService dictService;
	@Test
	public void testGetShapshot() {
		dictService.getDictSnapshot();
	}

}
