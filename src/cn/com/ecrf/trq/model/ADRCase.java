package cn.com.ecrf.trq.model;

import java.util.Date;
import java.util.List;

import cn.com.ecrf.trq.vo.lab.PlainExamVo;

public class ADRCase {
	private int id;
	//编号
	private String no;
	private String typeStr;
	private String bloodStr;
	private String name;
	private String sexStr;
	private Date birthdayDate;
	private String ethicStr;
	private String weight;
	private String contact;
	private String disease;
	private String patientNo;
	private String historyadrStr;
	private String historyadrtxt;
	private String familyadrStr;
	private String familyadrtxt;
	private String relationshipStr;
	private String info;
	private String info6txt;
	private String info7txt;
	private String drug1Str;
	private String drug2Str;
	private String adr;
	private String adr1;
	private String adr2;
	private String adr3;
	private String adr4;
	private String adr5;
	private String adr6;
	private String adr7;
	private String adr8;
	private String adr9;
	private String adr10;
	private String adrtxt;
	private Date adrDateDate;
	private String adrH;
	private String adrM;
	private String adrDescription;
	//不良反应/事件处理情况
	private String adrDealStr;
	private String adrDeal3Str;
	private String adrDeal3txt;
	private String adrDealDose;
	private String adrDeal4txt;
	//备注
	private String adrDealRemark;
	//不良反应/事件的结果
	private String endingStr;
	private String endingtxt;
	private Date deathDateDate;
	private String deathReason;
	private String adrStopStr;
	private String adrRestartStr;
	private String evaluateStr;
	private String careerStr;
	private String careertxt;
	private String email;
	private Date reportDateDate;
	private String remark;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	public String getBloodStr() {
		return bloodStr;
	}
	public void setBloodStr(String bloodStr) {
		this.bloodStr = bloodStr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSexStr() {
		return sexStr;
	}
	public void setSexStr(String sexStr) {
		this.sexStr = sexStr;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getEthicStr() {
		return ethicStr;
	}
	public void setEthicStr(String ethicStr) {
		this.ethicStr = ethicStr;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getHistoryadrStr() {
		return historyadrStr;
	}
	public void setHistoryadrStr(String historyadrStr) {
		this.historyadrStr = historyadrStr;
	}
	public String getHistoryadrtxt() {
		return historyadrtxt;
	}
	public void setHistoryadrtxt(String historyadrtxt) {
		this.historyadrtxt = historyadrtxt;
	}
	public String getFamilyadrStr() {
		return familyadrStr;
	}
	public void setFamilyadrStr(String familyadrStr) {
		this.familyadrStr = familyadrStr;
	}
	public String getFamilyadrtxt() {
		return familyadrtxt;
	}
	public void setFamilyadrtxt(String familyadrtxt) {
		this.familyadrtxt = familyadrtxt;
	}
	public String getRelationshipStr() {
		return relationshipStr;
	}
	public void setRelationshipStr(String relationshipStr) {
		this.relationshipStr = relationshipStr;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getInfo6txt() {
		return info6txt;
	}
	public void setInfo6txt(String info6txt) {
		this.info6txt = info6txt;
	}
	public String getInfo7txt() {
		return info7txt;
	}
	public void setInfo7txt(String info7txt) {
		this.info7txt = info7txt;
	}
	public String getDrug1Str() {
		return drug1Str;
	}
	public void setDrug1Str(String drug1Str) {
		this.drug1Str = drug1Str;
	}
	public String getDrug2Str() {
		return drug2Str;
	}
	public void setDrug2Str(String drug2Str) {
		this.drug2Str = drug2Str;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getAdr1() {
		return adr1;
	}
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}
	public String getAdr2() {
		return adr2;
	}
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}
	public String getAdr3() {
		return adr3;
	}
	public void setAdr3(String adr3) {
		this.adr3 = adr3;
	}
	public String getAdr4() {
		return adr4;
	}
	public void setAdr4(String adr4) {
		this.adr4 = adr4;
	}
	public String getAdr5() {
		return adr5;
	}
	public void setAdr5(String adr5) {
		this.adr5 = adr5;
	}
	public String getAdr6() {
		return adr6;
	}
	public void setAdr6(String adr6) {
		this.adr6 = adr6;
	}
	public String getAdr7() {
		return adr7;
	}
	public void setAdr7(String adr7) {
		this.adr7 = adr7;
	}
	public String getAdr8() {
		return adr8;
	}
	public void setAdr8(String adr8) {
		this.adr8 = adr8;
	}
	public String getAdr9() {
		return adr9;
	}
	public void setAdr9(String adr9) {
		this.adr9 = adr9;
	}
	public String getAdr10() {
		return adr10;
	}
	public void setAdr10(String adr10) {
		this.adr10 = adr10;
	}
	public String getAdrtxt() {
		return adrtxt;
	}
	public void setAdrtxt(String adrtxt) {
		this.adrtxt = adrtxt;
	}
	public Date getAdrDateDate() {
		return adrDateDate;
	}
	public void setAdrDateDate(Date adrDateDate) {
		this.adrDateDate = adrDateDate;
	}
	public String getAdrH() {
		return adrH;
	}
	public void setAdrH(String adrH) {
		this.adrH = adrH;
	}
	public String getAdrM() {
		return adrM;
	}
	public void setAdrM(String adrM) {
		this.adrM = adrM;
	}
	public String getAdrDescription() {
		return adrDescription;
	}
	public void setAdrDescription(String adrDescription) {
		this.adrDescription = adrDescription;
	}
	public String getAdrDealStr() {
		return adrDealStr;
	}
	public void setAdrDealStr(String adrDealStr) {
		this.adrDealStr = adrDealStr;
	}
	

	public String getAdrDeal3Str() {
		return adrDeal3Str;
	}
	public void setAdrDeal3Str(String adrDeal3Str) {
		this.adrDeal3Str = adrDeal3Str;
	}
	
	public String getAdrDealDose() {
		return adrDealDose;
	}
	public void setAdrDealDose(String adrDealDose) {
		this.adrDealDose = adrDealDose;
	}
	public String getAdrDeal3txt() {
		return adrDeal3txt;
	}
	public void setAdrDeal3txt(String adrDeal3txt) {
		this.adrDeal3txt = adrDeal3txt;
	}
	public String getAdrDeal4txt() {
		return adrDeal4txt;
	}
	public void setAdrDeal4txt(String adrDeal4txt) {
		this.adrDeal4txt = adrDeal4txt;
	}
	public String getAdrDealRemark() {
		return adrDealRemark;
	}
	public void setAdrDealRemark(String adrDealRemark) {
		this.adrDealRemark = adrDealRemark;
	}
	public String getEndingStr() {
		return endingStr;
	}
	public void setEndingStr(String endingStr) {
		this.endingStr = endingStr;
	}
	public String getEndingtxt() {
		return endingtxt;
	}
	public void setEndingtxt(String endingtxt) {
		this.endingtxt = endingtxt;
	}
	public Date getDeathDateDate() {
		return deathDateDate;
	}
	public void setDeathDateDate(Date deathDateDate) {
		this.deathDateDate = deathDateDate;
	}
	public String getDeathReason() {
		return deathReason;
	}
	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}
	public String getAdrStopStr() {
		return adrStopStr;
	}
	public void setAdrStopStr(String adrStopStr) {
		this.adrStopStr = adrStopStr;
	}
	public String getAdrRestartStr() {
		return adrRestartStr;
	}
	public void setAdrRestartStr(String adrRestartStr) {
		this.adrRestartStr = adrRestartStr;
	}
	public String getEvaluateStr() {
		return evaluateStr;
	}
	public void setEvaluateStr(String evaluateStr) {
		this.evaluateStr = evaluateStr;
	}
	public String getCareerStr() {
		return careerStr;
	}
	public void setCareerStr(String careerStr) {
		this.careerStr = careerStr;
	}
	public String getCareertxt() {
		return careertxt;
	}
	public void setCareertxt(String careertxt) {
		this.careertxt = careertxt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getReportDateDate() {
		return reportDateDate;
	}
	public void setReportDateDate(Date reportDateDate) {
		this.reportDateDate = reportDateDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
