package cn.com.ecrf.trq.model;

import java.util.Date;

public class BodyExamCase {

	private int id;
	
	private String no;
	
	private int done;
	
	private Date examDateDate;
	
	private String breathe;
	
	private String rate;
	
	private String ssy;
	
	private String szy;
	
	private String temperature;
	
	private String examlb;
	

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

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public Date getExamDateDate() {
		return examDateDate;
	}

	public void setExamDateDate(Date examDateDate) {
		this.examDateDate = examDateDate;
	}

	public String getBreathe() {
		return breathe;
	}

	public void setBreathe(String breathe) {
		this.breathe = breathe;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSsy() {
		return ssy;
	}

	public void setSsy(String ssy) {
		this.ssy = ssy;
	}

	public String getSzy() {
		return szy;
	}

	public void setSzy(String szy) {
		this.szy = szy;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getExamlb() {
		return examlb;
	}

	public void setExamlb(String examlb) {
		this.examlb = examlb;
	}
	
	
}
