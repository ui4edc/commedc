package cn.com.ecrf.trq.model;

import java.util.Date;
import java.util.List;

import cn.com.ecrf.trq.vo.CheckBoxVo;
/**
 * 包含患者基本情况和痰热清注射液用药史
 * @author user
 *
 */
public class PatientInfoCase {
	private int id;
	//观察表编号
	private String no;
	//患者姓名
	private String name;
	//姓名缩写
	private String abbr;
	//出生年月
	private Date birthday; //YYYY-MM-DD
	//年龄
	private int age;
	//体重
	private String weight;
	//性别
	private String sex;
	//民族
	private String ethic;
	//怀孕史：妊娠期，怀孕期，无
	private String hys;
	//身高
	private String height;
	//用药科室
	private String yyks;
	private String yykstxt;
	//入院日期
	private Date ryrq;
	//出院日期
	private Date cyrq;
	//医疗费用方式
	private String ylfyfs;
	private String ylfyfstxt;
	
	private boolean heightud;
	
	private boolean weightud;
	
	private Date createTime;
	
	private String createBy;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEthic() {
		return ethic;
	}
	public void setEthic(String ethic) {
		this.ethic = ethic;
	}
	public String getHys() {
		return hys;
	}
	public void setHys(String hys) {
		this.hys = hys;
	}
	
	public String getYyks() {
		return yyks;
	}
	public void setYyks(String yyks) {
		this.yyks = yyks;
	}
	public Date getRyrq() {
		return ryrq;
	}
	public void setRyrq(Date ryrq) {
		this.ryrq = ryrq;
	}
	public Date getCyrq() {
		return cyrq;
	}
	public void setCyrq(Date cyrq) {
		this.cyrq = cyrq;
	}
	public String getYlfyfs() {
		return ylfyfs;
	}
	public void setYlfyfs(String ylfyfs) {
		this.ylfyfs = ylfyfs;
	}
	public boolean isHeightud() {
		return heightud;
	}
	public void setHeightud(boolean heightud) {
		this.heightud = heightud;
	}
	public boolean isWeightud() {
		return weightud;
	}
	public void setWeightud(boolean weightud) {
		this.weightud = weightud;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getYykstxt() {
		return yykstxt;
	}
	public void setYykstxt(String yykstxt) {
		this.yykstxt = yykstxt;
	}
	public String getYlfyfstxt() {
		return ylfyfstxt;
	}
	public void setYlfyfstxt(String ylfyfstxt) {
		this.ylfyfstxt = ylfyfstxt;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	
	
	
	
}
