package cn.com.ecrf.trq.vo;

public class DrugCombinationVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//通用名
	private String name;
	//开始时间
	private String startDate;
	//结束时间
	private String endDate;
	//单次用药剂量
	private String dcyyjl;
	//剂量单位
	private String jldw;
	//给药途径
	private String gytj;
	//给药频次
	private String gypc;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDcyyjl() {
		return dcyyjl;
	}
	public void setDcyyjl(String dcyyjl) {
		this.dcyyjl = dcyyjl;
	}
	public String getJldw() {
		return jldw;
	}
	public void setJldw(String jldw) {
		this.jldw = jldw;
	}
	public String getGytj() {
		return gytj;
	}
	public void setGytj(String gytj) {
		this.gytj = gytj;
	}
	public String getGypc() {
		return gypc;
	}
	public void setGypc(String gypc) {
		this.gypc = gypc;
	}
	
	
}
