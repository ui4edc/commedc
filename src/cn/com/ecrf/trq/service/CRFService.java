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
import cn.com.ecrf.trq.model.dict.StaticDict;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.repository.DoubtRecordMapper;
import cn.com.ecrf.trq.repository.RoleMapper;
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
import cn.com.ecrf.trq.vo.DoubtRecordGetVo;
import cn.com.ecrf.trq.vo.DoubtRecordSubmitVo;
import cn.com.ecrf.trq.vo.DrugCombinationVo;
import cn.com.ecrf.trq.vo.DrugInstanceObject;
import cn.com.ecrf.trq.vo.DrugSummaryVo;
import cn.com.ecrf.trq.vo.DrugUseVo;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;
import cn.com.ecrf.trq.vo.crf.FieldDictVo;
import cn.com.ecrf.trq.vo.dict.StaticDictVo;
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
	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private RoleMapper roleMapper;
	
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

	public Map<String, Object> getCRFList(ListConditionVo condition) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		List<ListReturnVo> listVo = null;
		try{
			ListCondition sqlCondition = convertCondition(condition);
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			sqlCondition.setUserName(userName);
			int type = condition.getType();
			List<Role> roles = roleMapper.getRoleByUserName(userName);
			String roleName = null;
			if (roles != null && roles.size() > 0){
				roleName = roles.get(0).getRoleName();
				sqlCondition.setRoleName(roleName);
			}
			List<ListReturn> list = null;
			int total = 0;
			/*if (type == 0 || type == 1 || type == 3){//草稿,提交，审核通过
				list = cRFMapper.getPatientList(sqlCondition);
			}else if (type == 2){//质疑
				list = cRFMapper.getDoutSummaryList(sqlCondition);
			}*/
			switch(type){
			case 0:
				list = cRFMapper.getPatientList(sqlCondition);
				total = cRFMapper.getTotalPatientNum(sqlCondition);
				break;
			case 1:
				list = cRFMapper.getPatientList(sqlCondition);
				total = cRFMapper.getTotalPatientNum(sqlCondition);
				break;
			case 2:
				list = cRFMapper.getDoutSummaryList(sqlCondition);
				total = cRFMapper.getTotalPatientNum(sqlCondition);
				break;
			case 3: 
				list = cRFMapper.getPatientList(sqlCondition);
				total = cRFMapper.getTotalPatientNum(sqlCondition);
				break;
			case 4:
				//监察，
				if ("CRM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}
				break;
			case 5:
				if ("CRM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}
				break;
			case 6: 
				if ("CRM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}
				break;
			case 7: 
				if ("CRM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}
				break;
			case 8: 
				if ("CRM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getPatientList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}
				break;
			}
			listVo = convertListReturn(list);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, listVo, total);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}
	
	private List<ListReturnVo> convertListReturn(List<ListReturn> list) {
		// TODO Auto-generated method stub
		List<ListReturnVo> listVo = new ArrayList<ListReturnVo>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		if (condition.getType() == 1 || condition.getType() == 4 || condition.getType() == 7)
			lockStatus = 20;
		if (condition.getType() == 2 || condition.getType() == 5 || condition.getType() == 8)
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
			if (StringUtils.isNotBlank(condition.getCreateDateFrom()) && StringUtils.isNotBlank(condition.getCreateDateTo())){		
				sqlCondition.setCreateDateFrom(sdf.parse(condition.getCreateDateFrom()));
				sqlCondition.setCreateDateTo(sdf.parse(condition.getCreateDateTo()));
			}
			if (StringUtils.isNotBlank(condition.getLastModifiedFrom()) && StringUtils.isNotBlank(condition.getLastModifiedTo())){
				sqlCondition.setLastModifiedFrom(sdf.parse(condition.getLastModifiedFrom()));
				sqlCondition.setLastModifiedTo(sdf.parse(condition.getLastModifiedTo()));
			}
			if (StringUtils.isNotBlank(condition.getDoubtDateFrom()) && StringUtils.isNotBlank(condition.getDoubtDateTo())){
				sqlCondition.setDoubtDateFrom(sdf.parse(condition.getDoubtDateFrom()));
				sqlCondition.setDoubtDateTo(sdf.parse(condition.getDoubtDateTo()));
			}
			sqlCondition.setCrf(condition.isCrf());
			sqlCondition.setDesc(condition.isDesc());
			sqlCondition.setNo(condition.getNo());
			sqlCondition.setOrderBy(condition.getOrderBy());
			sqlCondition.setDesc(condition.isDesc());
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
			List<DoubtRecord> doubt = doubtRecordMapper.getDoubtSumm(id);
			Map<String, Object> doubtSumm = new HashMap<String, Object>();
			for (int i=0;doubt != null && i < doubt.size(); i++){
				doubtSumm.put(""+doubt.get(i).getMenuId(), doubt.get(i).getUnDeal());
			}
			result.put("doubt", doubtSumm);
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
			}else{
				//insert
				cRFMapper.insertPatientInfo(patientInfoCase);
			}
			updateProcessAndModifyDate(patientInfoVo.getNo(), ProcessUtils.BASIC_INFO);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(patientInfoCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, "保存基本信息失败");
		}
		
		return result;
	}
	
	private void updateProcessAndModifyDate(String no, int phase){
		int progress = cRFMapper.getProgress(no);
		if (progress < phase){
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("progress", phase);
			condition.put("no", no);
			cRFMapper.updateProgress(condition);
		}
		CRFUserSign userSign = new CRFUserSign();
		userSign.setNo(no);
		userSignMapper.updateUserSignDate(userSign);
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

	public void validDateRange(Date date, int id, String fieldName) throws Exception{
		PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
		Date startDate = patientInfoCase.getRyrq();
		Date endDate = patientInfoCase.getCyrq();
		if (date == null)
			throw new Exception(fieldName+"不能为空");
		if (date.getTime()<startDate.getTime() || date.getTime() > endDate.getTime())
			throw new Exception(fieldName+"不在入院日期和出院日期之间");
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
			updateProcessAndModifyDate(personalHistoryVo.getNo(), ProcessUtils.PERSON_HISTORY);
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
			updateProcessAndModifyDate(pastHistoryVo.getNo(), ProcessUtils.PASS_HISTORY);
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
					int key = Integer.parseInt(str);
					PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(key);
					String no = patientInfoCase.getNo();
					cRFMapper.deletePatientInfo(key);
					userSignMapper.deleteUserSign(no);
					cRFMapper.deleteADR(no);
					cRFMapper.deleteDiseaseInfo(no);
					cRFMapper.deleteDrugCombinationList(no);
					cRFMapper.deleteDrugUseInfo(no);
					cRFMapper.deleteLabExamCase(no);
					cRFMapper.deletePastHistory(no);
					cRFMapper.deletePersonHistory(no);
					cRFMapper.deleteDrugSummary(no);
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
			updateProcessAndModifyDate(diseaseInfoVo.getNo(), ProcessUtils.DISEASE_INFO);
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
			Date startDate = null, endDate = null;
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (StringUtils.isNotBlank(drugUseCase.getStartDate()))
					startDate = sdf.parse(drugUseCase.getStartDate());
				if (StringUtils.isNotBlank(drugUseCase.getEndDate()))
					endDate = sdf.parse(drugUseCase.getEndDate());
			}catch(Exception e){
				e.printStackTrace();
			}
			validDateRange(startDate, drugUseVo.getId(), "用药开始时间");
			validDateRange(endDate, drugUseVo.getId(), "用药结束时间");
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateDrugUseInfo(drugUseCase);
			else {
				cRFMapper.insertDrugUseInfo(drugUseCase);
			}
			updateProcessAndModifyDate(drugUseVo.getNo(), ProcessUtils.DRUG_USING);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(drugUseVo.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> getDrugUseInfo(String id) {
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
				validDateRange(drugCombinationCase.getStartDate(), drugCombinationVo.getId(), "开始时间");
				validDateRange(drugCombinationCase.getEndDate(), drugCombinationVo.getId(), "结束时间");
				if (drugCombinationCase.getSeq() > 0){
					cRFMapper.updateDrugCombination(drugCombinationCase);
				}else{
					//cRFMapper.deleteDrugCombination(drugCombinationVo.getNo());
					cRFMapper.insertDrugCombination(drugCombinationCase);
				}
			}
			updateProcessAndModifyDate(drugCombinationVo.getNo(), ProcessUtils.COMBINIATION_DRUG);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(drugCombinationVo.getNo());
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
			validDateRange(drugSummaryCase.getStartDate(), drugSummaryVo.getId(), "病程首次用药时间");
			validDateRange(drugSummaryCase.getEndDate(), drugSummaryVo.getId(), "病程末次用药时间");
			DrugSummaryCase dbCase = cRFMapper.getDrugSummary(drugSummaryVo.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateDrugSummary(drugSummaryCase);
			else {
				cRFMapper.insertDrugSummary(drugSummaryCase);
			}
			updateProcessAndModifyDate(drugSummaryVo.getNo(), ProcessUtils.DRUG_SUMMARY);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(drugSummaryVo.getNo());
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
			validDateRange(aDRCase.getAdrDateDate(), adrVo.getId(), "不良反应/事件发生时间");
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updateADR(aDRCase);
			else {
				cRFMapper.insertADR(aDRCase);
			}
			updateProcessAndModifyDate(adrVo.getNo(), ProcessUtils.ADR);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(adrVo.getNo());
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
			DrugUseExamVo drugUseExamVo = null;
			if (inHospitalExamVo  != null)
			drugUseExamVo = convertorService.convertInHospitalExamToOtherLabExam(inHospitalExamVo);
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
			DrugUseExamVo drugUseExamVo = null;
			if (inHospitalExamVo  != null)
				drugUseExamVo = convertorService.convertInHospitalExamToOtherLabExam(inHospitalExamVo);
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
			DrugSummaryVo drugSummaryVo = null;
			if (drugSummaryCase != null)
				drugSummaryVo = convertorService.convertDrugSummaryFromModelToView(drugSummaryCase);
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
			validDateRange(labExamCase.getExamDate(), inHospitalExamVo.getId(), "送检日期");
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
			ADRVo adrVo = null;
			if (adrCase != null)
				adrVo = convertorService.convertADRFromModelToView(adrCase);
			else{
				adrVo = new ADRVo();
				PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
				PatientInfoVo patientInfoVo = convertorService.convertPatientFromModelToView(patientInfoCase);
				adrVo.setBirthday(patientInfoVo.getBirthday());
				adrVo.setSex(patientInfoVo.getSex());
				adrVo.setEthic(patientInfoVo.getEthic());
				adrVo.setWeight(patientInfoVo.getWeight());
			}
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
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(doubtRecordSubmitVo.getId());
			doubtRecord.setNo(patientInfoCase.getNo());
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
			List<DoubtRecordGetVo> doubtRecordVos = new ArrayList<DoubtRecordGetVo>();
			if (doubtRecords != null){
				for (DoubtRecord record : doubtRecords){
					DoubtRecordGetVo doubtRecordVo = convertorService.convertDoubtRecordFromModelToView(record);
					doubtRecordVos.add(doubtRecordVo);
				}
			}
			
			result = AjaxReturnUtils.generateAjaxReturn(true, null, doubtRecordVos);
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
			doubtRecord.setDoubtId(doubtRecordSubmitVo.getDoubtId());
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

	public Map<String, Object> getStaticDict(String keyword, String type) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			StaticDict staticDict = new StaticDict();
			staticDict.setTypeabbr(type);
			staticDict.setName(keyword);
			List<StaticDict> dicts = dictMapper.getStaticDict(staticDict);
			List<StaticDictVo> dictVos = new ArrayList<StaticDictVo>();
			if (dicts != null){
				for (StaticDict dict : dicts){
					StaticDictVo staticDictVo = new StaticDictVo();
					staticDictVo.setId(dict.getId());
					staticDictVo.setName(dict.getName());
					dictVos.add(staticDictVo);
				}
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null, dictVos);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		return result;
	}

	public Map<String, Object> addADR(String no) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfoIdByNo(no);
			if (patientInfoCase != null){
				result = AjaxReturnUtils.generateAjaxReturn(true, null);
				result.put("id", patientInfoCase.getId());
			}else{
				result = AjaxReturnUtils.generateAjaxReturn(false, "观察表编号不存在");
			}
				
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}



	



	
	
	


	
}
