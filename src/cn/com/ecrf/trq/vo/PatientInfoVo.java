package cn.com.ecrf.trq.vo;

import java.util.Date;
import java.util.List;

public class PatientInfoVo {
		//id
		private int id;
		//观察表编号
		private String no;
		//患者姓名
		//private String name;
		//姓名缩写
		//private String abbr;
		//出生年月
		private String birthday; //YYYY-MM-DD
		//年龄
		private int age;

		//性别
		private int sex;
		//民族
		private int ethic;
		//民族其他
		//private String ethictxt;
		//怀孕史：妊娠期，怀孕期，无
		private int hys;
		//体重
		private String weight;
		private boolean weightud;
		//身高
		private String height;
		private boolean heightud;
		//用药科室
		private int yyks;
		//
		private String yykstxt;
		//入院日期
		private String indate;
		//出院日期
		private String outdate;
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
		
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
			this.weight = weight;
		}
		public boolean isWeightud() {
			return weightud;
		}
		public void setWeightud(boolean weightud) {
			this.weightud = weightud;
		}
		public int getSex() {
			return sex;
		}
		public void setSex(int sex) {
			this.sex = sex;
		}
		public int getEthic() {
			return ethic;
		}
		public void setEthic(int ethic) {
			this.ethic = ethic;
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
		public boolean isHeightud() {
			return heightud;
		}
		public void setHeightud(boolean heightud) {
			this.heightud = heightud;
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
		public String getIndate() {
			return indate;
		}
		public void setIndate(String indate) {
			this.indate = indate;
		}
		public String getOutdate() {
			return outdate;
		}
		public void setOutdate(String outdate) {
			this.outdate = outdate;
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
		
		
		
		
		
		
		
}
