package cn.com.ecrf.trq.vo;

import java.util.List;

public class PastHistoryVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//常见疾病:是，否
	private int cjjb;
	//常见疾病列表
	private String cjjblb;
	//private List<PastHistoryObject> cjjblb;
	//过敏性疾病史:是，否
	private  int gmxjbs;
	//过敏性疾病史列表
	private List<CheckBoxVo> gmxjbslb;
	
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
	public int getCjjb() {
		return cjjb;
	}
	public void setCjjb(int cjjb) {
		this.cjjb = cjjb;
	}
	public String getCjjblb() {
		return cjjblb;
	}
	public void setCjjblb(String cjjblb) {
		this.cjjblb = cjjblb;
	}
	public int getGmxjbs() {
		return gmxjbs;
	}
	public void setGmxjbs(int gmxjbs) {
		this.gmxjbs = gmxjbs;
	}
	public List<CheckBoxVo> getGmxjbslb() {
		return gmxjbslb;
	}
	public void setGmxjbslb(List<CheckBoxVo> gmxjbslb) {
		this.gmxjbslb = gmxjbslb;
	}

	
}
