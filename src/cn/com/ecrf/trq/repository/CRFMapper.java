package cn.com.ecrf.trq.repository;

import cn.com.ecrf.trq.model.PatientInfoCase;

public interface CRFMapper {
	
	public void insertPatientInfo(PatientInfoCase patientInfo);
	
	public void updatePatientInfo(PatientInfoCase patientInfo);
	
	public void insertPersonAndAllergicHistory(); 
}
