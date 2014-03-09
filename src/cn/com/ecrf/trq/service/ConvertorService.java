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
import cn.com.ecrf.trq.model.CRFFormEnum;
import cn.com.ecrf.trq.model.DiseaseInfoCase;
import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.DrugCombinationCase;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.DrugUseCase;
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
import cn.com.ecrf.trq.vo.lab.DrugUseExamVo;
import cn.com.ecrf.trq.vo.lab.InHospitalExamVo;
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

	public InHospitalExamVo convertLabExamFromModelToView(
			LabExamCase labExamCase) {
		// TODO Auto-generated method stub
		if (labExamCase == null)
			return null;
		InHospitalExamVo inHospitalExamVo = new InHospitalExamVo();
		if (StringUtils.isNotBlank(labExamCase.getData1())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data1 = util.convertFromString(labExamCase.getData1());
			inHospitalExamVo.setData1(data1);
		}
		if (StringUtils.isNotBlank(labExamCase.getData2())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data2 = util.convertFromString(labExamCase.getData2());
			inHospitalExamVo.setData2(data2);
		}
		if (StringUtils.isNotBlank(labExamCase.getData3())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data3 = util.convertFromString(labExamCase.getData3());
			inHospitalExamVo.setData3(data3);
		}
		if (StringUtils.isNotBlank(labExamCase.getData4())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data4 = util.convertFromString(labExamCase.getData4());
			inHospitalExamVo.setData1(data4);
		}
		if (StringUtils.isNotBlank(labExamCase.getData5())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data5 = util.convertFromString(labExamCase.getData5());
			inHospitalExamVo.setData5(data5);
		}
		if (StringUtils.isNotBlank(labExamCase.getData6())){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			List<PlainExamVo> data6 = util.convertFromString(labExamCase.getData6());
			inHospitalExamVo.setData6(data6);
		}
		inHospitalExamVo.setId(labExamCase.getId());
		if (StringUtils.isNotBlank(labExamCase.getDone())){
			FormEnumObject doneObj = new FormEnumObject(labExamCase.getDone(), FormEnumValue.DONE);
			inHospitalExamVo.setDone(convertContentToID(doneObj));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (labExamCase.getExamDate() != null){
			String examDate = sdf.format(labExamCase.getExamDate());
			inHospitalExamVo.setExamDate(examDate);
		}
		inHospitalExamVo.setId(labExamCase.getId());
		inHospitalExamVo.setNo(labExamCase.getNo());
		inHospitalExamVo.setResult(labExamCase.getResult());
		inHospitalExamVo.setResulttxt1(labExamCase.getResulttxt1());
		inHospitalExamVo.setResulttxt2(labExamCase.getResulttxt2());
		inHospitalExamVo.setResulttxt3(labExamCase.getResulttxt3());
		if (StringUtils.isNotBlank(labExamCase.getSample())){
			FormEnumObject sampleObj = new FormEnumObject(labExamCase.getSample(), FormEnumValue.SJYB);
			inHospitalExamVo.setSample(convertContentToID(sampleObj));
			inHospitalExamVo.setSampletxt(sampleObj.getOther());
		}
		return inHospitalExamVo;
	}

	public LabExamCase convertLabExamFromViewToModel(
			InHospitalExamVo inHospitalExamVo, int phase) {
		// TODO Auto-generated method stub
		LabExamCase labExamCase = new LabExamCase();
		if (inHospitalExamVo.getData1() != null && inHospitalExamVo.getData1().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData1(util.convertFromList(inHospitalExamVo.getData1()));
		}
		if (inHospitalExamVo.getData2() != null && inHospitalExamVo.getData2().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData2(util.convertFromList(inHospitalExamVo.getData2()));
		}
		if (inHospitalExamVo.getData3() != null && inHospitalExamVo.getData3().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData3(util.convertFromList(inHospitalExamVo.getData3()));
		}
		if (inHospitalExamVo.getData4() != null && inHospitalExamVo.getData4().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData4(util.convertFromList(inHospitalExamVo.getData4()));
		}
		if (inHospitalExamVo.getData5() != null && inHospitalExamVo.getData5().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData5(util.convertFromList(inHospitalExamVo.getData5()));
		}
		if (inHospitalExamVo.getData6() != null && inHospitalExamVo.getData6().size() > 0){
			JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
			labExamCase.setData6(util.convertFromList(inHospitalExamVo.getData6()));
		}
		if (inHospitalExamVo.getDone() > 0){
			FormEnumObject doneObj = new FormEnumObject(inHospitalExamVo.getDone(), null, FormEnumValue.DONE);
			labExamCase.setDone(convertIDToContent(doneObj));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(inHospitalExamVo.getExamDate())){
			Date examDate;
			try {
				examDate = sdf.parse(inHospitalExamVo.getExamDate());
				labExamCase.setExamDate(examDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		labExamCase.setId(inHospitalExamVo.getId());
		labExamCase.setNo(inHospitalExamVo.getNo());
		labExamCase.setPhase(phase);
		labExamCase.setResult(inHospitalExamVo.getResult());
		labExamCase.setResulttxt1(inHospitalExamVo.getResulttxt1());
		labExamCase.setResulttxt2(inHospitalExamVo.getResulttxt2());
		labExamCase.setResulttxt3(inHospitalExamVo.getResulttxt3());
		if (inHospitalExamVo.getSample() > 0){
			FormEnumObject sampleObj = new FormEnumObject(inHospitalExamVo.getSample(), inHospitalExamVo.getSampletxt(), FormEnumValue.SJYB);
			labExamCase.setSample(convertIDToContent(sampleObj));
		}
		return labExamCase;
	}

	public InHospitalExamVo convertOtherLabExamToInHospitalExam(
			DrugUseExamVo drugUseExamVo) {
		// TODO Auto-generated method stub
		InHospitalExamVo inHospitalExamVo = new InHospitalExamVo();
		inHospitalExamVo.setData1(drugUseExamVo.getData1());
		inHospitalExamVo.setData2(drugUseExamVo.getData2());
		inHospitalExamVo.setData3(drugUseExamVo.getData3());
		inHospitalExamVo.setData4(drugUseExamVo.getData4());
		inHospitalExamVo.setData5(drugUseExamVo.getData5());
		inHospitalExamVo.setData6(drugUseExamVo.getData6());
		inHospitalExamVo.setDone(drugUseExamVo.getDone());
		inHospitalExamVo.setNo(drugUseExamVo.getNo());
		inHospitalExamVo.setId(drugUseExamVo.getId());
		return inHospitalExamVo;
	}
	
	public DrugUseExamVo convertInHospitalExamToOtherLabExam(
			InHospitalExamVo inHospitalExamVo) {
		// TODO Auto-generated method stub
		DrugUseExamVo drugUseExamVo = new DrugUseExamVo();
		drugUseExamVo.setData1(inHospitalExamVo.getData1());
		drugUseExamVo.setData2(inHospitalExamVo.getData2());
		drugUseExamVo.setData3(inHospitalExamVo.getData3());
		drugUseExamVo.setData4(inHospitalExamVo.getData4());
		drugUseExamVo.setData5(inHospitalExamVo.getData5());
		drugUseExamVo.setData6(inHospitalExamVo.getData6());
		drugUseExamVo.setDone(inHospitalExamVo.getDone());
		drugUseExamVo.setId(inHospitalExamVo.getId());
		drugUseExamVo.setNo(inHospitalExamVo.getNo());
		return drugUseExamVo;
	}

	public DrugSummaryVo convertDrugSummaryFromModelToView(
			DrugSummaryCase drugSummaryCase) {
		// TODO Auto-generated method stub
		DrugSummaryVo drugSummaryVo = new DrugSummaryVo();
		FormEnumObject adrObj = new FormEnumObject(drugSummaryCase.getAdr(), FormEnumValue.YES_NO);
		drugSummaryVo.setAdr(convertContentToID(adrObj));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (drugSummaryCase.getDeathDate() != null){
			String deathDate = sdf.format(drugSummaryCase.getDeathDate());
			drugSummaryVo.setDeathDate(deathDate);
		}
		drugSummaryVo.setDeathReason(drugSummaryCase.getDeathReason());
		drugSummaryVo.setDrugCost(drugSummaryCase.getDrugCost());
		if (drugSummaryCase.getStartDate() != null){
			String startDate = sdf.format(drugSummaryCase.getStartDate());
			drugSummaryVo.setStartDate(startDate);
		}
		if (drugSummaryCase.getEndDate() != null){
			String endDate = sdf.format(drugSummaryCase.getEndDate());
			drugSummaryVo.setEndDate(endDate);
		}
		FormEnumObject endObj = new FormEnumObject(drugSummaryCase.getEnding(), FormEnumValue.DM_ENDING);
		drugSummaryVo.setEnding(convertContentToID(endObj));
		drugSummaryVo.setId(drugSummaryCase.getId());
		FormEnumObject interventionObj = new FormEnumObject(drugSummaryCase.getIntervention(), FormEnumValue.YES_NO);
		drugSummaryVo.setIntervention(convertContentToID(interventionObj));
		drugSummaryVo.setInterventiontxt(drugSummaryCase.getInterventiontxt());
		drugSummaryVo.setNo(drugSummaryCase.getNo());
		drugSummaryVo.setRemark(drugSummaryCase.getRemark());
		drugSummaryVo.setTreatmentCost(drugSummaryCase.getTreatmentCost());
		drugSummaryVo.setTrqCost(drugSummaryCase.getTrqCost());
		FormEnumObject unreasonableObj = new FormEnumObject(drugSummaryCase.getUnreasonable(), FormEnumValue.HAS_OR_NOT);
		drugSummaryVo.setUnreasonable(convertContentToID(unreasonableObj));
		return drugSummaryVo;
	}

	public DrugSummaryCase convertDrugSummaryFromViewToModel(
			DrugSummaryVo drugSummaryVo) {
		// TODO Auto-generated method stub
		DrugSummaryCase drugSummaryCase = new DrugSummaryCase();
		FormEnumObject adrObj = new FormEnumObject(drugSummaryVo.getAdr(), null, FormEnumValue.YES_NO);
		drugSummaryCase.setAdr(convertIDToContent(adrObj));
		if (StringUtils.isNotBlank(drugSummaryVo.getDeathDate())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date deathDate = sdf.parse(drugSummaryVo.getDeathDate());
				drugSummaryCase.setDeathDate(deathDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		drugSummaryCase.setDeathReason(drugSummaryVo.getDeathReason());
		drugSummaryCase.setDrugCost(drugSummaryVo.getDrugCost());
		if (StringUtils.isNotBlank(drugSummaryVo.getEndDate())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date endDate = sdf.parse(drugSummaryVo.getEndDate());
				drugSummaryCase.setEndDate(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FormEnumObject endingObj = new FormEnumObject(drugSummaryVo.getEnding(), null, FormEnumValue.DM_ENDING);
		drugSummaryCase.setEnding(convertIDToContent(endingObj));
		drugSummaryCase.setId(drugSummaryVo.getId());
		FormEnumObject interventionObj = new FormEnumObject(drugSummaryVo.getIntervention(), null, FormEnumValue.YES_NO);
		drugSummaryCase.setIntervention(convertIDToContent(interventionObj));
		drugSummaryCase.setInterventiontxt(drugSummaryVo.getInterventiontxt());
		drugSummaryCase.setNo(drugSummaryVo.getNo());
		drugSummaryCase.setRemark(drugSummaryVo.getRemark());
		if (StringUtils.isNotBlank(drugSummaryVo.getStartDate())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate = sdf.parse(drugSummaryVo.getStartDate());
				drugSummaryCase.setStartDate(startDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		drugSummaryCase.setTreatmentCost(drugSummaryVo.getTreatmentCost());
		drugSummaryCase.setTrqCost(drugSummaryVo.getTrqCost());
		FormEnumObject unreasonableObj = new FormEnumObject(drugSummaryVo.getUnreasonable(), null, FormEnumValue.HAS_OR_NOT);
		drugSummaryCase.setUnreasonable(convertIDToContent(unreasonableObj));
		//drugSummaryCase.setEnding(ending);
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



}
