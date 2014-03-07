package cn.com.ecrf.trq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.stat.AgeStat;
import cn.com.ecrf.trq.model.stat.HospitalStat;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.OrganizationMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;

@Service
public class StatService {

	@Autowired
	private CRFMapper cRFMapper;
	@Autowired
	private OrganizationMapper organizationMapper;

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
			List<Organization> organizaitons = organizationMapper.findAllOrganizations();
			HospitalStat hospitalStatVo = null;
			List<HospitalStat> hospitalStatVos = null;
			Map<String, HospitalStat> map = new HashMap<String, HospitalStat>();
			if (hospitalStats != null){
				for (HospitalStat hospitalStat : hospitalStats){
					if (map.containsKey(hospitalStat.getName())){
						
					}else{
						hospitalStatVo = new HospitalStat();
						hospitalStatVo.setName(hospitalStat.getName());
						map.put(hospitalStat.getName(), hospitalStatVo);
					}
					hospitalStatVo = map.get(hospitalStat.getName());
					switch (hospitalStat.getLockStatus()){
					case 10:
						hospitalStatVo.setNum1(hospitalStat.getNumber());
						break;
					case 20:
						hospitalStatVo.setNum2(hospitalStat.getNumber());
						break;
					case 30:
						hospitalStatVo.setNum3(hospitalStat.getNumber());
						break;
					case 40:
						hospitalStatVo.setNum4(hospitalStat.getNumber());
						break;
					}
					
				}
			}
			if (organizaitons != null){
				for (Organization organizaiton : organizaitons){
					HospitalStat stat = map.get(organizaiton.getName());
					if (stat == null){
						stat = new HospitalStat();
						stat.setName(organizaiton.getName());
						map.put(organizaiton.getName(), stat);
					}
					stat.setNum5(organizaiton.getInstanceNumber());
				}
			}
			TreeMap<String, HospitalStat> sortMap = new TreeMap<String, HospitalStat>(map);
			hospitalStatVos = new ArrayList<HospitalStat>(sortMap.values());
			result = AjaxReturnUtils.generateAjaxReturn(true, null, hospitalStatVos);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}
	
	
}
