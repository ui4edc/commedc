package cn.com.ecrf.trq.vo;

import java.util.Date;

public class DoubtRecordGetVo {

	private int id;
	private int menu;
	private String doubtField;
	private Date doubtDate;
	private String doubter;
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
	
	
}
