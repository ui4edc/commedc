package cn.com.ecrf.trq.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.MutilSelect;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;

@Service
public class ConvertorService {

	@Autowired
	private CRFMapper cRFMapper;
	
	public PatientInfoCase convertPatientFromViewToModel(
			PatientInfoVo patientInfoVo) {
		// TODO Auto-generated method stub
		PatientInfoCase patientInfoCase = new PatientInfoCase();
		//patientInfoCase.setAbbr(patientInfoVo.getAbbr());
		patientInfoCase.setAge(patientInfoVo.getAge());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		if (StringUtils.isNotBlank(patientInfoVo.getBirthday()))
			
				patientInfoCase.setBirthday(sdf.parse(patientInfoVo.getBirthday()));
			
		if (StringUtils.isNotBlank(patientInfoVo.getOutdate()))
			patientInfoCase.setCyrq(sdf.parse(patientInfoVo.getOutdate()));
		FormEnumObject ethicObj = new FormEnumObject(patientInfoVo.getEthic(), null, FormEnumValue.ETHIC);
		patientInfoCase.setEthic(convertIDToContent(ethicObj));
		patientInfoCase.setHeight(Integer.parseInt(patientInfoVo.getHeight()));
		patientInfoCase.setHeightud(patientInfoVo.isHeightud());
		FormEnumObject hysObj = new FormEnumObject(patientInfoVo.getHys(), null, FormEnumValue.HYS);
		patientInfoCase.setHys(convertIDToContent(hysObj));
		patientInfoCase.setId(patientInfoVo.getId());
		//patientInfoCase.setName(patientInfoVo.getName());
		patientInfoCase.setNo(patientInfoVo.getNo());
		if (StringUtils.isNotBlank(patientInfoVo.getIndate()))
			patientInfoCase.setRyrq(sdf.parse(patientInfoVo.getIndate()));
		FormEnumObject sexObj = new FormEnumObject(patientInfoVo.getSex(), null, FormEnumValue.SEX);
		patientInfoCase.setSex(convertIDToContent(sexObj));
		patientInfoCase.setWeight(Float.parseFloat(patientInfoVo.getWeight()));
		patientInfoCase.setWeightud(patientInfoVo.isWeightud());
		FormEnumObject ylfyfsObj = new FormEnumObject(patientInfoVo.getFeemode(), patientInfoVo.getFeemodetxt(), FormEnumValue.YLFYFS);
		patientInfoCase.setYlfyfs(convertIDToContent(ylfyfsObj));
/*		List<FormEnumObject> yyksobjs = new ArrayList<FormEnumObject>();
		//JSONArray jsonArray = new JSONArray();
		for (int i=0;patientInfoVo.getYyks() != null && i< patientInfoVo.getYyks().size();i++){
			CheckBoxVo checkBoxVo = (CheckBoxVo)patientInfoVo.getYyks().get(i);
			FormEnumObject yyksObj = new FormEnumObject(checkBoxVo.getId(), checkBoxVo.getOther(), FormEnumValue.YYKS);
			convertIDToContent(yyksObj);
			yyksobjs.add(yyksObj);
		}
		JSONUtils<FormEnumObject> util = new JSONUtils<FormEnumObject>(FormEnumObject.class);
		patientInfoCase.setYyks(util.convertFromList(yyksobjs));*/
		FormEnumObject yyksObj = new FormEnumObject(patientInfoVo.getYyks(), patientInfoVo.getYykstxt(), FormEnumValue.YYKS);
		patientInfoCase.setYyks(convertIDToContent(yyksObj));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientInfoCase;
	}
	
	public PatientInfoVo convertPatientFromModelToView(
			PatientInfoCase patientInfoCase) {
		// TODO Auto-generated method stub
		PatientInfoVo patientInfoVo = new PatientInfoVo();
		//patientInfoVo.setAbbr(patientInfoCase.getAbbr());
		patientInfoVo.setAge(patientInfoCase.getAge());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		if (patientInfoCase.getBirthday() != null)
			patientInfoVo.setBirthday(sdf.format(patientInfoCase.getBirthday()));
		if (patientInfoCase.getCyrq() != null)
			patientInfoVo.setOutdate(sdf.format(patientInfoCase.getCyrq()));
		FormEnumObject ethicObj = new FormEnumObject(patientInfoCase.getEthic(), FormEnumValue.ETHIC);
		patientInfoVo.setEthic(convertContentToID(ethicObj));
		patientInfoVo.setHeight(""+patientInfoCase.getHeight());
		patientInfoVo.setHeightud(patientInfoCase.isHeightud());
		FormEnumObject hysObj = new FormEnumObject(patientInfoCase.getHys(), FormEnumValue.HYS);
		patientInfoVo.setHys(convertContentToID(hysObj));
		patientInfoVo.setId(patientInfoCase.getId());
		//patientInfoCase.setName(patientInfoVo.getName());
		patientInfoVo.setNo(patientInfoCase.getNo());
		if (patientInfoCase.getRyrq() != null)
			patientInfoVo.setIndate(sdf.format(patientInfoCase.getRyrq()));
		FormEnumObject sexObj = new FormEnumObject(patientInfoCase.getSex(), FormEnumValue.SEX);
		patientInfoVo.setSex(convertContentToID(sexObj));
		patientInfoVo.setWeight(""+(int)patientInfoCase.getWeight());
		patientInfoVo.setWeightud(patientInfoCase.isWeightud());
		FormEnumObject ylfyfsObj = new FormEnumObject(patientInfoCase.getYlfyfs(), FormEnumValue.YLFYFS);
		patientInfoVo.setYyks(convertContentToID(ylfyfsObj));
		if (StringUtils.isNotBlank(ylfyfsObj.getOther()))
			patientInfoVo.setYykstxt(ylfyfsObj.getOther());
/*		List<FormEnumObject> yyksobjs = new ArrayList<FormEnumObject>();
		//JSONArray jsonArray = new JSONArray();
		for (int i=0;patientInfoVo.getYyks() != null && i< patientInfoVo.getYyks().size();i++){
			CheckBoxVo checkBoxVo = (CheckBoxVo)patientInfoVo.getYyks().get(i);
			FormEnumObject yyksObj = new FormEnumObject(checkBoxVo.getId(), checkBoxVo.getOther(), FormEnumValue.YYKS);
			convertIDToContent(yyksObj);
			yyksobjs.add(yyksObj);
		}
		JSONUtils<FormEnumObject> util = new JSONUtils<FormEnumObject>(FormEnumObject.class);
		patientInfoCase.setYyks(util.convertFromList(yyksobjs));*/
		FormEnumObject yyksObj = new FormEnumObject(patientInfoCase.getYyks(), FormEnumValue.YYKS);
		patientInfoVo.setYyks(convertContentToID(yyksObj));
		if (StringUtils.isNotBlank(yyksObj.getOther()))
			patientInfoVo.setYykstxt(yyksObj.getOther());
		FormEnumObject yyfyfsObj = new FormEnumObject(patientInfoCase.getYlfyfs(), FormEnumValue.YLFYFS);
		patientInfoVo.setFeemode(convertContentToID(yyksObj));
		if (StringUtils.isNotBlank(yyfyfsObj.getOther()))
			patientInfoVo.setFeemodetxt(yyfyfsObj.getOther());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientInfoVo;
	}

