package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;

public interface CRFMapper {
	
	public void insertPatientInfo(PatientInfoCase patientInfo);
	
	public void updatePatientInfo(PatientInfoCase patientInfo);
	
	public void insertPersonAndAllergicHistory();

	public String getFormEnumValue(Map<String, Object> condition);

	public int getFormEnumValueByName(Map<String, Object> condition);

	public int getNextSeq(int id);

	public int insertCRF(Map<String, Object> condition);

	public int getDoubtCRFNum(String userName);

	public int getToDoNum(String userName);

	public List<ListReturn> getPatientList(ListCondition condition);

	public List<ListReturn> getDoutSummaryList(ListCondition sqlCondition); 
}
