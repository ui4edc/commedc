package cn.com.ecrf.trq.test.base;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.service.CRFService;
import cn.com.ecrf.trq.service.UserService;
import cn.com.ecrf.trq.vo.PatientInfoVo;

public class CRFInsertTest {

	@Autowired
	private CRFService cRFService;
	@Test
	public void testInsertPatientInfo() {
		PatientInfoVo patientInfoVo = new PatientInfoVo();
		patientInfoVo.setAbbr("BCKD");
		patientInfoVo.setAge("23");
		patientInfoVo.setBirthday("2010-01-03");
		patientInfoVo.setOutDate("2013-01-01");
		patientInfoVo.setEthic(1);
		patientInfoVo.setEthictxt("畲族");
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

}
