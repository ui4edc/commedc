package cn.com.ecrf.trq.model;

import java.util.List;

import cn.com.ecrf.trq.vo.MutilSelect;

public class PersonAllergicHistoryCase {
	private int id;
	//观察表编号
	private String no;
	//吸烟史
	private String smoke;
	//饮酒史
	private String drink;
	
	private String drug; 
	
	private String food;
	
	private String other;
	
	private String foodlb;
	
	private String druglb;
	
	private String otherlb;

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

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFoodlb() {
		return foodlb;
	}

	public void setFoodlb(String foodlb) {
		this.foodlb = foodlb;
	}

	public String getDruglb() {
		return druglb;
	}

	public void setDruglb(String druglb) {
		this.druglb = druglb;
	}

	public String getOtherlb() {
		return otherlb;
	}

	public void setOtherlb(String otherlb) {
		this.otherlb = otherlb;
	}
	
	
	
	
	
}
