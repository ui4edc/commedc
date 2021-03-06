package cn.com.ecrf.trq.model;

import java.util.Date;

public class Organization {
	private int id;
	private String name;
	private String code;
	private String parentOrganization;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	private String organizationDesc;
	private String location;
	private String mailAddress;
	private int instanceNumber;
	private int adminUserId;
	private String adminUserName;
	private int crmUserId;
	private String crmUserName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentOrganization() {
		return parentOrganization;
	}
	public void setParentOrganization(String parentOrganization) {
		this.parentOrganization = parentOrganization;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrganizationDesc() {
		return organizationDesc;
	}
	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public int getInstanceNumber() {
		return instanceNumber;
	}
	public void setInstanceNumber(int instanceNumber) {
		this.instanceNumber = instanceNumber;
	}
	public int getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}
	public int getCrmUserId() {
		return crmUserId;
	}
	public void setCrmUserId(int crmUserId) {
		this.crmUserId = crmUserId;
	}
	public String getCrmUserName() {
		return crmUserName;
	}
	public void setCrmUserName(String crmUserName) {
		this.crmUserName = crmUserName;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	
	
	
}
