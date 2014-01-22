package cn.com.ecrf.trq.vo;

import java.util.Date;
import java.util.List;

public class PatientInfoVo {
		//id
		private int id;
		//观察表编号
		private String no;
		//患者姓名
		private String name;
		//姓名缩写
		private String abbr;
		//出生年月
		private String birthday; //YYYY-MM-DD
		//年龄
		private String age;
		//体重
		private String weight;
		private boolean weightud;
		//性别
		private int sex;
		//民族
		private int ethic;
		//民族其他
		//private String ethictxt;
		//怀孕史：妊娠期，怀孕期，无
		private int hys;
		//身高
		private String height;
		private boolean heightud;
		//用药科室
		private int yyks;
		//
		private String yykstxt;
		//入院日期
		private String inDate;
		//出院日期
		private String outDate;
		//医疗费用方式
		private int feemode;
		//医疗费用方式
		private String feemodetxt;
		
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
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
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
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public int getEthic() {
			return ethic;
		}
		public void setEthic(int ethic) {
			this.ethic = ethic;
		}
		public int getYyks() {
			return yyks;
		}
		public void setYyks(int yyks) {
			this.yyks = yyks;
		}
		public String getYykstxt() {
			return yykstxt;
		}
		public void setYykstxt(String yykstxt) {
			this.yykstxt = yykstxt;
		}
		public String getInDate() {
			return inDate;
		}
		public void setInDate(String inDate) {
			this.inDate = inDate;
		}
		public String getOutDate() {
			return outDate;
		}
		public void setOutDate(String outDate) {
			this.outDate = outDate;
		}
		public int getFeemode() {
			return feemode;
		}
		public void setFeemode(int feemode) {
			this.feemode = feemode;
		}
		public String getFeemodetxt() {
			return feemodetxt;
		}
		public void setFeemodetxt(String feemodetxt) {
			this.feemodetxt = feemodetxt;
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
		
		
		
		
		
}
