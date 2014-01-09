package cn.com.ecrf.trq.vo.lab;

/**
 * 血糖测定
 * @author user
 *
 */
public class BloodGlucoseExamVo {
	//检查日期
	private String jcrq;
	//空腹血糖
	private String kfxt;
	//餐后血糖
	private String chxt;
	public String getJcrq() {
		return jcrq;
	}
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	public String getKfxt() {
		return kfxt;
	}
	public void setKfxt(String kfxt) {
		this.kfxt = kfxt;
	}
	public String getChxt() {
		return chxt;
	}
	public void setChxt(String chxt) {
		this.chxt = chxt;
	}
	
	
}
