package cn.com.ecrf.trq.vo.data;

import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;

public class ExportVo {
	private PatientInfoCase patientInfoCase;
	private PastHistoryCase pastHistoryCase;
	private PersonAllergicHistoryCase personAllergicHistoryCase;
	
	public PatientInfoCase getPatientInfoCase() {
		return patientInfoCase;
	}
	public void setPatientInfoCase(PatientInfoCase patientInfoCase) {
		this.patientInfoCase = patientInfoCase;
	}
	public PastHistoryCase getPastHistoryCase() {
		return pastHistoryCase;
	}
	public void setPastHistoryCase(PastHistoryCase pastHistoryCase) {
		this.pastHistoryCase = pastHistoryCase;
	}
	public PersonAllergicHistoryCase getPersonAllergicHistoryCase() {
		return personAllergicHistoryCase;
	}
	public void setPersonAllergicHistoryCase(
			PersonAllergicHistoryCase personAllergicHistoryCase) {
		this.personAllergicHistoryCase = personAllergicHistoryCase;
	}
	
	
}
