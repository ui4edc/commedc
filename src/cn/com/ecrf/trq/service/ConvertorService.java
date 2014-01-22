package cn.com.ecrf.trq.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;

@Service
public class ConvertorService {

	@Autowired
	private CRFMapper cRFMapper;
	
	

	public PersonalHistoryVo convertPersonHistoryFromModelToView(
			PersonAllergicHistoryCase personAllergicHistoryCase) {
		// TODO Auto-generated method stub
		return null;
	}



	public PersonAllergicHistoryCase convertPersonHistoryFromModelToVo(
			PersonalHistoryVo personalHistoryVo) {
		// TODO Auto-generated method stub
		return null;
	}



	public PastHistoryCase convertPastHistoryFromModelToVo(
			PastHistoryVo pastHistoryVo) {
		// TODO Auto-generated method stub
		return null;
	}



	public PastHistoryVo convertPastHistoryFromViewToModel(
			PastHistoryCase pastHistoryCase) {
		// TODO Auto-generated method stub
		return null;
	}

}
