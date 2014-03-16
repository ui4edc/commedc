package cn.com.ecrf.trq.model;

import java.util.Date;

public class DrugSummaryCase {

	private int id;
	//观察表编号
	private String no;
	//病程首次用药时间
	private Date startDateDate;
	//病程末次用药时间
	private Date endDateDate;
	//痰热清注射液用药结局
	private int ending;
	//直接死因
	private String deathReason;
	//死亡时间
	private Date deathDateDate;
	private int hasAdr;
	//是否出现ADR
	private String adr;
	private String adr1;
	private String adr2;
	private String adr3;
	private String adr4;
	private String adr5;
	private String adr6;
	private String adrtxt;
	private int hasOtherAdr;
	//有无痰热清注射液用药不合理的现象
	private int unreasonable;
	//药师是否进行相关干预
	private int intervention;
	//药师如何进行干预
	private String interventiontxt;
	//治疗总费用
	private String treatmentCost;
	//药品总费用
	private String drugCost;
	//注射液费用
	private String trqCost;
	//备注
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
	public Date getStartDateDate() {
		return startDateDate;
	}
	public void setStartDateDate(Date startDateDate) {
		this.startDateDate = startDateDate;
	}
	public Date getEndDateDate() {
		return endDateDate;
	}
	public void setEndDateDate(Date endDateDate) {
		this.endDateDate = endDateDate;
	}
	public int getEnding() {
		return ending;
	}
	public void setEnding(int ending) {
		this.ending = ending;
	}
	public String getDeathReason() {
		return deathReason;
	}
	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}
	public Date getDeathDateDate() {
		return deathDateDate;
	}
	public void setDeathDateDate(Date deathDateDate) {
		this.deathDateDate = deathDateDate;
	}
	public int getHasAdr() {
		return hasAdr;
	}
	public void setHasAdr(int hasAdr) {
		this.hasAdr = hasAdr;
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
	public String getAdrtxt() {
		return adrtxt;
	}
	public void setAdrtxt(String adrtxt) {
		this.adrtxt = adrtxt;
	}
	public int getHasOtherAdr() {
		return hasOtherAdr;
	}
	public void setHasOtherAdr(int hasOtherAdr) {
		this.hasOtherAdr = hasOtherAdr;
	}
	public int getUnreasonable() {
		return unreasonable;
	}
	public void setUnreasonable(int unreasonable) {
		this.unreasonable = unreasonable;
	}
	public int getIntervention() {
		return intervention;
	}
	public void setIntervention(int intervention) {
		this.intervention = intervention;
	}
	public String getInterventiontxt() {
		return interventiontxt;
	}
	public void setInterventiontxt(String interventiontxt) {
		this.interventiontxt = interventiontxt;
	}
	public String getTreatmentCost() {
		return treatmentCost;
	}
	public void setTreatmentCost(String treatmentCost) {
		this.treatmentCost = treatmentCost;
	}
	public String getDrugCost() {
		return drugCost;
	}
	public void setDrugCost(String drugCost) {
		this.drugCost = drugCost;
	}
	public String getTrqCost() {
		return trqCost;
	}
	public void setTrqCost(String trqCost) {
		this.trqCost = trqCost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
