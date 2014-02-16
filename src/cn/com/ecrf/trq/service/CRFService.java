package cn.com.ecrf.trq.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.ADRCase;
import cn.com.ecrf.trq.model.CRFUserSign;
import cn.com.ecrf.trq.model.DiseaseInfoCase;
import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.DrugCombinationCase;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.DrugUseCase;
import cn.com.ecrf.trq.model.FieldDict;
import cn.com.ecrf.trq.model.LabExamCase;
import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.PhaseSignPage;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.DoubtRecordMapper;
import cn.com.ecrf.trq.repository.UserSignMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.LockStatusUtils;
import cn.com.ecrf.trq.utils.ProcessUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.ADRVo;
import cn.com.ecrf.trq.vo.CheckBoxVo;
import cn.com.ecrf.trq.vo.DiseaseInfoVo;
import cn.com.ecrf.trq.vo.DoubtRecordSubmitVo;
import cn.com.ecrf.trq.vo.DrugCombinationVo;
import cn.com.ecrf.trq.vo.DrugInstanceObject;
import cn.com.ecrf.trq.vo.DrugSummaryVo;
import cn.com.ecrf.trq.vo.DrugUseVo;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;
import cn.com.ecrf.trq.vo.crf.FieldDictVo;
import cn.com.ecrf.trq.vo.lab.DrugUseExamVo;
import cn.com.ecrf.trq.vo.lab.InHospitalExamVo;
import cn.com.ecrf.trq.vo.list.ListConditionVo;
import cn.com.ecrf.trq.vo.list.ListNotifyVo;
import cn.com.ecrf.trq.vo.list.ListReturnVo;

@Service
public class CRFService {
	@Autowired
	private CRFMapper cRFMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private ConvertorService convertorService;
	@Autowired
	private UserSignMapper userSignMapper;
	@Autowired
	private DoubtRecordMapper doubtRecordMapper;
	
	public ListNotifyVo getNotifyInfo() {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		int doubtNumber = cRFMapper.getDoubtCRFNum(userName);
		int toDoNumber = cRFMapper.getToDoNum(userName);
		ListNotifyVo notify = new ListNotifyVo();
		notify.setQuestionNum(doubtNumber);
		notify.setDeadlineNum(toDoNumber);
		return notify;
	}

