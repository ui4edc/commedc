package cn.com.ecrf.trq.repository;

import java.util.Map;

import cn.com.ecrf.trq.model.PatientInfoCase;

public interface CRFMapper {
	
	public void insertPatientInfo(PatientInfoCase patientInfo);
	
	public void updatePatientInfo(PatientInfoCase patientInfo);
	
	public void insertPersonAndAllergicHistory();

	public String getFormEnumValue(Map<String, Object> condition);

	public int getFormEnumValueByName(Map<String, Object> condition); 
}
