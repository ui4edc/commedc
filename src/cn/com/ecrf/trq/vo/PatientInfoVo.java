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
		//性别
		private int sex;
		//民族
		private int ethic;
		//民族其他
		private String ethictxt;
		//怀孕史：妊娠期，怀孕期，无
		private int hys;
		//身高
		private String height;
		//用药科室
		private List<CheckBoxVo> yyks;
		//入院日期
		private String ryrq;
		//出院日期
		private String cyrq;
		//医疗费用方式
		private int ylfyfs;
		//医疗费用方式
		private String ylfyfstxt;
		
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
		public int getYlfyfs() {
			return ylfyfs;
		}
		public void setYlfyfs(int ylfyfs) {
			this.ylfyfs = ylfyfs;
		}
		public String getYlfyfstxt() {
			return ylfyfstxt;
		}
		public void setYlfyfstxt(String ylfyfstxt) {
			this.ylfyfstxt = ylfyfstxt;
		}
		public int getEthic() {
			return ethic;
		}
		public void setEthic(int ethic) {
			this.ethic = ethic;
		}
		public String getEthictxt() {
			return ethictxt;
		}
		public void setEthictxt(String ethictxt) {
			this.ethictxt = ethictxt;
		}
		
		
		
		
}
