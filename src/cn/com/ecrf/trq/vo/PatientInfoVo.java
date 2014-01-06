package cn.com.ecrf.trq.vo;

import java.util.Date;
import java.util.List;

public class PatientInfoVo {
		//id
		private int id;
		//观察表编号
		private String blbh;
		//患者姓名
		private String name;
		//姓名缩写
		private String abbr;
		//出生年月
		private String birthday; //YYYY-MM-DD
		//年龄
		private String age;
		//体重
		private double weight;
		//性别
		private int sex;
		//怀孕史：妊娠期，怀孕期，无
		private int hys;
		//身高
		private int height;
		//用药科室
		private List<CheckBoxVo> yyks;
		//入院日期
		private String ryrq;
		//出院日期
		private String cyrq;
		//医疗费用方式
		private String ylfyfs;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getBlbh() {
			return blbh;
		}
		public void setBlbh(String blbh) {
			this.blbh = blbh;
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
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public double getWeight() {
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}
		public int getSex() {
			return sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
		public int getHys() {
			return hys;
		}
		public void setHys(int hys) {
			this.hys = hys;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public List<CheckBoxVo> getYyks() {
			return yyks;
		}
		public void setYyks(List<CheckBoxVo> yyks) {
			this.yyks = yyks;
		}
		public String getRyrq() {
			return ryrq;
		}
		public void setRyrq(String ryrq) {
			this.ryrq = ryrq;
		}
		public String getCyrq() {
			return cyrq;
		}
		public void setCyrq(String cyrq) {
			this.cyrq = cyrq;
		}
		public String getYlfyfs() {
			return ylfyfs;
		}
		public void setYlfyfs(String ylfyfs) {
			this.ylfyfs = ylfyfs;
		}
		
		
		
}
