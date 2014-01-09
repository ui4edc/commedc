package cn.com.ecrf.trq.vo;

/**
 * 用药小结
 * @author user
 *
 */
public class DrugSummaryVo {
	private int id;
	//观察表编号
	private String no;
	//病程首次用药时间
	private String startDate;
	//病程末次用药时间
	private String endDate;
	//痰热清注射液用药结局
	private int result;
	//直接死因
	private String deathReason;
	//死亡时间
	private String deathDate;
	//是否出现ADR
	private int adr;
	//有无痰热清注射液用药不合理的现象
	private int yybhlxx;
	//药师是否进行相关干预
	private int yyjxxggy;
	//药师如何进行干预
	private String yyjxgymc;
	//治疗总费用
	private String zlzfy;
	//药品总费用
	private String ypzfy;
	//注射液费用
	private String zsyfy;
	//备注
	private String comment;
	
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
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getDeathReason() {
		return deathReason;
	}
	public void setDeathReason(String deathReason) {
		this.deathReason = deathReason;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public int getAdr() {
		return adr;
	}
	public void setAdr(int adr) {
		this.adr = adr;
	}
	public int getYybhlxx() {
		return yybhlxx;
	}
	public void setYybhlxx(int yybhlxx) {
		this.yybhlxx = yybhlxx;
	}
	public int getYyjxxggy() {
		return yyjxxggy;
	}
	public void setYyjxxggy(int yyjxxggy) {
		this.yyjxxggy = yyjxxggy;
	}
	public String getYyjxgymc() {
		return yyjxgymc;
	}
	public void setYyjxgymc(String yyjxgymc) {
		this.yyjxgymc = yyjxgymc;
	}
	public String getZlzfy() {
		return zlzfy;
	}
	public void setZlzfy(String zlzfy) {
		this.zlzfy = zlzfy;
	}
	public String getYpzfy() {
		return ypzfy;
	}
	public void setYpzfy(String ypzfy) {
		this.ypzfy = ypzfy;
	}
	public String getZsyfy() {
		return zsyfy;
	}
	public void setZsyfy(String zsyfy) {
		this.zsyfy = zsyfy;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
