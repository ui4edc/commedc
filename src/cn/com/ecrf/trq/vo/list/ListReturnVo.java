package cn.com.ecrf.trq.vo.list;

public class ListReturnVo {

	private boolean crf;
	private String abbr;
	private String no;
	private String createDate = "";
	private String lastModified = "";
	private String orderBy;
	private int pageNo;
	private int pageSize;
	private String progress = "";
	private int progressType;
	private int type;
	private int id;
	private String doubtDate = "";
	private String doubter = "";
	private int doubtNumber;
	
	public boolean isCrf() {
		return crf;
	}
	public void setCrf(boolean crf) {
		this.crf = crf;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public int getProgressType() {
		return progressType;
	}
	public void setProgressType(int progressType) {
		this.progressType = progressType;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoubtDate() {
		return doubtDate;
	}
	public void setDoubtDate(String doubtDate) {
		this.doubtDate = doubtDate;
	}
	public String getDoubter() {
		return doubter;
	}
	public void setDoubter(String doubter) {
		this.doubter = doubter;
	}
	public int getDoubtNumber() {
		return doubtNumber;
	}
	public void setDoubtNumber(int doubtNumber) {
		this.doubtNumber = doubtNumber;
	}
	
	
}