	public List<ListReturnVo> getCRFList(ListConditionVo condition) {
		// TODO Auto-generated method stub
		List<ListReturnVo> listVo = null;
		try{
			ListCondition sqlCondition = convertCondition(condition);
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			sqlCondition.setUserName(userName);
			int type = condition.getType();
			List<ListReturn> list = null;
			/*if (type == 0 || type == 1 || type == 3){//草稿,提交，审核通过
				list = cRFMapper.getPatientList(sqlCondition);
			}else if (type == 2){//质疑
				list = cRFMapper.getDoutSummaryList(sqlCondition);
			}*/
			switch(type){
			case 0:
				list = cRFMapper.getPatientList(sqlCondition);
				break;
			case 1:
				list = cRFMapper.getPatientList(sqlCondition);
				break;
			case 2:
				list = cRFMapper.getDoutSummaryList(sqlCondition);
			case 3: 
				list = cRFMapper.getPatientList(sqlCondition);
				break;
			case 4:
				//监察，
				list = cRFMapper.getPatientList(sqlCondition);
				break;
			case 5:
				list = cRFMapper.getDoutSummaryList(sqlCondition);
				break;
			case 6: 
				list = cRFMapper.getPatientList(sqlCondition);
				break;
			}
			listVo = convertListReturn(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listVo;
	}
	
	private List<ListReturnVo> convertListReturn(List<ListReturn> list) {
		// TODO Auto-generated method stub
		List<ListReturnVo> listVo = new ArrayList<ListReturnVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (list != null){
			for (ListReturn result : list){
				ListReturnVo resultVo = new ListReturnVo();
				resultVo.setAbbr(result.getAbbr());
				if (result.getCreateDate() != null)
					resultVo.setCreateDate(sdf.format(result.getCreateDate()));
				if (result.getDoubtDate() != null)
					resultVo.setDoubtDate(sdf.format(result.getDoubtDate()));
				resultVo.setDoubter(result.getDoubter());
				resultVo.setId(result.getId());
				if (result.getLastModified() != null)
					resultVo.setLastModified(sdf.format(result.getLastModified()));
				resultVo.setNo(result.getNo());
				if (result.getProgress() > 0)
					resultVo.setProgress(result.getProgress() + "%");
				listVo.add(resultVo);
			}
		}
		return listVo;
	}

	private ListCondition convertCondition(ListConditionVo condition) {
		// TODO Auto-generated method stub
		ListCondition sqlCondition = new ListCondition();
		int lockStatus = 0;
		if (condition.getType() == 0)
			lockStatus = 10;
		if (condition.getType() == 1 || condition.getType() == 4)
			lockStatus = 20;
		if (condition.getType() == 2 || condition.getType() == 5)
			lockStatus = 30;
		if (condition.getType() == 3 || condition.getType() == 6)
			lockStatus = 40;
		
		sqlCondition.setLockStatus(lockStatus);
		sqlCondition.setAbbr(condition.getAbbr());
		String progress = condition.getProgress();
		if (StringUtils.isNotBlank(progress))
			sqlCondition.setProgress(Integer.parseInt(progress.replace("%", "")));
		sqlCondition.setProgressType(condition.getProgressType());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (StringUtils.isNotBlank(condition.getCreateDateForm())){		
				sqlCondition.setCreateDateForm(sdf.parse(condition.getCreateDateForm()));
				sqlCondition.setCreateDateTo(sdf.parse(condition.getCreateDateTo()));
			}
			if (StringUtils.isNotBlank(condition.getLastModifiedFrom())){
				sqlCondition.setLastModifiedFrom(sdf.parse(condition.getCreateDateForm()));
				sqlCondition.setLastModifiedTo(sdf.parse(condition.getCreateDateTo()));
			}
			if (StringUtils.isNotBlank(condition.getDoubtDateFrom())){
				sqlCondition.setDoubtDateFrom(sdf.parse(condition.getCreateDateForm()));
				sqlCondition.setDoubtDateTo(sdf.parse(condition.getCreateDateTo()));
			}
			sqlCondition.setCrf(condition.isCrf());
			sqlCondition.setDesc(condition.isDesc());
			sqlCondition.setNo(condition.getNo());
			if (condition.getPageNo() > 0 && condition.getPageSize() > 0){
				int pageNo = condition.getPageNo();
				int pageSize = condition.getPageSize();
				sqlCondition.setLimitStart((pageNo-1)*pageSize);
				sqlCondition.setLimitSize(pageSize);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlCondition;
	}

	public int getCRFTotal(ListConditionVo condition){
		int total = 0;
		
		try{
			ListCondition sqlCondition = convertCondition(condition);
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			sqlCondition.setUserName(userName);
			total = cRFMapper.getTotalPatientNum(sqlCondition);
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}

	public PhaseSignPage getPhaseSignInfo(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	

	
	
	

	

	public Map<String, Object> genCRFNo(String abbr) {
		// TODO Auto-generated method stub
		String no = "";
		Map<String, Object> result = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser = SecurityUtils.getSubject();  
			String userName = (String)currentUser.getPrincipal();
			User user = userService.findUserByLoginName(userName);
			Organization organization = userService.getOrganization(user.getOrganizationId());
			int  seq = cRFMapper.getNextSeq(organization.getId());
			DecimalFormat df = new DecimalFormat("0000");
			String subNo = df.format(seq);
			no = organization.getCode() + "-" + subNo;
/*			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("no", no);
			condition.put("abbr", abbr);
			condition.put("id", 0);*/
			PatientInfoCase patientInfoCase = new PatientInfoCase();
			patientInfoCase.setNo(no);
			patientInfoCase.setAbbr(abbr);
			patientInfoCase.setCreateBy(userName);
			cRFMapper.insertCRF(patientInfoCase);
			CRFUserSign userSign = new CRFUserSign();
			userSign.setNo(no);
			userSign.setLockStatus(LockStatusUtils.draft);
			userSign.setCroName(userName);
			userSign.setProgress(0);
			userSignMapper.insertUserSign(userSign);
			int id = patientInfoCase.getId();
			cRFMapper.updateNextSeq(organization.getId());
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("no", no);
			result.put("id", id);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> getCRFSumm(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			ListReturn listReturn = cRFMapper.getCRFSumm(Integer.parseInt(id));
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("abbr", listReturn.getAbbr());
			result.put("no", listReturn.getNo());
			result.put("progress", listReturn.getProgress() + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> getBasicInfo(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(Integer.parseInt(id));
			PatientInfoVo patientInfoVo = convertorService.convertPatientFromModelToView(patientInfoCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, patientInfoVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> savePatientInfo(PatientInfoVo patientInfoVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PatientInfoCase patientInfoCase =  convertorService.convertPatientFromViewToModel(patientInfoVo);
			if (patientInfoVo.getId() > 0){
				//update
				cRFMapper.updatePatientInfo(patientInfoCase);
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("progress", ProcessUtils.BASIC_INFO);
				condition.put("no", patientInfoVo.getNo());
				cRFMapper.updateProgress(condition);
			}else{
				//insert
				cRFMapper.insertPatientInfo(patientInfoCase);
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("progress", ProcessUtils.BASIC_INFO);
				condition.put("no", patientInfoVo.getNo());
				cRFMapper.updateProgress(condition);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			
			int progress = cRFMapper.getProgress(patientInfoCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, "保存基本信息失败");
		}
		
		return result;
	}
	
	public Map<String, Object> getPersonHistory(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			PersonAllergicHistoryCase personAllergicHistoryCase = cRFMapper.getPersonHistory(Integer.parseInt(id));
			PersonalHistoryVo personalHistoryVo = new PersonalHistoryVo();
			if (personAllergicHistoryCase != null)
				personalHistoryVo = convertorService.convertPersonHistoryFromModelToView(personAllergicHistoryCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, personalHistoryVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> savePersonHistory(
			PersonalHistoryVo personalHistoryVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PersonAllergicHistoryCase personAllergicHistoryCase =  convertorService.convertPersonHistoryFromModelToVo(personalHistoryVo);
			PersonAllergicHistoryCase dbCase = cRFMapper.getPersonHistory(personAllergicHistoryCase.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updatePersonHistory(personAllergicHistoryCase);
			else {
				cRFMapper.insertPersonHistory(personAllergicHistoryCase);
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("progress", ProcessUtils.PERSON_HISTORY);
			condition.put("no", personalHistoryVo.getNo());
			cRFMapper.updateProgress(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(personAllergicHistoryCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> getPastHistory(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			PastHistoryCase pastHistoryCase = cRFMapper.getPastHistory(Integer.parseInt(id));
			PastHistoryVo pastHistoryVo = convertorService.convertPastHistoryFromModelToView(pastHistoryCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, pastHistoryVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> savePastHistory(PastHistoryVo pastHistoryVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PastHistoryCase pastHistoryCase =  convertorService.convertPastHistoryFromViewToModel(pastHistoryVo);
			PastHistoryCase dbCase = cRFMapper.getPastHistory(pastHistoryCase.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updatePastHistory(pastHistoryCase);
			else {
				cRFMapper.insertPastHistory(pastHistoryCase);
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("progress", ProcessUtils.PASS_HISTORY);
			condition.put("no", pastHistoryVo.getNo());
			cRFMapper.updateProgress(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(pastHistoryCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> batchDelete(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			if (StringUtils.isNotBlank(id)){
				String[] idArray = id.split(",");
				for (String str : idArray){
					cRFMapper.deletePatientInfo(Integer.parseInt(str));
				}
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> saveDeseaseInfo(DiseaseInfoVo diseaseInfoVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			DiseaseInfoCase diseaseInfoCase =  convertorService.convertDiseaseInfoFromViewToModel(diseaseInfoVo);
			DiseaseInfoCase dbCase = cRFMapper.getDiseaseInfo(diseaseInfoVo.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateDiseaseInfo(diseaseInfoCase);
			else {
				cRFMapper.insertDiseaseInfo(diseaseInfoCase);
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("progress", ProcessUtils.DISEASE_INFO);
			condition.put("no", diseaseInfoVo.getNo());
			cRFMapper.updateProgress(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(diseaseInfoVo.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> getDeseaseInfo(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DiseaseInfoCase diseaseInfoCase = cRFMapper.getDiseaseInfo(Integer.parseInt(id));
			DiseaseInfoVo diseaseInfoVo = convertorService.convertDiseaseInfoFromModelToView(diseaseInfoCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, diseaseInfoVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> saveDrugUseInfo(DrugUseVo drugUseVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			DrugUseCase drugUseCase =  convertorService.convertDrugUseInfoFromViewToModel(drugUseVo);
			DrugUseCase dbCase = cRFMapper.getDrugUseInfo(drugUseVo.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateDrugUseInfo(drugUseCase);
			else {
				cRFMapper.insertDrugUseInfo(drugUseCase);
			}
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("progress", ProcessUtils.DISEASE_INFO);
			condition.put("no", drugUseVo.getNo());
			cRFMapper.updateProgress(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(drugUseVo.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> gettDrugUseInfo(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DrugUseCase drugUseInfoCase = cRFMapper.getDrugUseInfo(Integer.parseInt(id));
			DrugUseVo drugUseVo = convertorService.convertDrugUseInfoFromModelToView(drugUseInfoCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugUseVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> saveDrugCombinationInfo(
			DrugCombinationVo drugCombinationVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			List<DrugInstanceObject> drugs = drugCombinationVo.getDrug();
			for (int i=0;drugs != null && i<drugs.size();i++){
				DrugCombinationCase drugCombinationCase =  convertorService.convertDrugCombinationCaseFromViewToModel(drugs.get(i), drugCombinationVo.getNo());
				if (drugCombinationCase.getSeq() > 0){
					cRFMapper.updateDrugCombination(drugCombinationCase);
				}else{
					cRFMapper.deleteDrugCombination(drugCombinationVo.getNo());
					cRFMapper.insertDrugCombination(drugCombinationCase);
				}
			}
			int progress = cRFMapper.getProgress(drugCombinationVo.getNo());
			if (progress < ProcessUtils.DISEASE_INFO){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("progress", ProcessUtils.DISEASE_INFO);
				condition.put("no", drugCombinationVo.getNo());
				cRFMapper.updateProgress(condition);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			progress = cRFMapper.getProgress(drugCombinationVo.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	



	public Map<String, Object> saveDrugSummary(DrugSummaryVo drugSummaryVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			DrugSummaryCase drugSummaryCase =  convertorService.convertDrugSummaryFromViewToModel(drugSummaryVo);
			DrugSummaryCase dbCase = cRFMapper.getDrugSummary(drugSummaryVo.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateDrugSummary(drugSummaryCase);
			else {
				cRFMapper.insertDrugSummary(drugSummaryCase);
			}
			int progress = cRFMapper.getProgress(drugSummaryVo.getNo());
			if (progress < ProcessUtils.DRUG_SUMMARY){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("progress", ProcessUtils.DISEASE_INFO);
				condition.put("no", drugSummaryVo.getNo());
				cRFMapper.updateProgress(condition);
				progress = cRFMapper.getProgress(drugSummaryVo.getNo());
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> saveADR(ADRVo adrVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			ADRCase aDRCase =  convertorService.convertADRFromViewToModel(adrVo);
			ADRCase dbCase = cRFMapper.getADR(adrVo.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateADR(aDRCase);
			else {
				cRFMapper.insertADR(aDRCase);
			}
			int progress = cRFMapper.getProgress(adrVo.getNo());
			if (progress < ProcessUtils.ADR){
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("progress", ProcessUtils.ADR);
				condition.put("no", adrVo.getNo());
				cRFMapper.updateProgress(condition);
				progress = cRFMapper.getProgress(adrVo.getNo());
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> saveTotalCRF(int id) {
		// TODO Auto-generated method stub
		
		Map<String, Object> result;
		try{
			CRFUserSign userSign = new CRFUserSign();
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
			userSign.setNo(patientInfoCase.getNo());
			userSign.setLockStatus(LockStatusUtils.submit);
			userSign.setCroSignTime(new Date());
			userSignMapper.updateUserSign(userSign);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	

	public Map<String, Object> getDrugCombinationInfo(String id, String no) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			List<DrugCombinationCase> drugCombinationCases = cRFMapper.getDrugCombinationList(Integer.parseInt(id));
			DrugCombinationVo drugCombinationVo = new DrugCombinationVo();
			drugCombinationVo.setId(Integer.parseInt(id));
			drugCombinationVo.setNo(no);
			List<DrugInstanceObject> drugInstanceObjects = new ArrayList<DrugInstanceObject>();
			for (int i=0;drugCombinationCases!=null && i<drugCombinationCases.size();i++){
				DrugInstanceObject drugInstanceObject = convertorService.convertDrugCombinationCaseFromModelToView(drugCombinationCases.get(i));
				drugInstanceObjects.add(drugInstanceObject);
			}
			drugCombinationVo.setDrug(drugInstanceObjects);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugCombinationVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}

	public Map<String, Object> getInHospitalExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("phase", 1);
			LabExamCase labExamCase = cRFMapper.getLabExamCase(condition);
			InHospitalExamVo inHospitalExamVo = convertorService.convertLabExamFromModelToView(labExamCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, inHospitalExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}
	
	public Map<String, Object> getDrugUseExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("phase", 2);
			LabExamCase labExamCase = cRFMapper.getLabExamCase(condition);
			InHospitalExamVo inHospitalExamVo = convertorService.convertLabExamFromModelToView(labExamCase);
			DrugUseExamVo drugUseExamVo = convertorService.convertInHospitalExamToOtherLabExam(inHospitalExamVo);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugUseExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}
	
	public Map<String, Object> getOutHospitalExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("phase", 3);
			LabExamCase labExamCase = cRFMapper.getLabExamCase(condition);
			InHospitalExamVo inHospitalExamVo = convertorService.convertLabExamFromModelToView(labExamCase);
			DrugUseExamVo drugUseExamVo = convertorService.convertInHospitalExamToOtherLabExam(inHospitalExamVo);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugUseExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}
	
	public Map<String, Object> getDrugSummary(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DrugSummaryCase drugSummaryCase = cRFMapper.getDrugSummary(id);
			DrugSummaryVo drugSummaryVo = convertorService.convertDrugSummaryFromModelToView(drugSummaryCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugSummaryVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}
	
	
	public Map<String, Object> saveInHospitalExam(
			InHospitalExamVo inHospitalExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = saveLabExam(inHospitalExamVo, 1, ProcessUtils.IN_HOSPITAL_EXAM);
		return result;
	}
	
	public Map<String, Object> saveDrugUseExam(DrugUseExamVo drugUseExamVo) {
		// TODO Auto-generated method stub
		InHospitalExamVo inHospitalExamVo = convertorService.convertOtherLabExamToInHospitalExam(drugUseExamVo);
		Map<String, Object> result = saveLabExam(inHospitalExamVo, 2, ProcessUtils.DURING_DRUG_EXAM);
		return result;
	}

	public Map<String, Object> saveOutHospitalExam(DrugUseExamVo drugUseExamVo) {
		// TODO Auto-generated method stub
		InHospitalExamVo inHospitalExamVo = convertorService.convertOtherLabExamToInHospitalExam(drugUseExamVo);
		Map<String, Object> result = saveLabExam(inHospitalExamVo, 3, ProcessUtils.OUT_HOSPITAL_EXAM);
		return result;
	}
	
	private Map<String, Object> saveLabExam(InHospitalExamVo inHospitalExamVo, int phase, int progressLevel){
		Map<String, Object> result;
		try{
			LabExamCase labExamCase =  convertorService.convertLabExamFromViewToModel(inHospitalExamVo, phase);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", inHospitalExamVo.getId());
			condition.put("phase", phase);
			LabExamCase dbCase = cRFMapper.getLabExamCase(condition);
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateLabExamCase(labExamCase);
			else {
				cRFMapper.insertLabExamCase(labExamCase);
			}
			int progress = cRFMapper.getProgress(inHospitalExamVo.getNo());
			if (progress < progressLevel){
				condition = new HashMap<String, Object>();
				condition.put("progress", progressLevel);
				condition.put("no", inHospitalExamVo.getNo());
				cRFMapper.updateProgress(condition);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			progress = cRFMapper.getProgress(inHospitalExamVo.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;	
	}

	public Map<String, Object> getADR(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			ADRCase adrCase = cRFMapper.getADR(id);
			ADRVo adrVo = convertorService.convertADRFromModelToView(adrCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, adrVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}

	public Map<String, Object> saveDoubtRecord(
			DoubtRecordSubmitVo doubtRecordSubmitVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DoubtRecord doubtRecord = new DoubtRecord();
			doubtRecord.setDescription(doubtRecordSubmitVo.getDescription());
			doubtRecord.setDoubtDate(new Date());
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			doubtRecord.setDoubter(userName);
			doubtRecord.setFieldId(doubtRecordSubmitVo.getFieldId());
			doubtRecord.setId(doubtRecordSubmitVo.getId());
			doubtRecord.setMenuId(doubtRecordSubmitVo.getMenu());
			doubtRecordMapper.insertDoubtRecord(doubtRecord);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}
	
	public Map<String, Object> getDoubtRecord(int id, int menu) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DoubtRecord doubtRecord = new DoubtRecord();
			doubtRecord.setId(id);
			doubtRecord.setMenuId(menu);
			List<DoubtRecord> doubtRecords = doubtRecordMapper.getDoubtRecord(doubtRecord);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, doubtRecords);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> getDoubtDict(int menu) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			List<FieldDict> data = doubtRecordMapper.getCRFFieldDict(menu);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, data);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}

	public Map<String, Object> commitDoubtColumn(
			DoubtRecordSubmitVo doubtRecordSubmitVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DoubtRecord doubtRecord = new DoubtRecord();
			doubtRecord.setFieldId(doubtRecordSubmitVo.getFieldId());
			doubtRecord.setId(doubtRecordSubmitVo.getId());
			doubtRecord.setMenuId(doubtRecordSubmitVo.getMenu());
			doubtRecord.setCommitDate(new Date());
			doubtRecord.setFlag(1);
			doubtRecordMapper.saveDoubtRecord(doubtRecord);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}



	



	
	
	


	
}
