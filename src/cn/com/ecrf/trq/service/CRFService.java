package cn.com.ecrf.trq.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.CRFUserSign;
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
import cn.com.ecrf.trq.repository.UserSignMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.LockStatusUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.CheckBoxVo;
import cn.com.ecrf.trq.vo.PastHistoryVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.PersonalHistoryVo;
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
			if (type == 0 || type == 1 || type == 3){//草稿,提交，审核通过
				list = cRFMapper.getPatientList(sqlCondition);
			}else if (type == 2){//质疑
				list = cRFMapper.getDoutSummaryList(sqlCondition);
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
		if (condition.getType() == 1)
			lockStatus = 20;
		if (condition.getType() == 2)
			lockStatus = 30;
		if (condition.getType() == 3)
			lockStatus = 40;
		if (condition.getType() == 4)
			lockStatus = 50;
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
			}else{
				//insert
				cRFMapper.insertPatientInfo(patientInfoCase);
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
				cRFMapper.updateProgress(20);
			}
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
			PastHistoryVo pastHistoryVo = convertorService.convertPastHistoryFromViewToModel(pastHistoryCase);
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
			PastHistoryCase pastHistoryCase =  convertorService.convertPastHistoryFromModelToVo(pastHistoryVo);
			PastHistoryCase dbCase = cRFMapper.getPastHistory(pastHistoryCase.getId());
			if (dbCase != null && dbCase.getNo() != null)
				cRFMapper.updatePastHistory(pastHistoryCase);
			else {
				cRFMapper.insertPastHistory(pastHistoryCase);
				cRFMapper.updateProgress(30);
			}
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


	
}
