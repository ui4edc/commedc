package cn.com.ecrf.trq.model.list;

import java.util.Date;

public class ListReturn {

	private boolean crf;
	private String abbr;
	private String no;
	private Date createDate;
	private Date lastModified;
	private String orderBy;
	private int pageNo;
	private int pageSize;
	private int progress;
	private int progressType;
	private int type;
	private int id;
	private Date doubtDate;
	private String doubter;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
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
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
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
	public Date getDoubtDate() {
		return doubtDate;
	}
	public void setDoubtDate(Date doubtDate) {
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
