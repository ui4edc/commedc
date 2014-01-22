package cn.com.ecrf.trq.model.list;

import java.util.Date;

public class ListCondition {
	private boolean crf;
	private String abbr;
	private String no;
	private Date createDateForm;
	private Date createDateTo;
	private Date lastModifiedFrom;
	private String orderBy;
	private boolean desc;
	private int pageNo;
	private int pageSize;
	private int progress;
	private int progressType;
	private int type;
	private int lockStatus;
	private boolean undealed;
	private Date doubtDateFrom;
	private Date doubtDateTo;
	private Date lastModifiedTo;
	
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
	public boolean isUndealed() {
		return undealed;
	}
	public void setUndealed(boolean undealed) {
		this.undealed = undealed;
	}
	public boolean isDesc() {
		return desc;
	}
	public void setDesc(boolean desc) {
		this.desc = desc;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Date getCreateDateForm() {
		return createDateForm;
	}
	public void setCreateDateForm(Date createDateForm) {
		this.createDateForm = createDateForm;
	}
	public Date getCreateDateTo() {
		return createDateTo;
	}
	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}
	public Date getLastModifiedFrom() {
		return lastModifiedFrom;
	}
	public void setLastModifiedFrom(Date lastModifiedFrom) {
		this.lastModifiedFrom = lastModifiedFrom;
	}
	public Date getDoubtDateFrom() {
		return doubtDateFrom;
	}
	public void setDoubtDateFrom(Date doubtDateFrom) {
		this.doubtDateFrom = doubtDateFrom;
	}
	public Date getDoubtDateTo() {
		return doubtDateTo;
	}
	public void setDoubtDateTo(Date doubtDateTo) {
		this.doubtDateTo = doubtDateTo;
	}
	public Date getLastModifiedTo() {
		return lastModifiedTo;
	}
	public void setLastModifiedTo(Date lastModifiedTo) {
		this.lastModifiedTo = lastModifiedTo;
	}
	
	
	
	
}
