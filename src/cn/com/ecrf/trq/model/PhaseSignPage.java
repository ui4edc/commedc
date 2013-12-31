package cn.com.ecrf.trq.model;

import java.util.Date;

/**
 * 各观察表的签字表
 * @author user
 *
 */
public class PhaseSignPage {
	private String blbh;
	private int lockStatus;
	private Date croCreateTime;
	private Date croSignTime;
	private String croName;
	private Date crmSignTime;
	private String crmName;
	private Date dmSignTime;
	private String dmName;
	
	public String getBlbh() {
		return blbh;
	}
	public void setBlbh(String blbh) {
		this.blbh = blbh;
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
	public Date getCroSignTime() {
		return croSignTime;
	}
	public void setCroSignTime(Date croSignTime) {
		this.croSignTime = croSignTime;
	}
	public String getCroName() {
		return croName;
	}
	public void setCroName(String croName) {
		this.croName = croName;
	}
	public Date getCrmSignTime() {
		return crmSignTime;
	}
	public void setCrmSignTime(Date crmSignTime) {
		this.crmSignTime = crmSignTime;
	}
	public String getCrmName() {
		return crmName;
	}
	public void setCrmName(String crmName) {
		this.crmName = crmName;
	}
	public Date getDmSignTime() {
		return dmSignTime;
	}
	public void setDmSignTime(Date dmSignTime) {
		this.dmSignTime = dmSignTime;
	}
	public String getDmName() {
		return dmName;
	}
	public void setDmName(String dmName) {
		this.dmName = dmName;
	}
	
	
}
