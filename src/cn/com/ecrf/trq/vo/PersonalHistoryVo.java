package cn.com.ecrf.trq.vo;

import java.util.List;

public class PersonalHistoryVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//吸烟史
	private int smoke;
	//饮酒史
	private int drink;
	
	private int hasDrug; 
	
	private int hasFood;
	
	private int hasOther;
	
	private List<MutilSelect> food;
	
	private List<MutilSelect> drug;
	
	private List<MutilSelect> other;

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

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}

	public int getDrink() {
		return drink;
	}

	public void setDrink(int drink) {
		this.drink = drink;
	}

	public int getHasDrug() {
		return hasDrug;
	}

	public void setHasDrug(int hasDrug) {
		this.hasDrug = hasDrug;
	}

	public int getHasFood() {
		return hasFood;
	}

	public void setHasFood(int hasFood) {
		this.hasFood = hasFood;
	}

	public int getHasOther() {
		return hasOther;
	}

	public void setHasOther(int hasOther) {
		this.hasOther = hasOther;
	}

	public List<MutilSelect> getFood() {
		return food;
	}

	public void setFood(List<MutilSelect> food) {
		this.food = food;
	}

	public List<MutilSelect> getDrug() {
		return drug;
	}

	public void setDrug(List<MutilSelect> drug) {
		this.drug = drug;
	}

	public List<MutilSelect> getOther() {
		return other;
	}

	public void setOther(List<MutilSelect> other) {
		this.other = other;
	}
	
	
	
	
	
}
