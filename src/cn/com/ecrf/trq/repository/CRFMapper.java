package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;

public interface CRFMapper {
	
	public void insertPatientInfo(PatientInfoCase patientInfo);
	
	public void updatePatientInfo(PatientInfoCase patientInfo);

	public String getFormEnumValue(Map<String, Object> condition);

	public int getFormEnumValueByName(Map<String, Object> condition);

	public int getNextSeq(int id);

	public int insertCRF(Map<String, Object> condition);

	public int getDoubtCRFNum(String userName);

	public int getToDoNum(String userName);

	public List<ListReturn> getPatientList(ListCondition condition);

	public List<ListReturn> getDoutSummaryList(ListCondition sqlCondition);

	public int getProgress(String no);

	public ListReturn getCRFSumm(int id);

	public PatientInfoCase getBasicInfo(int id);

	public PersonAllergicHistoryCase getPersonHistory(int id);

	public void insertPersonHistory(
			PersonAllergicHistoryCase personAllergicHistoryCase);

	public void updatePersonHistory(
			PersonAllergicHistoryCase personAllergicHistoryCase);

	public void updateProgress(int progress);

	public PastHistoryCase getPastHistory(int id);

	public void updatePastHistory(PastHistoryCase pastHistoryCase);

	public void insertPastHistory(PastHistoryCase pastHistoryCase); 
}
