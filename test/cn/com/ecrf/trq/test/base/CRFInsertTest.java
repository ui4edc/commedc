package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.model.CRFUserSign;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.UserSignMapper;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.LockStatusUtils;
import cn.com.ecrf.trq.vo.PatientInfoVo;

public class CRFInsertTest extends SpringControllerTest{

	@Autowired
	private CRFService cRFService;
	@Autowired
	private UserSignMapper userSignMapper;
	@Autowired
	private CRFMapper cRFMapper;
	
	@Ignore
	@Test
	public void testInsertPatientInfo() {
		PatientInfoVo patientInfoVo = new PatientInfoVo();
		patientInfoVo.setAbbr("BCKD");
		patientInfoVo.setAge("23");
		patientInfoVo.setBirthday("2010-01-03");
		patientInfoVo.setOutDate("2013-01-01");
		patientInfoVo.setEthic(1);
		//patientInfoVo.setEthictxt("畲族");
		patientInfoVo.setHeight("185");
		patientInfoVo.setHys(3);
		patientInfoVo.setName("张三");
		patientInfoVo.setNo("010-1001");
		patientInfoVo.setInDate("2012-12-30");
		patientInfoVo.setSex(2);
		patientInfoVo.setWeight("55.5");
		patientInfoVo.setFeemode(1);
		patientInfoVo.setFeemodetxt("其他医疗方式");
		cRFService.savePatientInfo(patientInfoVo);
	}
	
	@Ignore
	@Test
	public void testInsertUserSign(){
		CRFUserSign userSign = new CRFUserSign();
		userSign.setLockStatus(LockStatusUtils.draft);
		userSign.setCroName("admin");
		userSign.setProgress(0);
		userSign.setNo("001-1234");
		userSignMapper.insertUserSign(userSign);
	}
	
	@Ignore
	@Test
	public void testInsertCRF(){
		PatientInfoCase patientInfoCase = new PatientInfoCase();
		patientInfoCase.setNo("001-1234");
		patientInfoCase.setAbbr("ABCD");
		patientInfoCase.setCreateBy("admin");
		cRFMapper.insertCRF(patientInfoCase);
		System.out.println(patientInfoCase.getId());
		Assert.assertTrue(patientInfoCase.getId() > 0);
	}
	
	@Ignore
	@Test
	public void testGetPatientList(){
		ListCondition condition = new ListCondition();
		condition.setAbbr("");
		condition.setCreateDateForm(null);
		condition.setLockStatus(10);
		condition.setProgressType(1);
		condition.setProgress(0);
		List<ListReturn> list = cRFMapper.getPatientList(condition);
		System.out.println(list.size());
		condition.setProgress(10);
		list = cRFMapper.getPatientList(condition);
		System.out.println(list.size());
		condition.setProgressType(2);
		condition.setProgress(10);
		condition.setLimitStart(0);
		condition.setLimitSize(20);
		list = cRFMapper.getPatientList(condition);
		System.out.println(list.size());
		int total = cRFMapper.getTotalPatientNum(condition);
		System.out.println("total:"+total);
	}
	
	@Test
	public void testGetPersonHistory(){
		PersonAllergicHistoryCase person = cRFMapper.getPersonHistory(7);
	}
	
	@Test
	public void testServiceGetPersonHistory(){
		 Map<String, Object> result = cRFService.getPersonHistory("8");
		 Object obj = result.get("data");
		 System.out.println(obj);
	}
	

}
