package cn.com.ecrf.trq.model.list;

import java.util.Date;

public class ListCondition {
	private boolean crf;
	private String abbr;
	private String no;
	private Date createDateFrom;
	private Date createDateTo;
	private Date lastModifiedFrom;
	private String orderBy;
	private boolean desc;
	/*private int pageNo;
	private int pageSize;*/
	private int limitStart;
	private int limitSize;
	private int progress;
	private int progressType;
	private int type;
	private int lockStatus;
	private boolean undealed;
	private Date doubtDateFrom;
	private Date doubtDateTo;
	private Date lastModifiedTo;
	private String userName;
	private String roleName;
	
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
	public Date getCreateDateFrom() {
		return createDateFrom;
	}
	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
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
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
	public int getLimitSize() {
		return limitSize;
	}
	public void setLimitSize(int limitSize) {
		this.limitSize = limitSize;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
