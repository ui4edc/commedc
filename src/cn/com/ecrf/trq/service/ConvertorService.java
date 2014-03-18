package cn.com.ecrf.trq.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.ADRCase;
import cn.com.ecrf.trq.model.BodyExamCase;
import cn.com.ecrf.trq.model.CRFFormEnum;
import cn.com.ecrf.trq.model.DiseaseInfoCase;
import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.DrugCombinationCase;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.DrugUseCase;
import cn.com.ecrf.trq.model.ECGExamCase;
import cn.com.ecrf.trq.model.LabExamCase;
import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.utils.DictUtils;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.PinyinUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.ADRVo;
import cn.com.ecrf.trq.vo.DiseaseInfoVo;
import cn.com.ecrf.trq.vo.DoubtRecordGetVo;
import cn.com.ecrf.trq.vo.DrugInstanceObject;
import cn.com.ecrf.trq.vo.DrugSummaryVo;
import cn.com.ecrf.trq.vo.DrugUseVo;
import cn.com.ecrf.trq.vo.MutilSelect;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;
import cn.com.ecrf.trq.vo.crf.BanDrug;
import cn.com.ecrf.trq.vo.lab.BodyExamVo;
import cn.com.ecrf.trq.vo.lab.DrugUseExamVo;
import cn.com.ecrf.trq.vo.lab.ECGExamVo;
import cn.com.ecrf.trq.vo.lab.InHospitalExamVo;
import cn.com.ecrf.trq.vo.lab.LabExamInstanceVo;
import cn.com.ecrf.trq.vo.lab.PlainExamVo;

@Service
public class ConvertorService {

