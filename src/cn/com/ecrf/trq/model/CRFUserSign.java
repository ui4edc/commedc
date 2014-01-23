package cn.com.ecrf.trq.model;

import java.util.Date;

public class CRFUserSign {

	private String no;
	private int lockStatus;
	private Date croCreateTime;
	private String croName;
	private Date croSignTime;
	private String crmName;
	private Date crmSignTime;
	private String dmName;
	private Date dmSignTime;
	private Date croUpdateTime;
	private int progress;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(int lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Date getCroCreateTime() {
		return croCreateTime;
	}
	public void setCroCreateTime(Date croCreateTime) {
		this.croCreateTime = croCreateTime;
	}
	public String getCroName() {
		return croName;
	}
	public void setCroName(String croName) {
		this.croName = croName;
	}
	public Date getCroSignTime() {
		return croSignTime;
	}
	public void setCroSignTime(Date croSignTime) {
		this.croSignTime = croSignTime;
	}
	public String getCrmName() {
		return crmName;
	}
	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}
	public Date getCrmSignTime() {
		return crmSignTime;
	}
	public void setCrmSignTime(Date crmSignTime) {
		this.crmSignTime = crmSignTime;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	public Date getDmSignTime() {
		return dmSignTime;
	}
	public void setDmSignTime(Date dmSignTime) {
		this.dmSignTime = dmSignTime;
	}
	public Date getCroUpdateTime() {
		return croUpdateTime;
	}
	public void setCroUpdateTime(Date croUpdateTime) {
		this.croUpdateTime = croUpdateTime;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	
	
}
