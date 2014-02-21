package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.ADRCase;
import cn.com.ecrf.trq.model.CRFFormEnum;
import cn.com.ecrf.trq.model.DiseaseInfoCase;
import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.DrugCombinationCase;
import cn.com.ecrf.trq.model.DrugSummaryCase;
import cn.com.ecrf.trq.model.DrugUseCase;
import cn.com.ecrf.trq.model.LabExamCase;
import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.model.list.ListCondition;
import cn.com.ecrf.trq.model.list.ListReturn;

public interface CRFMapper {
	
	public void insertPatientInfo(PatientInfoCase patientInfo);
	
	public void updatePatientInfo(PatientInfoCase patientInfo);

	public CRFFormEnum getFormEnumValue(Map<String, Object> condition);

	public CRFFormEnum getFormEnumValueByName(Map<String, Object> condition);

	public int getNextSeq(int id);

	public void insertCRF(PatientInfoCase patientInfoCase);

	public int getDoubtCRFNum(String userName);

	public int getToDoNum(String userName);

	public List<ListReturn> getPatientList(ListCondition condition);
	
	public int getTotalPatientNum(ListCondition sqlCondition); 

	public List<ListReturn> getDoutSummaryList(ListCondition sqlCondition);

	public int getProgress(String no);

	public ListReturn getCRFSumm(int id);

	public PatientInfoCase getBasicInfo(int id);

	public PersonAllergicHistoryCase getPersonHistory(int id);

	public void insertPersonHistory(
			PersonAllergicHistoryCase personAllergicHistoryCase);

	public void updatePersonHistory(
			PersonAllergicHistoryCase personAllergicHistoryCase);

	public void updateProgress(Map<String,Object> condition);

	public PastHistoryCase getPastHistory(int id);

	public void updatePastHistory(PastHistoryCase pastHistoryCase);

	public void insertPastHistory(PastHistoryCase pastHistoryCase);

	public void updateNextSeq(int id);

	public void delete(int id);

	public DiseaseInfoCase getDiseaseInfo(int id);

	public void updateDiseaseInfo(DiseaseInfoCase diseaseInfoCase);

	public void insertDiseaseInfo(DiseaseInfoCase diseaseInfoCase);

	public DrugUseCase getDrugUseInfo(int id);

	public void updateDrugUseInfo(DrugUseCase drugUseCase);

	public void insertDrugUseInfo(DrugUseCase drugUseCase);

	public void updateDrugCombination(DrugCombinationCase drugCombinationCase);

	public void insertDrugCombination(DrugCombinationCase drugCombinationCase);

	public List<DrugCombinationCase> getDrugCombinationList(int parseInt);

	public LabExamCase getLabExamCase(Map<String, Object> condition);

	public void updateLabExamCase(LabExamCase labExamCase);

	public void insertLabExamCase(LabExamCase labExamCase);

	public DrugSummaryCase getDrugSummary(int id);

	public void updateDrugSummary(DrugSummaryCase drugSummaryCase);

	public void insertDrugSummary(DrugSummaryCase drugSummaryCase);

	public ADRCase getADR(int id);

	public void updateADR(ADRCase aDRCase);

	public void insertADR(ADRCase aDRCase);

	public void deleteDrugCombination(String no);

	public void deletePatientInfo(int id);

	public void deleteADR(int id);

	public void deleteADR(String no);

	public void deleteDiseaseInfo(String no);

	public void deleteDrugCombinationList(String no);

	public void deleteDrugUseInfo(String no);

	public void deleteLabExamCase(String no);

	public void deletePastHistory(String no);

	public void deletePersonHistory(String no);

	public void deleteDrugSummary(String no);

	public PatientInfoCase getBasicInfoIdByNo(String no);

	public List<ListReturn> getPatientListByCRM(ListCondition sqlCondition);

	public List<ListReturn> getDoutSummaryListByCRM(ListCondition sqlCondition);
	


}
