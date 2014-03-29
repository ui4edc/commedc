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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import cn.com.ecrf.trq.model.ADRCase;
import cn.com.ecrf.trq.model.ADRDrug;
import cn.com.ecrf.trq.model.BodyExamCase;
import cn.com.ecrf.trq.model.CRFUserSign;
import cn.com.ecrf.trq.model.DiseaseInfoCase;
import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.DrugCombinationBase;
import cn.com.ecrf.trq.model.DrugCombinationCase;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.DrugUseCase;
import cn.com.ecrf.trq.model.ECGExamCase;
import cn.com.ecrf.trq.model.FieldDict;
import cn.com.ecrf.trq.model.LabExamCase;
import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.PhaseSignPage;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.SystemConfiguration;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.StaticDict;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.repository.ConfigurationMapper;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.repository.DoubtRecordMapper;
import cn.com.ecrf.trq.repository.RoleMapper;
import cn.com.ecrf.trq.repository.UserMapper;
import cn.com.ecrf.trq.repository.UserSignMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.DictUtils;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.LockStatusUtils;
import cn.com.ecrf.trq.utils.PinyinUtils;
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
import cn.com.ecrf.trq.vo.lab.BodyExamVo;
import cn.com.ecrf.trq.vo.lab.DrugUseExamVo;
import cn.com.ecrf.trq.vo.lab.ECGExamVo;
import cn.com.ecrf.trq.vo.lab.InHospitalExamVo;
import cn.com.ecrf.trq.vo.lab.LabExamInstanceVo;
import cn.com.ecrf.trq.vo.lab.PlainExamVo;
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
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ConfigurationMapper configurationMapper;
	
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
					list = cRFMapper.getDoutSummaryListByCRM(sqlCondition);
					total = cRFMapper.getTotalPatientNumByCRM(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getDoutSummaryList(sqlCondition);
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
					list = cRFMapper.getDoutSummaryList(sqlCondition);
					total = cRFMapper.getTotalPatientNum(sqlCondition);
				}else if ("DM".equalsIgnoreCase(roleName)){
					list = cRFMapper.getDoutSummaryList(sqlCondition);
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
				resultVo.setDoubtNumber(result.getDoubtNumber());
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
		SimpleDateFormat sdfT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (StringUtils.isNotBlank(condition.getCreateDateFrom()) && StringUtils.isNotBlank(condition.getCreateDateTo())){		
				sqlCondition.setCreateDateFrom(sdfT.parse(condition.getCreateDateFrom()+" 00:00:00"));
				sqlCondition.setCreateDateTo(sdfT.parse(condition.getCreateDateTo()+" 23:59:59"));
			}
			if (StringUtils.isNotBlank(condition.getLastModifiedFrom()) && StringUtils.isNotBlank(condition.getLastModifiedTo())){
				sqlCondition.setLastModifiedFrom(sdfT.parse(condition.getLastModifiedFrom()+" 00:00:00"));
				sqlCondition.setLastModifiedTo(sdfT.parse(condition.getLastModifiedTo()+" 23:59:59"));
			}
			if (StringUtils.isNotBlank(condition.getDoubtDateFrom()) && StringUtils.isNotBlank(condition.getDoubtDateTo())){
				sqlCondition.setDoubtDateFrom(sdfT.parse(condition.getDoubtDateFrom()+" 00:00:00"));
				sqlCondition.setDoubtDateTo(sdfT.parse(condition.getDoubtDateTo()+" 23:59:59"));
			}
			sqlCondition.setCrf(condition.isCrf());
			sqlCondition.setDesc(condition.isDesc());
			sqlCondition.setNo(condition.getNo());
			sqlCondition.setOrderBy(condition.getOrderBy());
			sqlCondition.setDesc(condition.isDesc());
			sqlCondition.setUndealed(condition.isUndealed());
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

	public Map<String, Object> genCRFNo(String abbr, String no) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser = SecurityUtils.getSubject();  
			String userName = (String)currentUser.getPrincipal();
			User user = userService.findUserByLoginName(userName);
			/*Organization organization = userService.getOrganization(user.getOrganizationId());
			int  seq = cRFMapper.getNextSeq(organization.getId());
			DecimalFormat df = new DecimalFormat("0000");
			String subNo = df.format(seq);
			no = organization.getCode() + "-" + subNo;*/
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
			//cRFMapper.updateNextSeq(organization.getId());
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("no", no);
			result.put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, "观察表编号已存在");
			/*if (e instanceof DuplicateKeyException){
				result = AjaxReturnUtils.generateAjaxReturn(false, "观察表编号已存在");
			}else{
				result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
			}*/
			
			
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
		PatientInfoVo patientInfoVo = null;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(Integer.parseInt(id));
			if (patientInfoCase.getBirthday() == null) {//means just create the patient, no other information
							
			}else{
				patientInfoVo = convertorService.convertPatientFromModelToView(patientInfoCase);
			}
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
			PersonalHistoryVo personalHistoryVo = null;
			if (personAllergicHistoryCase != null){
				personalHistoryVo = new PersonalHistoryVo();
				personalHistoryVo = convertorService.convertPersonHistoryFromModelToView(personAllergicHistoryCase);
			}
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
	
	private void validCostIsNull(String drugCost, int id, String fieldName) throws Exception{
		// TODO Auto-generated method stub
		SystemConfiguration configuration = configurationMapper.getConfiguration("crf.cost.required.hospital");
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		User user = userMapper.findUserByLoginName(userName);
		boolean isRequired = false;
		if (configuration != null && StringUtils.isNotBlank(configuration.getValue1())){
			String[] hospitalIds = configuration.getValue1().split(",");
			for (String idStr : hospitalIds){
				if (idStr.trim() != null && idStr.trim().length() > 0){
					if (Integer.parseInt(idStr) == user.getOrganizationId()){
						isRequired = true;
						break;
					}
				}
			}
			if (isRequired && !StringUtils.isNotBlank(drugCost))
				throw new Exception(fieldName+" 不能为空");
		}
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
					cRFMapper.deleteDrugUseInfoByNo(no);
					cRFMapper.deleteLabExamCase(no);
					cRFMapper.deletePastHistory(no);
					cRFMapper.deletePersonHistory(no);
					cRFMapper.deleteDrugSummary(no);
					doubtRecordMapper.deleteDoubtRecord(no);
				}
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}
	//批量提交
	public Map<String, Object> batchCommit(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			List<Role> roles = roleMapper.getRoleByUserName(userName);
			String roleName = null;
			if (roles != null && roles.size() > 0){
				roleName = roles.get(0).getRoleName();
			}
			if (StringUtils.isNotBlank(id)){
				String[] idArray = id.split(",");
				for (String str : idArray){
					int key = Integer.parseInt(str);
					PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(key);
					CRFUserSign dbUserSign = userSignMapper.getUserSignByNo(patientInfoCase.getNo());
					if ("CRM".equalsIgnoreCase(roleName)){
						dbUserSign.setLockStatus(LockStatusUtils.pass);
						dbUserSign.setCrmSignTime(new Date());
						dbUserSign.setCrmName(userName);
						userSignMapper.updateUserSign(dbUserSign);
					}else if ("DM".equalsIgnoreCase(roleName)){
						dbUserSign.setLockStatus(LockStatusUtils.pass);
						dbUserSign.setDmSignTime(new Date());
						dbUserSign.setDmName(userName);
						userSignMapper.updateUserSign(dbUserSign);
					}
					
				}
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> saveDiseaseInfo(DiseaseInfoVo diseaseInfoVo) {
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
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", drugUseVo.getId());
			condition.put("drugUseId", drugUseVo.getDrugUseId());
			List<DrugUseCase> drugUseInfoCases = cRFMapper.getDrugUseInfo(condition);
			DrugUseCase dbCase = null;
			if (drugUseInfoCases != null && drugUseInfoCases.size() > 0)
				dbCase = drugUseInfoCases.get(0);
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
			if (dbCase != null && dbCase.getNo() != null && dbCase.getDrugUseId() > 0){
				cRFMapper.updateDrugUseInfo(drugUseCase);
				cRFMapper.updateAllDrugUseInfo(drugUseCase);
			}else {
				cRFMapper.insertDrugUseInfoWithDrugUseId(drugUseCase);
				cRFMapper.updateAllDrugUseInfo(drugUseCase);
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

	public Map<String, Object> getDrugUseInfo(int id, int drugUseId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("drugUseId", drugUseId);
			List<DrugUseCase> drugUseInfoCases = cRFMapper.getDrugUseInfo(condition);
			DrugUseVo drugUseVo = null;
			if (drugUseInfoCases != null && drugUseInfoCases.size() > 0){
				drugUseVo = convertorService.convertDrugUseInfoFromModelToView(drugUseInfoCases.get(0));
			}
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
			DrugCombinationBase drugCombinationBase = new DrugCombinationBase();
			drugCombinationBase.setHasDrug(drugCombinationVo.getHasDrug());	
			drugCombinationBase.setId(drugCombinationVo.getId());
			drugCombinationBase.setNo(drugCombinationVo.getNo());
			DrugCombinationBase dbBase = cRFMapper.getDrugCombinationBase(drugCombinationVo.getNo());
			if (dbBase != null){
				cRFMapper.updateDrugCombinationBase(drugCombinationBase);
			}else{
				cRFMapper.insertDrugCombinationBase(drugCombinationBase);
			}
			for (int i=0;drugs != null && i<drugs.size();i++){
				DrugCombinationCase drugCombinationCase =  convertorService.convertDrugCombinationCaseFromViewToModel(drugs.get(i), drugCombinationVo.getNo());
				//validDateRange(drugCombinationCase.getStartDate(), drugCombinationVo.getId(), "开始时间");
				//validDateRange(drugCombinationCase.getEndDate(), drugCombinationVo.getId(), "结束时间");
				drugCombinationCase.setDrugId(drugCombinationCase.getId());
				if (drugCombinationCase.getDrugId() > 0){
					cRFMapper.updateDrugCombination(drugCombinationCase);
				}else{
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
	
	public Map<String, Object> saveOneDrugCombination(
			DrugInstanceObject drugInstanceObject) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(drugInstanceObject.getId());
			DrugCombinationCase drugCombinationCase =  convertorService.convertDrugCombinationCaseFromViewToModel(drugInstanceObject, patientInfoCase.getNo());
			validDateRange(drugCombinationCase.getStartDate(), drugInstanceObject.getId(), "开始时间");
			validDateRange(drugCombinationCase.getEndDate(), drugInstanceObject.getId(), "结束时间"); 
			
			if (drugCombinationCase.getDrugId() > 0){
				cRFMapper.updateDrugCombination(drugCombinationCase);
			}else{
				cRFMapper.insertDrugCombination(drugCombinationCase);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("id", drugCombinationCase.getDrugId());
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
			validDateRange(drugSummaryCase.getStartDateDate(), drugSummaryCase.getId(), "病程首次用药时间");
			validDateRange(drugSummaryCase.getEndDateDate(), drugSummaryCase.getId(), "病程末次用药时间");
			validCostIsNull(drugSummaryCase.getDrugCost(), drugSummaryCase.getId(), "药品总费用");
			validCostIsNull(drugSummaryCase.getTreatmentCost(), drugSummaryCase.getId(), "治疗总费用");
			validCostIsNull(drugSummaryCase.getTrqCost(), drugSummaryCase.getId(), "痰热清注射液费用");
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
			validDateRange(aDRCase.getReportDateDate(), adrVo.getId(), "报告日期");
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
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			List<Role> roles = roleMapper.getRoleByUserName(userName);
			String roleName = null;
			if (roles != null && roles.size() > 0){
				roleName = roles.get(0).getRoleName();
			}
			if ("CRM".equalsIgnoreCase(roleName)){
				userSign.setLockStatus(LockStatusUtils.pass);
				userSign.setCrmSignTime(new Date());
				userSign.setCrmName(userName);
			}else if ("DM".equalsIgnoreCase(roleName)){
				userSign.setLockStatus(LockStatusUtils.pass);
				userSign.setDmSignTime(new Date());
				userSign.setDmName(userName);
			}else{
				CRFUserSign dbUserSign = userSignMapper.getUserSignByNo(patientInfoCase.getNo());
				if (dbUserSign.getLockStatus() == 0)
					dbUserSign.setLockStatus(LockStatusUtils.submit);
				userSign.setLockStatus(dbUserSign.getLockStatus());
				userSign.setCroSignTime(new Date());
				userSign.setCroName(userName);
			}
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
		DrugCombinationVo drugCombinationVo = null;
		try{
			PatientInfoCase patientInfo = cRFMapper.getBasicInfo(Integer.parseInt(id));
			DrugCombinationBase drugCombinationBase = cRFMapper.getDrugCombinationBase(patientInfo.getNo());
			if (drugCombinationBase != null){
				drugCombinationVo = new DrugCombinationVo();
				drugCombinationVo.setHasDrug(drugCombinationBase.getHasDrug());
				List<DrugCombinationCase> drugCombinationCases = cRFMapper.getDrugCombinationList(Integer.parseInt(id));
				
				drugCombinationVo.setId(Integer.parseInt(id));
				drugCombinationVo.setNo(no);
				List<DrugInstanceObject> drugInstanceObjects = new ArrayList<DrugInstanceObject>();
				for (int i=0;drugCombinationCases!=null && i<drugCombinationCases.size();i++){
					DrugInstanceObject drugInstanceObject = convertorService.convertDrugCombinationCaseFromModelToView(drugCombinationCases.get(i));
					drugInstanceObjects.add(drugInstanceObject);
				}
				drugCombinationVo.setDrug(drugInstanceObjects);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugCombinationVo);
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
				//DrugSummaryCase drugSummaryCase = cRFMapper.getDrugSummary(id);
				PatientInfoVo patientInfoVo = convertorService.convertPatientFromModelToView(patientInfoCase);
				adrVo.setBirthday(patientInfoVo.getBirthday());
				adrVo.setSex(patientInfoVo.getSex());
				adrVo.setEthic(patientInfoVo.getEthic());
				adrVo.setWeight(patientInfoVo.getWeight());
				/*if (drugSummaryCase.getHasAdr() == 1)
					convertorService.convertFromDrugSummaryToAdr(drugSummaryCase, adrVo);*/
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
			//doubtRecord.setDoubtDate(new Date());
			Subject subject = SecurityUtils.getSubject();
			String userName = (String) subject.getPrincipal();
			doubtRecord.setDoubter(userName);
			doubtRecord.setFieldId(doubtRecordSubmitVo.getFieldId());
			doubtRecord.setId(doubtRecordSubmitVo.getId());
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(doubtRecordSubmitVo.getId());
			doubtRecord.setNo(patientInfoCase.getNo());
			doubtRecord.setMenuId(doubtRecordSubmitVo.getMenu());
			doubtRecordMapper.insertDoubtRecord(doubtRecord);
			List<DoubtRecord> records = doubtRecordMapper.getUndealDoubtRecord(doubtRecordSubmitVo.getId());
			if (records != null && records.size() > 0){
				CRFUserSign userSign = new CRFUserSign();
				userSign.setNo(patientInfoCase.getNo());
				userSign.setLockStatus(LockStatusUtils.doubt);
				userSignMapper.updateUserSign(userSign);
			}
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
			//doubtRecord.setCommitDate(new Date());
			doubtRecord.setFlag(1);
			doubtRecordMapper.saveDoubtRecord(doubtRecord);
			List<DoubtRecord> records = doubtRecordMapper.getUndealDoubtRecord(doubtRecordSubmitVo.getId());
			if (records == null || records.size() == 0){
				CRFUserSign userSign = new CRFUserSign();
				PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(doubtRecordSubmitVo.getId());
				userSign.setNo(patientInfoCase.getNo());
				userSign.setLockStatus(LockStatusUtils.pass);
				Subject subject = SecurityUtils.getSubject();
				String userName = (String) subject.getPrincipal();
				List<Role> roles = roleMapper.getRoleByUserName(userName);
				String roleName = null;
				if (roles != null && roles.size() > 0){
					roleName = roles.get(0).getRoleName();
				}
				if ("CRO".equalsIgnoreCase(roleName) || "LCRO".equalsIgnoreCase(roleName)){

				}else if ("CRM".equalsIgnoreCase(roleName)){
					userSign.setCrmName(userName);
					userSign.setCrmSignTime(new Date());
					userSignMapper.updateUserSign(userSign);
				}else if ("DM".equalsIgnoreCase(roleName)){
					userSign.setDmName(userName);
					userSign.setDmSignTime(new Date());
					userSignMapper.updateUserSign(userSign);
				}
				
			}
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
			if (StringUtils.isNotBlank(keyword)){
				staticDict.setAbbr(PinyinUtils.getFirstHanyuPinyin(keyword)+"%");
			}
			List<StaticDictVo> dictVos = new ArrayList<StaticDictVo>();
			if ("way".equalsIgnoreCase(type)){
				List<StaticDict> dicts = dictMapper.getStaticDict(staticDict);
				if (dicts != null){
					for (StaticDict dict : dicts){
						StaticDictVo staticDictVo = new StaticDictVo();
						staticDictVo.setId(dict.getId());
						staticDictVo.setName(dict.getName());
						dictVos.add(staticDictVo);
					}
				}
			}else if ("drug".equalsIgnoreCase(type)){
				DictRow dictRow = new DictRow();
				dictRow.setName(keyword+"%");
				dictRow.setType(DictUtils.drug);
				dictRow.setTypeAbbr(PinyinUtils.getFirstHanyuPinyin(DictUtils.drug));
				List<DictRow> dicts = dictMapper.getDictRow(dictRow);
				if (dicts != null){
					for (DictRow dict : dicts){
						StaticDictVo staticDictVo = new StaticDictVo();
						staticDictVo.setId(dict.getId());
						staticDictVo.setName(dict.getName());
						dictVos.add(staticDictVo);
					}
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

	public Map<String, Object> getDrugUseNum(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			List<DrugUseCase> drugUseCases = cRFMapper.getDrugUseInfo(condition);
			List<Integer> drugUseLists = new ArrayList<Integer>();
			Map<String, Object> data = new HashMap<String, Object>();
			for (int i=0;drugUseCases!=null && i<drugUseCases.size();i++){
				drugUseLists.add(drugUseCases.get(i).getDrugUseId());
			}
			data.put("drugUseIdList", drugUseLists);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, data);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> addNewDrugUseInfo(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			DrugUseCase drugUseCase = new DrugUseCase();
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
			drugUseCase.setNo(patientInfoCase.getNo());
			drugUseCase.setId(id);
			cRFMapper.insertDrugUseInfo(drugUseCase);
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("drugUseId", drugUseCase.getDrugUseId());
			cRFMapper.deleteDrugUseInfoByDrugUseId(condition);
			//int drugUseId = cRFMapper.getNextDrugUseId();
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("drugUseId", drugUseCase.getDrugUseId());
			result.put("data", data);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> deleteDrugUseInfo(int id, int drugUseId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("id", id);
			condition.put("drugUseId", drugUseId);
			cRFMapper.deleteDrugUseInfoByDrugUseId(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, null);
		}
		
		return result;
	}

	public Map<String, Object> getBodyExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			BodyExamCase bodyExamCase = cRFMapper.getBodyExam(id);
			BodyExamVo bodyExamVo = null;
			if (bodyExamCase != null)
				bodyExamVo = convertorService.convertBodyExamFromModelToView(bodyExamCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, bodyExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> saveBodyExam(BodyExamVo bodyExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			BodyExamCase bodyExamCase = convertorService.convertBodyExamFromViewToModel(bodyExamVo);
			if (bodyExamCase.getExamDateDate() != null)
				validDateRange(bodyExamCase.getExamDateDate(), bodyExamCase.getId(), "检查日期");
			BodyExamCase dbCase = cRFMapper.getBodyExam(bodyExamCase.getId());
			if (dbCase != null && dbCase.getNo() != null){
				cRFMapper.updateBodyExam(bodyExamCase);
			}else{
				cRFMapper.insertBodyExam(bodyExamCase);
			}
			updateProcessAndModifyDate(bodyExamCase.getNo(), ProcessUtils.BODY_EXAM);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(bodyExamCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> getECGExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			ECGExamCase eCGExamCase = cRFMapper.getECGExam(id);
			ECGExamVo eCGExamVo = null;
			if (eCGExamCase != null)
				eCGExamVo = convertorService.convertECGExamFromModelToView(eCGExamCase);
			result = AjaxReturnUtils.generateAjaxReturn(true, null, eCGExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> saveECGExam(ECGExamVo eCGExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			ECGExamCase eCGExamCase = convertorService.convertECGExamFromViewToModel(eCGExamVo);
			if (eCGExamCase.getExamDateDate() != null)
				validDateRange(eCGExamCase.getExamDateDate(), eCGExamCase.getId(), "检查日期");
			ECGExamCase dbCase = cRFMapper.getECGExam(eCGExamCase.getId());			
			if (dbCase != null && dbCase.getId() > 0){
				cRFMapper.updateECGExam(eCGExamCase);
			}else{
				cRFMapper.insertECGExam(eCGExamCase);
			}
			updateProcessAndModifyDate(eCGExamCase.getNo(), ProcessUtils.ECG_EXAM);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(eCGExamCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> saveDrugUseExam(DrugUseExamVo drugUseExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{	
			LabExamCase labExamCase = convertorService.convertDrugUseExamFromViewToModel(drugUseExamVo);
			validateDateRangeFromLabExam(drugUseExamVo);
			if (labExamCase.getExamDateDate() != null)
				validDateRange(labExamCase.getExamDateDate(), labExamCase.getId(), "送检日期");
			LabExamCase dbCase = cRFMapper.getLabExamCase(labExamCase.getId());
			if (dbCase != null && dbCase.getNo() != null){
				cRFMapper.updateLabExamCase(labExamCase);
			}else{
				cRFMapper.insertLabExamCase(labExamCase);
			}
			updateProcessAndModifyDate(labExamCase.getNo(), ProcessUtils.LAB_EXAM);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			int progress = cRFMapper.getProgress(labExamCase.getNo());
			result.put("progress", progress + "%");
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	private void validateDateRangeFromLabExam(DrugUseExamVo drugUseExamVo) throws Exception {
		// TODO Auto-generated method stub
		List<LabExamInstanceVo> data1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<List<LabExamInstanceVo>> list = new ArrayList<List<LabExamInstanceVo>>();
		list.add(drugUseExamVo.getData1());
		list.add(drugUseExamVo.getData2());
		list.add(drugUseExamVo.getData3());
		list.add(drugUseExamVo.getData4());
		for (int i=0;i<list.size();i++){
			data1 = list.get(i);
			if (data1 != null && data1.size() > 0){
				for (LabExamInstanceVo one : data1){
					try {
						Date examDate = sdf.parse(one.getExamDate());
						validDateRange(examDate, drugUseExamVo.getId(), "检查日期");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Map<String, Object> getDrugUseExam(int id) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			LabExamCase labExamCase = cRFMapper.getLabExamCase(id);
			DrugUseExamVo drugUseExamVo = null;
			if (labExamCase != null){
				drugUseExamVo = convertorService.convertDrugUseExamFromModelToView(labExamCase);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null, drugUseExamVo);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		
		return result;
	}

	public Map<String, Object> deleteOneDrugCombination(int drugId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			cRFMapper.deleteDrugCombination(drugId);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> saveOneDoubtDrugInADE(PlainExamVo plainExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(plainExamVo.getId());
			ADRDrug adrDrug =  convertorService.convertADEDrugFromViewToModel(plainExamVo, patientInfoCase.getNo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			validDateRange(sdf.parse(adrDrug.getF7()), plainExamVo.getId(), "开始时间");
			validDateRange(sdf.parse(adrDrug.getF8()), plainExamVo.getId(), "结束时间"); 
			adrDrug.setDrugType(1);
			if (adrDrug.getDrugId() > 0){
				cRFMapper.updateADRDrug(adrDrug);
			}else{
				cRFMapper.insertADRDrug(adrDrug);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("id", adrDrug.getDrugId());
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	/*public Map<String, Object> deleteOneDrugCombination(int id, String uuid) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			ADRCase adrCase = cRFMapper.getADR(id);
			String drug1 = adrCase.getDrug1Str();
			if (!StringUtils.isNotBlank(uuid)){
				result = AjaxReturnUtils.generateAjaxReturn(false, "uuid为空");
			}else{
				if (StringUtils.isNotBlank(drug1)){
					JSONUtils<PlainExamVo> util = new JSONUtils<PlainExamVo>(PlainExamVo.class);
					List<PlainExamVo> drugList = util.convertFromString(drug1);
					if (drugList != null){
						for (PlainExamVo drug : drugList){
							if (uuid.equalsIgnoreCase(drug.getUuid())){
								
							}
						}
					}
				}
			}
			
			///cRFMapper.deleteOneDoubtDrugInADE(drugId);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}*/

	public Map<String, Object> deleteOneDoubtDrugInADE(int id, int drugId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
			condition.put("no", patientInfoCase.getNo());
			condition.put("drugId", drugId);
			condition.put("drugType", 1); //1-doubt drug, 2-combine drug
			cRFMapper.deleteADRDrug(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> saveOneCombineDrugInADE(PlainExamVo plainExamVo) {
		// TODO Auto-generated method stub
		Map<String, Object> result;
		try{
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(plainExamVo.getId());
			ADRDrug adrDrug =  convertorService.convertADEDrugFromViewToModel(plainExamVo, patientInfoCase.getNo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			validDateRange(sdf.parse(adrDrug.getF7()), plainExamVo.getId(), "开始时间");
			validDateRange(sdf.parse(adrDrug.getF8()), plainExamVo.getId(), "结束时间"); 
			adrDrug.setDrugType(2);
			if (adrDrug.getDrugId() > 0){
				cRFMapper.updateADRDrug(adrDrug);
			}else{
				cRFMapper.insertADRDrug(adrDrug);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("id", adrDrug.getDrugId());
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	public Map<String, Object> deleteOneCombineDrugInADE(int id, int drugId) {
		// TODO Auto-generated method stub
		Map<String, Object> result = null;
		try{
			Map<String, Object> condition = new HashMap<String, Object>();
			PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(id);
			condition.put("no", patientInfoCase.getNo());
			condition.put("drugId", drugId);
			condition.put("drugType", 2); //1-doubt drug, 2-combine drug
			cRFMapper.deleteADRDrug(condition);
			///cRFMapper.deleteOneDoubtDrugInADE(drugId);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}

	



	



	



	
	
	


	
}
