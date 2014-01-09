package cn.com.ecrf.trq.vo.lab;

/**
 * 实验室检查（肾功两项）
 * @author user
 *
 */
public class LabKidneyExamVo {
	//检查日期
	private String jcrq;
	//尿素氮BUN
	private String nsd;
	//血肌酐CR
	private String xjg;
	public String getJcrq() {
		return jcrq;
	}
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	public String getNsd() {
		return nsd;
	}
	public void setNsd(String nsd) {
		this.nsd = nsd;
	}
	public String getXjg() {
		return xjg;
	}
	public void setXjg(String xjg) {
		this.xjg = xjg;
	}
	
	
}
