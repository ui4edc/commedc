package cn.com.ecrf.trq.vo;

import java.util.List;

public class DrugCombinationVo {
	//id
	private int id;
	//观察表编号
	private String no;
	
	private List<DrugInstanceObject> drug;

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

	public List<DrugInstanceObject> getDrug() {
		return drug;
	}

	public void setDrug(List<DrugInstanceObject> drug) {
		this.drug = drug;
	}
	
	
	
	
}
