package cn.com.ecrf.trq.vo;

import java.util.List;

import cn.com.ecrf.trq.vo.lab.PlainExamVo;

/**
 * ADR Vo
 * @author user
 *
 */
public class ADRVo {
	/**
	 * {"id":5,"no":"001-0001","type":1,"blood":1,"name":"","sex":1,"birthday":"2014-01-01",
	 * "ethic":1,"weight":"","contact":"","disease":"","patientNo":"","historyadr":1,
	 * "historyadrtxt":"","familyadr":1,"familyadrtxt":"","relationship":1,"info":"",
	 * "info6txt":"","info7txt":"","drug1":[{"f1":"","f2":"","f3":"","f4":"","f5":"","f6":"","f7":"","f8":""}],
	 * "drug2":[{"f1":"","f2":"","f3":"","f4":"","f5":"","f6":"","f7":"","f8":""}],
	 * "adr":"","adr1":"","adr2":"","adr3":"","adr4":"","adr5":"","adr6":"","adr7":"","adr8":"","adr9":"",
	 * "adr10":"","adrtxt":"","adrDate":"2014-01-01","adrH":"","adrM":"","adrDescription":"",
	 * "adrDeal":1,"adrDeal3":1,"adrDealDose":"","adrDeal3txt":"","adrDeal4txt":"","adrDealRemark":"",
	 * "ending":1,"endingtxt":"","deathDate":"2014-01-01","deathReason":"","adrStop":1,"adrRestart":1,
	 * "evaluate":1,"career":1,"careertxt":"","email":"","reportDate":"2014-01-01","remark":""}
	 */
	private int id;
	//编号
	private String no;
	private int type;
	private int blood;
	private String name;
	private int sex;
	private String birthday;
	private int ethic;
	private String weight;
	private String contact;
	private String disease;
	private String patientNo;
	private int historyadr;
	private String historyadrtxt;
	private int familyadr;
	private String familyadrtxt;
	private int relationship;
	private String info;
	private String info6txt;
	private String info7txt;
	private List<PlainExamVo> drug1;
	private List<PlainExamVo> drug2;
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
	private String adrDate;
	private String adrH;
	private String adrM;
	private String adrDescription;
	private int adrDeal;
	private int adrDeal3;
	private String adrDealDose;
	private String adrDeal3txt;
	private String adrDeal4txt;
	private String adrDealRemark;
	private int ending;
	private String endingtxt;
	private String deathDate;
	private String deathReason;
	private int adrStop;
	private int adrRestart;
	private int evaluate;
	private int career;
	private String careertxt;
	private String email;
	private String reportDate;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getEthic() {
		return ethic;
	}
	public void setEthic(int ethic) {
		this.ethic = ethic;
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
	public int getHistoryadr() {
		return historyadr;
	}
	public void setHistoryadr(int historyadr) {
		this.historyadr = historyadr;
	}
	public String getHistoryadrtxt() {
		return historyadrtxt;
	}
	public void setHistoryadrtxt(String historyadrtxt) {
		this.historyadrtxt = historyadrtxt;
	}
	public int getFamilyadr() {
		return familyadr;
	}
	public void setFamilyadr(int familyadr) {
		this.familyadr = familyadr;
	}
	public String getFamilyadrtxt() {
		return familyadrtxt;
	}
	public void setFamilyadrtxt(String familyadrtxt) {
		this.familyadrtxt = familyadrtxt;
	}
	public int getRelationship() {
		return relationship;
	}
	public void setRelationship(int relationship) {
		this.relationship = relationship;
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
	public List<PlainExamVo> getDrug1() {
		return drug1;
	}
	public void setDrug1(List<PlainExamVo> drug1) {
		this.drug1 = drug1;
	}
	public List<PlainExamVo> getDrug2() {
		return drug2;
	}
	public void setDrug2(List<PlainExamVo> drug2) {
		this.drug2 = drug2;
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
	public String getAdrDate() {
		return adrDate;
	}
	public void setAdrDate(String adrDate) {
		this.adrDate = adrDate;
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
	public int getAdrDeal() {
		return adrDeal;
	}
	public void setAdrDeal(int adrDeal) {
		this.adrDeal = adrDeal;
	}
	public int getAdrDeal3() {
		return adrDeal3;
	}
	public void setAdrDeal3(int adrDeal3) {
		this.adrDeal3 = adrDeal3;
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
	public int getEnding() {
		return ending;
	}
	public void setEnding(int ending) {
		this.ending = ending;
	}
	public String getEndingtxt() {
		return endingtxt;
	}
	public void setEndingtxt(String endingtxt) {
		this.endingtxt = endingtxt;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getDeathReason() {
		return deathReason;
	}
	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}
	public int getAdrStop() {
		return adrStop;
	}
	public void setAdrStop(int adrStop) {
		this.adrStop = adrStop;
	}
	public int getAdrRestart() {
		return adrRestart;
	}
	public void setAdrRestart(int adrRestart) {
		this.adrRestart = adrRestart;
	}
	public int getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(int evaluate) {
		this.evaluate = evaluate;
	}
	public int getCareer() {
		return career;
	}
	public void setCareer(int career) {
		this.career = career;
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
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
