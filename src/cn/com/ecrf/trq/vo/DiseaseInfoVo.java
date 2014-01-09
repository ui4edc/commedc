package cn.com.ecrf.trq.vo;

import java.util.List;

public class DiseaseInfoVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//内科疾病
	private boolean nkjb;
	//内科疾病名称:上呼吸道感染, 急性支气管炎
	private List<SubCheckBoxVo> nkjblb;
	//外科疾病:是，否
	private boolean wkjb;
	//外科疾病名称
	private int wkjbmc;
	//传染病:是，否
	private boolean crb;
	//传染病:流行性
	private int crbmc;
	//传染病其他
	private String crbOther;
	//其他疾病：是，否
	private boolean qtjb;
	//其他疾病名称
	private String qtjbOther;
	//第一诊断(编号)
	private String dyzd;
	//中医诊断
	private int zyzd;
	//中医诊断名称
	private int zyzdOther;
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
	public boolean isNkjb() {
		return nkjb;
	}
	public void setNkjb(boolean nkjb) {
		this.nkjb = nkjb;
	}
	public List<SubCheckBoxVo> getNkjblb() {
		return nkjblb;
	}
	public void setNkjblb(List<SubCheckBoxVo> nkjblb) {
		this.nkjblb = nkjblb;
	}
	public boolean isWkjb() {
		return wkjb;
	}
	public void setWkjb(boolean wkjb) {
		this.wkjb = wkjb;
	}
	public int getWkjbmc() {
		return wkjbmc;
	}
	public void setWkjbmc(int wkjbmc) {
		this.wkjbmc = wkjbmc;
	}
	public boolean isCrb() {
		return crb;
	}
	public void setCrb(boolean crb) {
		this.crb = crb;
	}
	public int getCrbmc() {
		return crbmc;
	}
	public void setCrbmc(int crbmc) {
		this.crbmc = crbmc;
	}
	public String getCrbOther() {
		return crbOther;
	}
	public void setCrbOther(String crbOther) {
		this.crbOther = crbOther;
	}
	public boolean isQtjb() {
		return qtjb;
	}
	public void setQtjb(boolean qtjb) {
		this.qtjb = qtjb;
	}
	public String getQtjbOther() {
		return qtjbOther;
	}
	public void setQtjbOther(String qtjbOther) {
		this.qtjbOther = qtjbOther;
	}
	public String getDyzd() {
		return dyzd;
	}
	public void setDyzd(String dyzd) {
		this.dyzd = dyzd;
	}
	public int getZyzd() {
		return zyzd;
	}
	public void setZyzd(int zyzd) {
		this.zyzd = zyzd;
	}
	public int getZyzdOther() {
		return zyzdOther;
	}
	public void setZyzdOther(int zyzdOther) {
		this.zyzdOther = zyzdOther;
	}
	
	
	
}
