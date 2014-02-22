package cn.com.ecrf.trq.vo;

public class DoubtRecordSubmitVo {
	private int id;
	private int menu;
	private int doubtId;
	private int fieldId;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMenu() {
		return menu;
	}
	public void setMenu(int menu) {
		this.menu = menu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDoubtId() {
		return doubtId;
	}
	public void setDoubtId(int doubtId) {
		this.doubtId = doubtId;
	}
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	
	
}
