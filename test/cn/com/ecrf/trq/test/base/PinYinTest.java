package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.Arrays;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;

import cn.com.ecrf.trq.utils.PinyinUtils;

public class PinYinTest {

	@Test
	public void  testPinyin() throws BadHanyuPinyinOutputFormatCombination{  
        String pinyin = null;  
        String [] firstAndPinyin = null;  
        String s = "< 中國-China-중국 >";  
          
        pinyin = PinyinUtils.getFirstHanyuPinyin( s);  
        System.err.println("getFirstHanyuPinyin("+s+")......["+pinyin+"]");  
          
        pinyin = PinyinUtils.getHanyuPinyin( s);  
        System.err.println("getHanyuPinyin("+s+")......["+pinyin+"]");  
          
        firstAndPinyin = PinyinUtils.getFirstAndHanyuPinyin( s);  
        System.err.println("getFirstAndHanyuPinyin("+s+")......"+Arrays.asList(firstAndPinyin));  
    }  

}
