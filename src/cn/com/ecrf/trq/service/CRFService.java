package cn.com.ecrf.trq.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PhaseSignPage;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.CheckBoxVo;
import cn.com.ecrf.trq.vo.ListConditionVo;
import cn.com.ecrf.trq.vo.ListNotifyVo;
import cn.com.ecrf.trq.vo.ListReturnVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;

@Service
public class CRFService {
	@Autowired
	private CRFMapper cRFMapper;
	
	public ListNotifyVo getNotifyInfo() {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		
		return null;
	}

	public List<ListReturnVo> getCRFList(ListConditionVo condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getCRFTotal(ListConditionVo condition){
		int total = 0;
		return total;
	}

	public PatientInfoVo getPatientInfo(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public PhaseSignPage getPhaseSignInfo(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public Map<String, Object> savePatientInfo(PatientInfoVo patientInfoVo) {
		// TODO Auto-generated method stub
		if (patientInfoVo.getId() > 0){
			//update
			
		}else{
			//insert
			PatientInfoCase patientInfoCase =  convertPatientFromVoToModel(patientInfoVo);
		}
		return null;
	}

	private PatientInfoCase convertPatientFromVoToModel(
			PatientInfoVo patientInfoVo) {
		// TODO Auto-generated method stub
		PatientInfoCase patientInfoCase = new PatientInfoCase();
		patientInfoCase.setAbbr(patientInfoVo.getAbbr());
		patientInfoCase.setAge(Integer.parseInt(patientInfoVo.getAge()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		if (StringUtils.isNotBlank(patientInfoVo.getBirthday()))
			
				patientInfoCase.setBirthday(sdf.parse(patientInfoVo.getBirthday()));
			
		if (StringUtils.isNotBlank(patientInfoVo.getCyrq()))
			patientInfoCase.setCyrq(sdf.parse(patientInfoVo.getCyrq()));
		FormEnumObject ethicObj = new FormEnumObject(patientInfoVo.getEthic(), patientInfoVo.getEthictxt(), FormEnumValue.ETHIC);
		patientInfoCase.setEthic(convertIDToContent(ethicObj));
		patientInfoCase.setHeight(Integer.parseInt(patientInfoVo.getHeight()));
		FormEnumObject hysObj = new FormEnumObject(patientInfoVo.getHys(), null, FormEnumValue.HYS);
		patientInfoCase.setHys(convertIDToContent(hysObj));
		patientInfoCase.setId(patientInfoVo.getId());
		patientInfoCase.setName(patientInfoVo.getName());
		patientInfoCase.setNo(patientInfoVo.getNo());
		if (StringUtils.isNotBlank(patientInfoVo.getRyrq()))
			patientInfoCase.setRyrq(sdf.parse(patientInfoVo.getRyrq()));
		FormEnumObject sexObj = new FormEnumObject(patientInfoVo.getSex(), null, FormEnumValue.SEX);
		patientInfoCase.setSex(convertIDToContent(sexObj));
		patientInfoCase.setWeight(Float.parseFloat(patientInfoVo.getWeight()));
		FormEnumObject ylfyfsObj = new FormEnumObject(patientInfoVo.getSex(), null, FormEnumValue.YLFYFS);
		patientInfoCase.setYlfyfs(convertIDToContent(ylfyfsObj));
		List<FormEnumObject> yyksobjs = new ArrayList<FormEnumObject>();
		for (int i=0;patientInfoVo.getYyks() != null && i< patientInfoVo.getYyks().size();i++){
			CheckBoxVo checkBoxVo = (CheckBoxVo)patientInfoVo.getYyks().get(i);
			FormEnumObject yyksObj = new FormEnumObject(checkBoxVo.getId(), checkBoxVo.getOther(), FormEnumValue.YLFYFS);
			convertIDToContent(yyksObj);
			yyksobjs.add(yyksObj);
		}
		JSONArray jsonArray = new JSONArray();
		//jsonArray.
		//JSONArray arry=JSONArray.fromObject(yyksobjs);
		patientInfoCase.setYyks("");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private String convertIDToContent(FormEnumObject obj) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		if (obj.getSeq() > 0){
			condition.put("seq", obj.getSeq());
			condition.put("type", obj.getType());
			String value = cRFMapper.getFormEnumValue(condition);
			if (!StringUtils.isNotBlank(value) || "其他".equals(value))
				value = obj.getOther();
			obj.setName(value);
		}
		return obj.getName();
	}

	private int convertContentToID(FormEnumObject obj) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("name", obj.getName());
		condition.put("type", obj.getType());
		int seq = cRFMapper.getFormEnumValueByName(condition);
		if (seq == 0){
			condition = new HashMap<String, Object>();
			condition.put("name", FormEnumValue.OTHER);
			condition.put("type", obj.getType());
			seq = cRFMapper.getFormEnumValueByName(condition);
			obj.setOther(obj.getName());
		}
		obj.setSeq(seq);
		return obj.getSeq();
	}


	
}
