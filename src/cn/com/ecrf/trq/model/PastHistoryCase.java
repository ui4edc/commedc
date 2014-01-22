package cn.com.ecrf.trq.model;

import java.util.List;

/**
 * 包含既往史
 * @author user
 *
 */
public class PastHistoryCase {
	private int id;
	
	private String no;
	//常见疾病:是，否
	private String disease;
	//常见疾病列表
	private String diseaselb;
	//private List<PastHistoryObject> cjjblb;
	//过敏性疾病史:是，否
	private String allergy;
	//过敏性疾病史
	private String allergylb;
	
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
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getDiseaselb() {
		return diseaselb;
	}
	public void setDiseaselb(String diseaselb) {
		this.diseaselb = diseaselb;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getAllergylb() {
		return allergylb;
	}
	public void setAllergylb(String allergylb) {
		this.allergylb = allergylb;
	}
	
	
	
	
}
