package cn.com.ecrf.trq.model;

import java.util.Date;

public class ECGExamCase {

	private int id;
	private String no;
	private String normal;
	private Date examDateDate;
	private int done;
	private String description;
	
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
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExamDateDate() {
		return examDateDate;
	}
	public void setExamDateDate(Date examDateDate) {
		this.examDateDate = examDateDate;
	}
	
	
}
