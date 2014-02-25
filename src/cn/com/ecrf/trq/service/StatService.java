package cn.com.ecrf.trq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.stat.AgeStat;
import cn.com.ecrf.trq.model.stat.HospitalStat;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;

@Service
public class StatService {

	@Autowired
	private CRFMapper cRFMapper;

	public Map<String, Object> getAgeStat() {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			List<AgeStat> ageStats = new ArrayList<AgeStat>();
			for (int i=10;i<100;i=i+10){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("start", i);
				condition.put("end", i+10);
				int ageNum = cRFMapper.getAgeStat(condition);
				AgeStat ageStat = new AgeStat();
				ageStat.setName(""+i);
				ageStat.setNumber(ageNum);
				ageStats.add(ageStat);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null, ageStats);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> getSexStat() {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			List<AgeStat> ageStats = cRFMapper.getSexStat();
			result = AjaxReturnUtils.generateAjaxReturn(true, null, ageStats);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> getHospitalStat() {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			List<HospitalStat> hospitalStats = cRFMapper.getHospitalStat();
			result = AjaxReturnUtils.generateAjaxReturn(true, null, hospitalStats);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}
	
	
}
