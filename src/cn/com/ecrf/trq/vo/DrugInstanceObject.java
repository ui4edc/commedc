package cn.com.ecrf.trq.vo;

public class DrugInstanceObject {
	private int id;
	//同瓶用药名称
	private String name;
	//同瓶用药剂量
	private String dose;
	//同瓶用药剂量单位
	private String unit;
	//百分比
	private String percent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	
	
}
