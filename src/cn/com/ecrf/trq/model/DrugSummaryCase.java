package cn.com.ecrf.trq.model;

import java.util.Date;

public class DrugSummaryCase {

	private int id;
	//观察表编号
	private String no;
	//病程首次用药时间
	private Date startDate;
	//病程末次用药时间
	private Date endDate;
	//痰热清注射液用药结局
	private String ending;
	//直接死因
	private String deathReason;
	//死亡时间
	private Date deathDate;
	//是否出现ADR
	private String adr;
	//有无痰热清注射液用药不合理的现象
	private String unreasonable;
	//药师是否进行相关干预
	private String intervention;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEnding() {
		return ending;
	}
	public void setEnding(String ending) {
		this.ending = ending;
	}
	public String getDeathReason() {
		return deathReason;
	}
	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}
	public Date getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getUnreasonable() {
		return unreasonable;
	}
	public void setUnreasonable(String unreasonable) {
		this.unreasonable = unreasonable;
	}
	public String getIntervention() {
		return intervention;
	}
	public void setIntervention(String intervention) {
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
