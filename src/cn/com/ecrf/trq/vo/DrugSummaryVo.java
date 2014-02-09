package cn.com.ecrf.trq.vo;

/**
 * 用药小结
 * @author user
 *
 */
public class DrugSummaryVo {
	private int id;
	//观察表编号
	private String no;
	//病程首次用药时间
	private String startDate;
	//病程末次用药时间
	private String endDate;
	//痰热清注射液用药结局
	private int ending;
	//直接死因
	private String deathReason;
	//死亡时间
	private String deathDate;
	//是否出现ADR
	private int adr;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public int getAdr() {
		return adr;
	}
	public void setAdr(int adr) {
		this.adr = adr;
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
