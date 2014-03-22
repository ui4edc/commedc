package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.model.ADRCase;
import cn.com.ecrf.trq.model.CRFUserSign;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.UserSignMapper;
import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.StatService;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.utils.LockStatusUtils;
import cn.com.ecrf.trq.vo.ADRVo;
import cn.com.ecrf.trq.vo.DrugSummaryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.lab.InHospitalExamVo;

public class CRFInsertTest extends SpringControllerTest{

	@Autowired
	private CRFService cRFService;
	@Autowired
	private UserSignMapper userSignMapper;
	@Autowired
	private CRFMapper cRFMapper;
	@Autowired
	private StatService statService;
	
	@Ignore
	@Test
	public void testInsertPatientInfo() {
		PatientInfoVo patientInfoVo = new PatientInfoVo();
		//patientInfoVo.setAbbr("BCKD");
		patientInfoVo.setAge(23);
		patientInfoVo.setBirthday("2010-01-03");
		patientInfoVo.setOutdate("2013-01-01");
		patientInfoVo.setEthic(1);
		//patientInfoVo.setEthictxt("畲族");
		patientInfoVo.setHeight("185");
		patientInfoVo.setHys(3);
		//patientInfoVo.setName("张三");
		patientInfoVo.setNo("010-1001");
		patientInfoVo.setIndate("2012-12-30");
		patientInfoVo.setSex(2);
		patientInfoVo.setWeight("55.5");
		patientInfoVo.setFeemode(1);
		patientInfoVo.setFeemodetxt("其他医疗方式");
		cRFService.savePatientInfo(patientInfoVo);
	}
	@Ignore
	@Test
	public void testUpdatePatientInfo() {
		PatientInfoVo patientInfoVo = new PatientInfoVo();
		patientInfoVo.setId(5);
		patientInfoVo.setNo("001-0001");
		//patientInfoVo.setAbbr("BCKD");
		patientInfoVo.setAge(23);
		patientInfoVo.setBirthday("2010-01-03");
		patientInfoVo.setOutdate("2013-01-01");
		patientInfoVo.setEthic(1);
		//patientInfoVo.setEthictxt("畲族");
		patientInfoVo.setHeight("185");
		patientInfoVo.setHys(3);
		//patientInfoVo.setName("张三");
		patientInfoVo.setNo("010-0001");
		patientInfoVo.setIndate("2012-12-30");
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
		condition.setCreateDateFrom(null);
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
	@Ignore
	@Test
	public void testGetPersonHistory(){
		PersonAllergicHistoryCase person = cRFMapper.getPersonHistory(7);
	}
	@Ignore
	@Test
	public void testServiceGetPersonHistory(){
		 Map<String, Object> result = cRFService.getPersonHistory("8");
		 Object obj = result.get("data");
		 System.out.println(obj);
	}
	
	
	@Test
	public void testSaveDrugSummary(){
		DrugSummaryVo drugSummaryVo = new DrugSummaryVo();
		drugSummaryVo.setId(5);
		drugSummaryVo.setNo("001-0001");
		drugSummaryVo.setRemark("remark");
		cRFService.saveDrugSummary(drugSummaryVo);
		Map<String, Object> result = cRFService.getDrugSummary(drugSummaryVo.getId());
		Assert.assertEquals("remark", ((DrugSummaryVo)result.get("data")).getRemark());
		drugSummaryVo.setRemark("remark1");
		cRFService.saveDrugSummary(drugSummaryVo);
		result = cRFService.getDrugSummary(drugSummaryVo.getId());
		Assert.assertEquals("remark1", ((DrugSummaryVo)result.get("data")).getRemark());
	}
	@Ignore
	@Test
	public void testSaveADR(){
		ADRCase adrCase = cRFMapper.getADR(5);
		ADRVo adrVo = new ADRVo();
		adrVo.setId(5);
		adrVo.setNo("001-0001");
		adrVo.setName("abc");
		cRFService.saveADR(adrVo);
		adrVo.setName("abc1");
		cRFService.saveADR(adrVo);
		
	}
	@Ignore
	@Test
	public void testGetDrugUse(){
		cRFService.getDrugUseNum(5);
		
	}
	
	@Test
	public void testGetStat(){
		statService.getSexStat();
		statService.getAgeStat();
		statService.getHospitalStat();
		cRFService.getDrugUseInfo(5, 1);
	}
	
	@Test
	public void testDeleteCRF(){
		cRFService.batchDelete("5");
	}
}
