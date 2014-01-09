package cn.com.ecrf.trq.vo;

public class ADRDrugObject {
	private int id;
	//批准文号
	private String pzwh;
	//商品名称
	private String spmc;
	//通用名称（含剂型）
	private String tymc;
	//生产厂家
	private String sccj;
	//生产批号
	private String scph;
	//用法用量（次剂量、途径、日次数）
	private String yfyl;
	//用药开始时间
	private String yykssj;
	//用药结束时间
	private String yyjssj;
	//用药原因
	private String reason;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public String getTymc() {
		return tymc;
	}
	public void setTymc(String tymc) {
		this.tymc = tymc;
	}
	public String getSccj() {
		return sccj;
	}
	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	public String getScph() {
		return scph;
	}
	public void setScph(String scph) {
		this.scph = scph;
	}
	public String getYfyl() {
		return yfyl;
	}
	public void setYfyl(String yfyl) {
		this.yfyl = yfyl;
	}
	public String getYykssj() {
		return yykssj;
	}
	public void setYykssj(String yykssj) {
		this.yykssj = yykssj;
	}
	public String getYyjssj() {
		return yyjssj;
	}
	public void setYyjssj(String yyjssj) {
		this.yyjssj = yyjssj;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
