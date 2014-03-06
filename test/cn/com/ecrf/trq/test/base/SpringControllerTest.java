package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.model.dict.StaticDict;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.CipherUtil;
import cn.com.ecrf.trq.utils.PinyinUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring.xml","classpath:/spring-mybatis.xml", "classpath:/spring-shiro.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)    
@Transactional
public class SpringControllerTest {

	@Autowired
	private DictMapper dictMapper;
	
	/*@Test
	public void testInsertStaticDict(){
		StaticDict staticDict = new StaticDict();
		staticDict.setName("口服");
		staticDict.setTypeabbr("way");
		List<StaticDict> staticDicts = dictMapper.getStaticDict(staticDict);
		for (StaticDict one : staticDicts){
			try {
				one.setAbbr(PinyinUtils.getFirstHanyuPinyin(one.getName()));
				System.out.println(one.getAbbr());
				dictMapper.updateStaticDict(one);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}*/
	
	
	
	

}
