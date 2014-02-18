package cn.com.ecrf.trq.model;

import java.util.Date;

public class DoubtRecord {
	private int id;
	private int doubtId;
	private int menuId;
	private String menuName;
	private int fieldId;
	private String fieldName;
	private String doubtField;
	private Date doubtDate;
	private String doubter;
	private String description;
	private Date commitDate;
	private int flag;
	private int total;
	private int unDeal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getFieldId() {
		return fieldId;
	}
	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
	public String getDoubtField() {
		return doubtField;
	}
	public void setDoubtField(String doubtField) {
		this.doubtField = doubtField;
	}
	public Date getDoubtDate() {
		return doubtDate;
	}
	public void setDoubtDate(Date doubtDate) {
		this.doubtDate = doubtDate;
	}
	public String getDoubter() {
		return doubter;
	}
	public void setDoubter(String doubter) {
		this.doubter = doubter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCommitDate() {
		return commitDate;
	}
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUnDeal() {
		return unDeal;
	}
	public void setUnDeal(int unDeal) {
		this.unDeal = unDeal;
	}
	public int getDoubtId() {
		return doubtId;
	}
	public void setDoubtId(int doubtId) {
		this.doubtId = doubtId;
	}
	
	
}
