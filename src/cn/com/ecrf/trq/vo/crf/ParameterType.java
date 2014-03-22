package cn.com.ecrf.trq.vo.crf;

public class ParameterType {
	private int id;
	private int drugUseId;
	private String uuid;
	private int drugId;
	private String no;
	private String abbr;
	private int times;
	private int menu;
	private String keyword;
	private String type;
	private int selectedItemId;
	private int baseItemId;
	
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
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getMenu() {
		return menu;
	}
	public void setMenu(int menu) {
		this.menu = menu;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDrugUseId() {
		return drugUseId;
	}
	public void setDrugUseId(int drugUseId) {
		this.drugUseId = drugUseId;
	}
	public int getSelectedItemId() {
		return selectedItemId;
	}
	public void setSelectedItemId(int selectedItemId) {
		this.selectedItemId = selectedItemId;
	}
	public int getBaseItemId() {
		return baseItemId;
	}
	public void setBaseItemId(int baseItemId) {
		this.baseItemId = baseItemId;
	}
	public int getDrugId() {
		return drugId;
	}
	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
