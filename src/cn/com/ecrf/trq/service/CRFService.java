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

import cn.com.ecrf.trq.model.Organization;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PhaseSignPage;
import cn.com.ecrf.trq.model.Role;
import cn.com.ecrf.trq.model.User;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.utils.AjaxReturnUtils;
import cn.com.ecrf.trq.utils.FormEnumObject;
import cn.com.ecrf.trq.utils.FormEnumValue;
import cn.com.ecrf.trq.utils.JSONUtils;
import cn.com.ecrf.trq.utils.StringUtils;
import cn.com.ecrf.trq.vo.CheckBoxVo;
import cn.com.ecrf.trq.vo.PatientInfoVo;
import cn.com.ecrf.trq.vo.list.ListConditionVo;
import cn.com.ecrf.trq.vo.list.ListNotifyVo;
import cn.com.ecrf.trq.vo.list.ListReturnVo;

@Service
public class CRFService {
	@Autowired
	private CRFMapper cRFMapper;
	@Autowired
	private UserService userService;
	
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
		ListCondition sqlCondition = convertCondition(condition);
		int type = condition.getType();
		List<ListReturn> list = null;
		if (type == 1 || type == 2 || type == 4){//草稿
			list = cRFMapper.getPatientList(sqlCondition);
		}else if (type == 3){//质疑
			list = cRFMapper.getDoutSummaryList(sqlCondition);
		}
		List<ListReturnVo> listVo = convertListReturn(list);
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
				if (result.getCreateDate() != null)
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
		if (condition.getType() == 1)
			lockStatus = 10;
		if (condition.getType() == 2)
			lockStatus = 20;
		if (condition.getType() == 3)
			lockStatus = 30;
		if (condition.getType() == 4)
			lockStatus = 40;
		if (condition.getType() == 45)
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlCondition;
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
		Map<String, Object> result;
		try{
			PatientInfoCase patientInfoCase =  convertPatientFromVoToModel(patientInfoVo);
			if (patientInfoVo.getId() > 0){
				//update
				cRFMapper.updatePatientInfo(patientInfoCase);
			}else{
				//insert
				cRFMapper.insertPatientInfo(patientInfoCase);
			}
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
		}catch(Exception e){
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, "保存基本信息失败");
		}
		
		return result;
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
			
		if (StringUtils.isNotBlank(patientInfoVo.getOutDate()))
			patientInfoCase.setCyrq(sdf.parse(patientInfoVo.getOutDate()));
		FormEnumObject ethicObj = new FormEnumObject(patientInfoVo.getEthic(), patientInfoVo.getEthictxt(), FormEnumValue.ETHIC);
		patientInfoCase.setEthic(convertIDToContent(ethicObj));
		patientInfoCase.setHeight(Integer.parseInt(patientInfoVo.getHeight()));
		FormEnumObject hysObj = new FormEnumObject(patientInfoVo.getHys(), null, FormEnumValue.HYS);
		patientInfoCase.setHys(convertIDToContent(hysObj));
		patientInfoCase.setId(patientInfoVo.getId());
		patientInfoCase.setName(patientInfoVo.getName());
		patientInfoCase.setNo(patientInfoVo.getNo());
		if (StringUtils.isNotBlank(patientInfoVo.getInDate()))
			patientInfoCase.setRyrq(sdf.parse(patientInfoVo.getInDate()));
		FormEnumObject sexObj = new FormEnumObject(patientInfoVo.getSex(), null, FormEnumValue.SEX);
		patientInfoCase.setSex(convertIDToContent(sexObj));
		patientInfoCase.setWeight(Float.parseFloat(patientInfoVo.getWeight()));
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
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("no", no);
			condition.put("abbr", abbr);
			int id = cRFMapper.insertCRF(condition);
			result = AjaxReturnUtils.generateAjaxReturn(true, null);
			result.put("no", no);
			result.put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
			result = AjaxReturnUtils.generateAjaxReturn(false, e.getMessage());
		}
		return result;
	}


	
}
