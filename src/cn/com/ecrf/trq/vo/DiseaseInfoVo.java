package cn.com.ecrf.trq.vo;

import java.util.List;

public class DiseaseInfoVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//第一入院诊断
	private String diagnosis; 
	private String disease1;
	private String disease2;
	private String disease3;
	private String diseasetxt;
	private String fy1;
	private String fy2;
	private int zy;
	private String zytxt;
	
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
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDisease1() {
		return disease1;
	}
	public void setDisease1(String disease1) {
		this.disease1 = disease1;
	}
	public String getDisease2() {
		return disease2;
	}
	public void setDisease2(String disease2) {
		this.disease2 = disease2;
	}
	public String getDisease3() {
		return disease3;
	}
	public void setDisease3(String disease3) {
		this.disease3 = disease3;
	}
	public String getDiseasetxt() {
		return diseasetxt;
	}
	public void setDiseasetxt(String diseasetxt) {
		this.diseasetxt = diseasetxt;
	}
	public String getFy1() {
		return fy1;
	}
	public void setFy1(String fy1) {
		this.fy1 = fy1;
	}
	public String getFy2() {
		return fy2;
	}
	public void setFy2(String fy2) {
		this.fy2 = fy2;
	}
	public int getZy() {
		return zy;
	}
	public void setZy(int zy) {
		this.zy = zy;
	}
	public String getZytxt() {
		return zytxt;
	}
	public void setZytxt(String zytxt) {
		this.zytxt = zytxt;
	}
	
	
	
	
	
}
