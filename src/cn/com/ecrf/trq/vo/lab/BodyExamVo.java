package cn.com.ecrf.trq.vo.lab;

/**
 * 体格检查
 * @author user
 *
 */
public class BodyExamVo {
	//
	private int id;
	//检查日期
	private String jcrq;
	//体温
	private String tw;
	//呼吸
	private String hx;
	//收缩压
	private String ssy;
	//舒张压
	private String szy;
	//心率
	private String xl;
	
	public String getJcrq() {
		return jcrq;
	}
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	public String getTw() {
		return tw;
	}
	public void setTw(String tw) {
		this.tw = tw;
	}
	public String getHx() {
		return hx;
	}
	public void setHx(String hx) {
		this.hx = hx;
	}
	public String getSsy() {
		return ssy;
	}
	public void setSsy(String ssy) {
		this.ssy = ssy;
	}
	public String getSzy() {
		return szy;
	}
	public void setSzy(String szy) {
		this.szy = szy;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	
	
}