	@Autowired
	private CRFMapper cRFMapper;
	@Autowired 
	private DictMapper dictMapper;
	
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
		patientInfoCase.setHeight(patientInfoVo.getHeight());
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
		patientInfoCase.setWeight(patientInfoVo.getWeight());
		patientInfoCase.setWeightud(patientInfoVo.isWeightud());
		FormEnumObject ylfyfsObj = new FormEnumObject(patientInfoVo.getFeemode(), null, FormEnumValue.YLFYFS);
		patientInfoCase.setYlfyfs(convertIDToContent(ylfyfsObj));
		patientInfoCase.setYlfyfstxt(patientInfoVo.getFeemodetxt());
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
		FormEnumObject yyksObj = new FormEnumObject(patientInfoVo.getYyks(), null, FormEnumValue.YYKS);
		patientInfoCase.setYyks(convertIDToContent(yyksObj));
		patientInfoCase.setYykstxt(patientInfoVo.getYykstxt());
		String yyks = patientInfoCase.getYyks();
		if (StringUtils.isNotBlank(patientInfoCase.getYyks())){
			yyks = patientInfoCase.getYyks();
		}
		try{
			List<DictRow> dictRows = getItemDict(yyks, DictUtils.dept);
			if (dictRows == null || dictRows.size() < 1){
				insertItemDict(yyks, DictUtils.dept);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
		if (StringUtils.isNotBlank(patientInfoCase.getEthic())){
			FormEnumObject ethicObj = new FormEnumObject(patientInfoCase.getEthic(), FormEnumValue.ETHIC);
			patientInfoVo.setEthic(convertContentToID(ethicObj));
		}
		patientInfoVo.setHeight(patientInfoCase.getHeight());
		patientInfoVo.setHeightud(patientInfoCase.isHeightud());
		if (StringUtils.isNotBlank(patientInfoCase.getHys())){
			FormEnumObject hysObj = new FormEnumObject(patientInfoCase.getHys(), FormEnumValue.HYS);
			patientInfoVo.setHys(convertContentToID(hysObj));
		}
		patientInfoVo.setId(patientInfoCase.getId());
		//patientInfoCase.setName(patientInfoVo.getName());
		patientInfoVo.setNo(patientInfoCase.getNo());
		if (patientInfoCase.getRyrq() != null)
			patientInfoVo.setIndate(sdf.format(patientInfoCase.getRyrq()));
		if (StringUtils.isNotBlank(patientInfoCase.getSex())){
			FormEnumObject sexObj = new FormEnumObject(patientInfoCase.getSex(), FormEnumValue.SEX);
			patientInfoVo.setSex(convertContentToID(sexObj));
		}
		patientInfoVo.setWeight(patientInfoCase.getWeight());
		patientInfoVo.setWeightud(patientInfoCase.isWeightud());
		if (StringUtils.isNotBlank(patientInfoCase.getYlfyfs())){
			FormEnumObject ylfyfsObj = new FormEnumObject(patientInfoCase.getYlfyfs(), FormEnumValue.YLFYFS);
			patientInfoVo.setFeemode(convertContentToID(ylfyfsObj));
		}
		patientInfoVo.setFeemodetxt(patientInfoCase.getYlfyfstxt());
		if (StringUtils.isNotBlank(patientInfoCase.getYyks())){
			FormEnumObject yyksObj = new FormEnumObject(patientInfoCase.getYyks(), FormEnumValue.YYKS);
			patientInfoVo.setYyks(convertContentToID(yyksObj));
		}
		patientInfoVo.setYykstxt(patientInfoCase.getYykstxt());
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
		personalHistoryVo.setId(personAllergicHistoryCase.getId());
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
		personalHistoryCase.setId(personalHistoryVo.getId());
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
			personalHistoryCase.setOtherlb(util.convertFromList(personalHistoryVo.getOther()));
		return personalHistoryCase;
	}



	public PastHistoryCase convertPastHistoryFromViewToModel(
			PastHistoryVo pastHistoryVo) {
		// TODO Auto-generated method stub
		PastHistoryCase pastHistoryCase = new PastHistoryCase();
		try {
			BeanUtils.copyProperties(pastHistoryCase, pastHistoryVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pastHistoryCase;
	}



	public PastHistoryVo convertPastHistoryFromModelToView(
			PastHistoryCase pastHistoryCase) {
		// TODO Auto-generated method stub
		PastHistoryVo pastHistoryVo = null;
		try {
			if (pastHistoryCase != null){
				pastHistoryVo = new PastHistoryVo();
				BeanUtils.copyProperties(pastHistoryVo, pastHistoryCase);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pastHistoryVo;
	}
	
	private String convertIDToContent(FormEnumObject obj) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		if (obj.getSeq() > 0){
			condition.put("seq", obj.getSeq());
			condition.put("type", obj.getType());
			CRFFormEnum formEnum = cRFMapper.getFormEnumValue(condition);
			if (formEnum != null)
				obj.setName(formEnum.getName());
			
		}
		return obj.getName();
	}

	private int convertContentToID(FormEnumObject obj) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("name", obj.getName());
		condition.put("type", obj.getType());
		CRFFormEnum formEnum = cRFMapper.getFormEnumValueByName(condition);
		/*if (seq == 0){
			condition = new HashMap<String, Object>();
			condition.put("name", FormEnumValue.OTHER);
			condition.put("type", obj.getType());
			seq = cRFMapper.getFormEnumValueByName(condition);
			obj.setOther(obj.getName());
		}*/
		int seq = 0;
		if (formEnum != null)
			seq = formEnum.getSeq();
		obj.setSeq(seq);
		return obj.getSeq();
	}

	public DiseaseInfoCase convertDiseaseInfoFromViewToModel(
			DiseaseInfoVo diseaseInfoVo) {
		// TODO Auto-generated method stub
		DiseaseInfoCase diseaseInfoCase = null;
		try {
			if (diseaseInfoVo != null){
				diseaseInfoCase = new DiseaseInfoCase();
				BeanUtils.copyProperties(diseaseInfoCase, diseaseInfoVo);
			}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diseaseInfoCase;
	}

	public DiseaseInfoVo convertDiseaseInfoFromModelToView(
			DiseaseInfoCase diseaseInfoCase) {
		// TODO Auto-generated method stub
		DiseaseInfoVo diseaseInfoVo = null;
		try {
			if (diseaseInfoCase != null){
				diseaseInfoVo = new DiseaseInfoVo();
				BeanUtils.copyProperties(diseaseInfoVo, diseaseInfoCase);
			}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diseaseInfoVo;
	}

	public DrugUseVo convertDrugUseInfoFromModelToView(
			DrugUseCase drugUseInfoCase) {
		// TODO Auto-generated method stub
		DrugUseVo drugUseVo = null;
		try {
			if (drugUseInfoCase != null){
				drugUseVo = new DrugUseVo();
				BeanUtils.copyProperties(drugUseVo, drugUseInfoCase);
				JSONUtils<BanDrug> util = new JSONUtils<BanDrug>(BanDrug.class);
				if (StringUtils.isNotBlank(drugUseInfoCase.getBanDruglb()))
					drugUseVo.setBanDrug(util.convertFromString(drugUseInfoCase.getBanDruglb()));
				if (StringUtils.isNotBlank(drugUseInfoCase.getBottlelb()))
					drugUseVo.setBottle(util.convertFromString(drugUseInfoCase.getBottlelb()));
				if (StringUtils.isNotBlank(drugUseInfoCase.getInjectionlb()))
					drugUseVo.setInjection(util.convertFromString(drugUseInfoCase.getInjectionlb()));
				//if (StringUtils.isNotBlank(drugUseInfoCase.getGrouplb()))
					//drugUseVo.setGroup(util.convertFromString(drugUseInfoCase.getGrouplb()));
				if (drugUseInfoCase.getFood() == null)
					drugUseVo.setFood("");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugUseVo;
	}

	public DrugUseCase convertDrugUseInfoFromViewToModel(DrugUseVo drugUseVo) {
		// TODO Auto-generated method stub
		DrugUseCase drugUseCase = null;
		try {
			if (drugUseVo != null){
				drugUseCase = new DrugUseCase();
				BeanUtils.copyProperties(drugUseCase, drugUseVo);
				JSONUtils<BanDrug> util = new JSONUtils<BanDrug>(BanDrug.class);
				if (drugUseVo.getBanDrug()!= null && drugUseVo.getBanDrug().size() > 0){
					drugUseCase.setBanDruglb(util.convertFromList(drugUseVo.getBanDrug()));
					for (BanDrug banDrug : drugUseVo.getBanDrug()){
						try{
							List<DictRow> dictRows = getItemDict(banDrug.getName(), DictUtils.drug);
							if (dictRows == null || dictRows.size() < 1){
								insertItemDict(banDrug.getName(), DictUtils.drug);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
				}
				if (drugUseVo.getBottle() != null && drugUseVo.getBottle().size() > 0){
					drugUseCase.setBottlelb(util.convertFromList(drugUseVo.getBottle()));
					for (BanDrug banDrug : drugUseVo.getBanDrug()){
						try{
							List<DictRow> dictRows = getItemDict(banDrug.getName(), DictUtils.drug);
							if (dictRows == null || dictRows.size() < 1){
								insertItemDict(banDrug.getName(), DictUtils.drug);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
				}
				if (drugUseVo.getInjection() != null && drugUseVo.getInjection().size() > 0){
					drugUseCase.setInjectionlb(util.convertFromList(drugUseVo.getInjection()));
					for (BanDrug banDrug : drugUseVo.getBanDrug()){
						try{
							List<DictRow> dictRows = getItemDict(banDrug.getName(), DictUtils.drug);
							if (dictRows == null || dictRows.size() < 1){
								insertItemDict(banDrug.getName(), DictUtils.drug);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				/*if (drugUseVo.getGroup() != null && drugUseVo.getGroup().size() > 0){
					drugUseCase.setGrouplb(util.convertFromList(drugUseVo.getGroup()));
					for (BanDrug banDrug : drugUseVo.getBanDrug()){
						try{
							List<DictRow> dictRows = getItemDict(banDrug.getName(), DictUtils.drug);
							if (dictRows == null || dictRows.size() < 1){
								insertItemDict(banDrug.getName(), DictUtils.drug);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}*/
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugUseCase;
	}

	private List<DictRow> getItemDict(String name, String type) {
		// TODO Auto-generated method stub
		List<DictRow> rows = null;
		try {
			DictRow dictRow = new DictRow();
			dictRow.setName(name);
			dictRow.setAbbr(PinyinUtils.getFirstHanyuPinyin(name));
			dictRow.setType(type);
			dictRow.setTypeAbbr(PinyinUtils.getFirstHanyuPinyin(type));
			rows = dictMapper.getExactDictRow(dictRow);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}
	
	private void insertItemDict(String name, String type) {
		// TODO Auto-generated method stub
		try {
			DictRow dictRow = new DictRow();
			dictRow.setName(name);
			dictRow.setAbbr(PinyinUtils.getFirstHanyuPinyin(name));
			dictRow.setType(type);
			dictRow.setTypeAbbr(PinyinUtils.getFirstHanyuPinyin(type));
			dictMapper.insertDictRow(dictRow);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DrugUseVo convertDrugUseInfoFromJSONToView(String json) {
		// TODO Auto-generated method stub
		return null;
	}

	public DrugCombinationCase convertDrugCombinationCaseFromViewToModel(
			DrugInstanceObject drugInstanceObject, String no) {
		// TODO Auto-generated method stub
		DrugCombinationCase drugCombinationCase = new DrugCombinationCase();
		drugCombinationCase.setName(drugInstanceObject.getName());
		try{
			List<DictRow> dictRows = getItemDict(drugInstanceObject.getName(), DictUtils.drug);
			if (dictRows == null || dictRows.size() < 1){
				insertItemDict(drugInstanceObject.getName(), DictUtils.drug);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		drugCombinationCase.setSeq(drugInstanceObject.getId());
		drugCombinationCase.setNo(no);
		drugCombinationCase.setDose(drugInstanceObject.getDose());
		drugCombinationCase.setFrequency(drugInstanceObject.getFrequency());
		drugCombinationCase.setUnit(drugInstanceObject.getUnit());
		drugCombinationCase.setWay(drugInstanceObject.getWay());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (StringUtils.isNotBlank(drugInstanceObject.getStart())){
				Date startDate = sdf.parse(drugInstanceObject.getStart());
				drugCombinationCase.setStartDate(startDate);
			}
			if (StringUtils.isNotBlank(drugInstanceObject.getEnd())){
				Date endDate = sdf.parse(drugInstanceObject.getEnd());
				drugCombinationCase.setEndDate(endDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugCombinationCase;
	}
	
	public  DrugInstanceObject convertDrugCombinationCaseFromModelToView(
			DrugCombinationCase drugCombinationCase) {
		// TODO Auto-generated method stub
		DrugInstanceObject drugInstanceObject = new DrugInstanceObject();
		try {
			BeanUtils.copyProperties(drugInstanceObject, drugCombinationCase);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (drugCombinationCase.getStartDate() != null){
				String start = sdf.format(drugCombinationCase.getStartDate());
				drugInstanceObject.setStart(start);
			}
			if (drugCombinationCase.getEndDate() != null){
				String end = sdf.format(drugCombinationCase.getEndDate());
				drugInstanceObject.setEnd(end);
			}
			drugInstanceObject.setId(drugCombinationCase.getSeq());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugInstanceObject;
	}

	public DrugUseExamVo convertDrugUseExamFromModelToView(
			LabExamCase labExamCase) {
		// TODO Auto-generated method stub
		DrugUseExamVo drugUseExamVo = null;
		try {
			if (labExamCase != null){
				drugUseExamVo = new DrugUseExamVo();
				BeanUtils.copyProperties(drugUseExamVo, labExamCase);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (labExamCase.getExamDateDate() != null)
					drugUseExamVo.setExamDate(sdf.format(labExamCase.getExamDateDate()));
				JSONUtils<LabExamInstanceVo> util = new JSONUtils<LabExamInstanceVo>(LabExamInstanceVo.class);
				if (StringUtils.isNotBlank(labExamCase.getData1lb()))
					drugUseExamVo.setData1(util.convertFromString(labExamCase.getData1lb()));
				if (StringUtils.isNotBlank(labExamCase.getData2lb()))
					drugUseExamVo.setData2(util.convertFromString(labExamCase.getData2lb()));
				if (StringUtils.isNotBlank(labExamCase.getData3lb()))
					drugUseExamVo.setData3(util.convertFromString(labExamCase.getData3lb()));
				if (StringUtils.isNotBlank(labExamCase.getData4lb()))
					drugUseExamVo.setData4(util.convertFromString(labExamCase.getData4lb()));
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugUseExamVo;
	}
	
	public LabExamCase convertDrugUseExamFromViewToModel(
			DrugUseExamVo drugUseExamVo) {
		// TODO Auto-generated method stub
		LabExamCase labExamCase = new LabExamCase();
		try {
			BeanUtils.copyProperties(labExamCase, drugUseExamVo);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotBlank(drugUseExamVo.getExamDate()))
				labExamCase.setExamDateDate(sdf.parse(drugUseExamVo.getExamDate()));
			JSONUtils<LabExamInstanceVo> util = new JSONUtils<LabExamInstanceVo>(LabExamInstanceVo.class);
			if (drugUseExamVo.getData1() != null && drugUseExamVo.getData1().size() > 0)
				labExamCase.setData1lb(util.convertFromList(drugUseExamVo.getData1()));
			if (drugUseExamVo.getData2() != null && drugUseExamVo.getData2().size() > 0)
				labExamCase.setData2lb(util.convertFromList(drugUseExamVo.getData2()));
			if (drugUseExamVo.getData3() != null && drugUseExamVo.getData3().size() > 0)
				labExamCase.setData3lb(util.convertFromList(drugUseExamVo.getData3()));
			if (drugUseExamVo.getData4() != null && drugUseExamVo.getData4().size() > 0)
				labExamCase.setData4lb(util.convertFromList(drugUseExamVo.getData4()));
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return labExamCase;
	}

	public DrugSummaryVo convertDrugSummaryFromModelToView(
			DrugSummaryCase drugSummaryCase) {
		// TODO Auto-generated method stub
		DrugSummaryVo drugSummaryVo = new DrugSummaryVo();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			BeanUtils.copyProperties(drugSummaryCase, drugSummaryVo);
			if (drugSummaryCase.getDeathDateDate() != null){
				drugSummaryVo.setDeathDate(sdf.format(drugSummaryCase.getDeathDateDate()));
			}
			if (drugSummaryCase.getStartDateDate() != null){
				drugSummaryVo.setStartDate(sdf.format(drugSummaryCase.getStartDateDate()));
			}
			if (drugSummaryCase.getEndDateDate() != null){
				drugSummaryVo.setEndDate(sdf.format(drugSummaryCase.getEndDateDate()));
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return drugSummaryVo;
	}

	public DrugSummaryCase convertDrugSummaryFromViewToModel(
			DrugSummaryVo drugSummaryVo) {
		// TODO Auto-generated method stub
		DrugSummaryCase drugSummaryCase = new DrugSummaryCase();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			BeanUtils.copyProperties(drugSummaryCase, drugSummaryVo);
			if (StringUtils.isNotBlank(drugSummaryVo.getDeathDate())){
				drugSummaryCase.setDeathDateDate(sdf.parse(drugSummaryVo.getDeathDate()));
			}
			if (StringUtils.isNotBlank(drugSummaryVo.getEndDate())){
				drugSummaryCase.setEndDateDate(sdf.parse(drugSummaryVo.getEndDate()));
			}
			if (StringUtils.isNotBlank(drugSummaryVo.getStartDate())){
				drugSummaryCase.setStartDateDate(sdf.parse(drugSummaryVo.getStartDate()));
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return drugSummaryCase;
	}

	public ADRCase convertADRFromViewToModel(ADRVo adrVo) {
		// TODO Auto-generated method stub
		ADRCase aDRCase = new ADRCase();
		try {
			BeanUtils.copyProperties(aDRCase, adrVo);
			if (StringUtils.isNotBlank(adrVo.getAdrDate())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date adrDateDate = sdf.parse(adrVo.getAdrDate());
					aDRCase.setAdrDateDate(adrDateDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject adrDeal3Obj = new FormEnumObject(adrVo.getAdrDeal3(), adrVo.getAdrDeal3txt(), FormEnumValue.ADR_DEAL3);
			aDRCase.setAdrDeal3Str(convertIDToContent(adrDeal3Obj));
			FormEnumObject adrDealObj = new FormEnumObject(adrVo.getAdrDeal(), null, FormEnumValue.ADR_DEAL);
			aDRCase.setAdrDealStr(convertIDToContent(adrDealObj));
			FormEnumObject adrRestartObj = new FormEnumObject(adrVo.getAdrRestart(), null, FormEnumValue.ADR_RESTART);
			aDRCase.setAdrRestartStr(convertIDToContent(adrRestartObj));
			FormEnumObject adrStopObj = new FormEnumObject(adrVo.getAdrStop(), null, FormEnumValue.ADR_STOP);
			aDRCase.setAdrStopStr(convertIDToContent(adrStopObj));
			if (StringUtils.isNotBlank(adrVo.getBirthday())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date birthdayDate = sdf.parse(adrVo.getBirthday());
					aDRCase.setBirthdayDate(birthdayDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject bloodObj = new FormEnumObject(adrVo.getBlood(), null, FormEnumValue.YES_NO);
			aDRCase.setBloodStr(convertIDToContent(bloodObj));
			FormEnumObject careerObj = new FormEnumObject(adrVo.getCareer(), adrVo.getCareertxt(), FormEnumValue.CAREER);
			aDRCase.setCareerStr(convertIDToContent(careerObj));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			if (adrVo.getDrug1() != null && adrVo.getDrug1().size() > 0)
				aDRCase.setDrug1Str(util.convertFromList(adrVo.getDrug1()));
			if (adrVo.getDrug2() != null && adrVo.getDrug2().size() > 0)
				aDRCase.setDrug2Str(util.convertFromList(adrVo.getDrug2()));
			FormEnumObject endingObj = new FormEnumObject(adrVo.getEnding(), adrVo.getEndingtxt(), FormEnumValue.ADR_RESULT);
			aDRCase.setEndingStr(convertIDToContent(endingObj));
			FormEnumObject ethicObj = new FormEnumObject(adrVo.getEthic(), null, FormEnumValue.ETHIC);
			aDRCase.setEthicStr(convertIDToContent(ethicObj));
			FormEnumObject evaluateObj = new FormEnumObject(adrVo.getEvaluate(), null, FormEnumValue.ADR_EVALUATE);
			aDRCase.setEvaluateStr(convertIDToContent(evaluateObj));
			FormEnumObject familyadrObj = new FormEnumObject(adrVo.getFamilyadr(), adrVo.getFamilyadrtxt(), FormEnumValue.YES_NO_UNKNOWN);
			aDRCase.setFamilyadrStr(convertIDToContent(familyadrObj));
			FormEnumObject historyadrObj = new FormEnumObject(adrVo.getHistoryadr(), adrVo.getHistoryadrtxt(), FormEnumValue.YES_NO_UNKNOWN);
			aDRCase.setHistoryadrStr(convertIDToContent(historyadrObj));
			FormEnumObject relationshipObj = new FormEnumObject(adrVo.getRelationship(), null, FormEnumValue.ADR_RELATIONSHIP);
			aDRCase.setRelationshipStr(convertIDToContent(relationshipObj));
			if (StringUtils.isNotBlank(adrVo.getDeathDate())){
				try {
					Date deathDateDate = sdf.parse(adrVo.getDeathDate());
					aDRCase.setDeathDateDate(deathDateDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (StringUtils.isNotBlank(adrVo.getReportDate())){
				try {
					Date reportDateDate = sdf.parse(adrVo.getReportDate());
					aDRCase.setReportDateDate(reportDateDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject sexObj = new FormEnumObject(adrVo.getSex(), null, FormEnumValue.SEX);
			aDRCase.setSexStr(convertIDToContent(sexObj));
			FormEnumObject typeObj = new FormEnumObject(adrVo.getType(), null, FormEnumValue.ADR_TYPE);
			aDRCase.setTypeStr(convertIDToContent(typeObj));
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aDRCase;
	}

	public ADRVo convertADRFromModelToView(ADRCase adrCase) {
		// TODO Auto-generated method stub
		ADRVo aDRVo = new ADRVo();
		try {
			BeanUtils.copyProperties(aDRVo, adrCase);
			if (adrCase.getAdrDateDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					String adrDate = sdf.format(adrCase.getAdrDateDate());
					aDRVo.setAdrDate(adrDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject adrDeal3Obj = new FormEnumObject(adrCase.getAdrDeal3Str(), FormEnumValue.ADR_DEAL3);
			aDRVo.setAdrDeal3(convertContentToID(adrDeal3Obj));
			FormEnumObject adrDealObj = new FormEnumObject(adrCase.getAdrDealStr(), FormEnumValue.ADR_DEAL);
			aDRVo.setAdrDeal(convertContentToID(adrDealObj));
			FormEnumObject adrRestartObj = new FormEnumObject(adrCase.getAdrRestartStr(), FormEnumValue.ADR_RESTART);
			aDRVo.setAdrRestart(convertContentToID(adrRestartObj));
			FormEnumObject adrStopObj = new FormEnumObject(adrCase.getAdrStopStr(), FormEnumValue.ADR_STOP);
			aDRVo.setAdrStop(convertContentToID(adrStopObj));
			if (adrCase.getBirthdayDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					String birthday = sdf.format(adrCase.getBirthdayDate());
					aDRVo.setBirthday(birthday);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject bloodObj = new FormEnumObject(adrCase.getBloodStr(), FormEnumValue.YES_NO);
			aDRVo.setBlood(convertContentToID(bloodObj));
			FormEnumObject careerObj = new FormEnumObject(adrCase.getCareerStr(), FormEnumValue.CAREER);
			aDRVo.setCareer(convertContentToID(careerObj));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			if (StringUtils.isNotBlank(adrCase.getDrug1Str()))
				aDRVo.setDrug1(util.convertFromString(adrCase.getDrug1Str()));
			if (StringUtils.isNotBlank(adrCase.getDrug2Str()))
				aDRVo.setDrug2(util.convertFromString(adrCase.getDrug2Str()));
			FormEnumObject endingObj = new FormEnumObject(adrCase.getEndingStr(), FormEnumValue.ADR_ENDING);
			aDRVo.setEnding(convertContentToID(endingObj));
			FormEnumObject ethicObj = new FormEnumObject(adrCase.getEthicStr(), FormEnumValue.ETHIC);
			aDRVo.setEthic(convertContentToID(ethicObj));
			FormEnumObject evaluateObj = new FormEnumObject(adrCase.getEvaluateStr(), FormEnumValue.ADR_EVALUATE);
			aDRVo.setEvaluate(convertContentToID(evaluateObj));
			FormEnumObject familyadrObj = new FormEnumObject(adrCase.getFamilyadrStr(), FormEnumValue.YES_NO_UNKNOWN);
			aDRVo.setFamilyadr(convertContentToID(familyadrObj));
			FormEnumObject historyadrObj = new FormEnumObject(adrCase.getHistoryadrStr(), FormEnumValue.YES_NO_UNKNOWN);
			aDRVo.setHistoryadr(convertContentToID(historyadrObj));
			aDRVo.setInfo(aDRVo.getInfo());
			aDRVo.setInfo6txt(aDRVo.getInfo6txt());
			aDRVo.setInfo7txt(aDRVo.getInfo7txt());
			FormEnumObject relationshipObj = new FormEnumObject(adrCase.getRelationshipStr(), FormEnumValue.ADR_RELATIONSHIP);
			aDRVo.setRelationship(convertContentToID(relationshipObj));
			if (adrCase.getDeathDateDate() != null){
				try {
					String deathDate = sdf.format(adrCase.getDeathDateDate());
					aDRVo.setDeathDate(deathDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (adrCase.getReportDateDate() != null){
				try {
					String reportDate = sdf.format(adrCase.getReportDateDate());
					aDRVo.setReportDate(reportDate);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			FormEnumObject sexObj = new FormEnumObject(adrCase.getSexStr(), FormEnumValue.SEX);
			aDRVo.setSex(convertContentToID(sexObj));
			FormEnumObject typeObj = new FormEnumObject(adrCase.getTypeStr(), FormEnumValue.ADR_TYPE);
			aDRVo.setType(convertContentToID(typeObj));
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aDRVo;
	}

	public DoubtRecordGetVo convertDoubtRecordFromModelToView(DoubtRecord record) {
		// TODO Auto-generated method stub
		DoubtRecordGetVo doubtRecordVo = new DoubtRecordGetVo();
		doubtRecordVo.setDescription(record.getDescription());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (record.getDoubtDate() != null)
			doubtRecordVo.setDoubtDate(sdf.format(record.getDoubtDate()));
		doubtRecordVo.setDoubter(record.getDoubter());
		doubtRecordVo.setDoubtField(record.getFieldName());
		doubtRecordVo.setDoubtId(record.getDoubtId());
		return doubtRecordVo;
	}

	public BodyExamVo convertBodyExamFromModelToView(BodyExamCase bodyExamCase) {
		// TODO Auto-generated method stub
		BodyExamVo bodyExamVo = null;
		try {
				if (bodyExamCase != null){
					bodyExamVo = new BodyExamVo();
					BeanUtils.copyProperties(bodyExamVo,bodyExamCase);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (bodyExamCase.getExamDateDate() != null){
						bodyExamVo.setExamDate(sdf.format(bodyExamCase.getExamDateDate()));
					}
				}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return bodyExamVo;
	}

	public BodyExamCase convertBodyExamFromViewToModel(BodyExamVo bodyExamVo) {
		// TODO Auto-generated method stub
		BodyExamCase bodyExamCase = null;
		try {
				bodyExamCase = new BodyExamCase();
				BeanUtils.copyProperties(bodyExamCase, bodyExamVo);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (StringUtils.isNotBlank(bodyExamVo.getExamDate())){
					bodyExamCase.setExamDateDate(sdf.parse(bodyExamVo.getExamDate()));
				}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bodyExamCase;
	}



	public ECGExamVo convertECGExamFromModelToView(ECGExamCase eCGExamCase) {
		// TODO Auto-generated method stub
		ECGExamVo eCGExamVo = null;
		
		try {
				if (eCGExamCase != null){
					eCGExamVo = new ECGExamVo();
					BeanUtils.copyProperties(eCGExamVo, eCGExamCase);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if (eCGExamCase.getExamDateDate() != null){
						eCGExamVo.setExamDate(sdf.format(eCGExamCase.getExamDateDate()));
					}
				}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eCGExamVo;
	}



	public ECGExamCase convertECGExamFromViewToModel(ECGExamVo eCGExamVo) {
		// TODO Auto-generated method stub
		ECGExamCase eCGExamCase = null;
		try {
				if (eCGExamVo != null){
					eCGExamCase = new ECGExamCase();
					BeanUtils.copyProperties(eCGExamCase, eCGExamVo);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (StringUtils.isNotBlank(eCGExamVo.getExamDate())){
						eCGExamCase.setExamDateDate(sdf.parse(eCGExamVo.getExamDate()));
					}
				}
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eCGExamCase;
	}










	/*public void convertFromDrugSummaryToAdr(DrugSummaryCase drugSummaryCase,
			ADRVo adrVo) {
		// TODO Auto-generated method stub
		adrVo.setAdr(drugSummaryCase.getAdr());
		adrVo.setAdr1(drugSummaryCase.getAdr1());
	
	}*/



}