	public PersonalHistoryVo convertPersonHistoryFromModelToView(
			PersonAllergicHistoryCase personAllergicHistoryCase) {
		// TODO Auto-generated method stub
		PersonalHistoryVo personalHistoryVo = new PersonalHistoryVo();
		FormEnumObject drinkObj = new FormEnumObject(personAllergicHistoryCase.getDrink(), FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryVo.setDrink(convertContentToID(drinkObj));
		FormEnumObject smokeObj = new FormEnumObject(personAllergicHistoryCase.getSmoke(), FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryVo.setSmoke(convertContentToID(smokeObj));
		FormEnumObject hasDrugObj = new FormEnumObject(personAllergicHistoryCase.getDrug(), FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryVo.setHasDrug(convertContentToID(hasDrugObj));
		FormEnumObject hasFoodObj = new FormEnumObject(personAllergicHistoryCase.getFood(), FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryVo.setHasFood(convertContentToID(hasFoodObj));
		FormEnumObject hasOtherObj = new FormEnumObject(personAllergicHistoryCase.getOther(), FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryVo.setHasOther(convertContentToID(hasOtherObj));
		JSONUtils<MutilSelect> util = new JSONUtils<MutilSelect>(MutilSelect.class);
		
		if (StringUtils.isNotBlank(personAllergicHistoryCase.getDruglb()))
			personalHistoryVo.setDrug(util.convertFromString(personAllergicHistoryCase.getDruglb()));
		else
			personalHistoryVo.setDrug(new ArrayList<MutilSelect>());
		if (StringUtils.isNotBlank(personAllergicHistoryCase.getFoodlb()))
			personalHistoryVo.setFood(util.convertFromString(personAllergicHistoryCase.getFoodlb()));
		else
			personalHistoryVo.setFood(new ArrayList<MutilSelect>());
		if (StringUtils.isNotBlank(personAllergicHistoryCase.getOtherlb()))
			personalHistoryVo.setOther(util.convertFromString(personAllergicHistoryCase.getOtherlb()));
		else
			personalHistoryVo.setOther(new ArrayList<MutilSelect>());
		return personalHistoryVo;
	}



	public PersonAllergicHistoryCase convertPersonHistoryFromModelToVo(
			PersonalHistoryVo personalHistoryVo) {
		// TODO Auto-generated method stub
		PersonAllergicHistoryCase  personalHistoryCase = new PersonAllergicHistoryCase();
		personalHistoryCase.setNo(personalHistoryVo.getNo());
		FormEnumObject drinkObj = new FormEnumObject(personalHistoryVo.getDrink(), null, FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryCase.setDrink(convertIDToContent(drinkObj));
		FormEnumObject smokeObj = new FormEnumObject(personalHistoryVo.getSmoke(), null, FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryCase.setSmoke(convertIDToContent(smokeObj));
		FormEnumObject hasDrugObj = new FormEnumObject(personalHistoryVo.getSmoke(), null, FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryCase.setDrug(convertIDToContent(hasDrugObj));
		FormEnumObject hasFoodObj = new FormEnumObject(personalHistoryVo.getHasFood(), null, FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryCase.setFood(convertIDToContent(hasFoodObj));
		FormEnumObject hasOtherObj = new FormEnumObject(personalHistoryVo.getHasOther(), null, FormEnumValue.YES_NO_UNKNOWN);
		personalHistoryCase.setOther(convertIDToContent(hasOtherObj));
		JSONUtils<MutilSelect> util = new JSONUtils<MutilSelect>(MutilSelect.class);
		if (personalHistoryVo.getDrug() != null && personalHistoryVo.getDrug().size() > 0)
			personalHistoryCase.setDruglb(util.convertFromList(personalHistoryVo.getDrug()));
		if (personalHistoryVo.getFood() != null && personalHistoryVo.getFood().size() > 0)
			personalHistoryCase.setFoodlb(util.convertFromList(personalHistoryVo.getFood()));
		if (personalHistoryVo.getOther() != null && personalHistoryVo.getOther().size() > 0)
			personalHistoryCase.setOther(util.convertFromList(personalHistoryVo.getOther()));
		return personalHistoryCase;
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
