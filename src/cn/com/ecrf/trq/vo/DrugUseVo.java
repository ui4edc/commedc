package cn.com.ecrf.trq.vo;

import java.util.Date;
import java.util.List;

/**
 * 用药情况
 * @author user
 *
 */
public class DrugUseVo {
		private int id;
		//观察表编号
		private String no;
		//痰热清用药史
		private int trqyys;
		//不良反应
		private int blfy;
		//不良反应表现
		private String blfybx;
		//批号
		private String ph;
		//用药开始时间，日期
		private String yykssjd;
		//用药开始时间，小时
		private String yykssjh;
		//用药开始时间，分钟
		private String yykssjm;
		//用药结束时间，日期
		private String yyjssjd;
		//用药结束时间，小时
		private String yyjssjh;
		//用药结束时间，分钟
		private String yyjssjm;
		//溶媒类型
		private int rmlx;
		//溶媒其他名称
		private String rmqtmc;
		//溶媒其他百分比
		private String rmqtbfb;
		//配液至给药时间
		private String pyzgysj;
		//配液场所
		private int pycs;
		//给药途径：静脉滴注，静脉泵入， 其他途径名称
		private int gytj;
		//静脉滴注速度
		private String jmdzsd;
		//静脉滴注时间
		private String jmdzsj;
		//静脉泵入速度
		private String jmbrsd;
		//其他途径名称
		private String qttjmc;
		//其他途径速度
		private String qttjsd;
		//其他途径单位
		private String qttjdw;
		//通瓶用药
		private int tpyy;
		//同瓶用药列表
		private List<DrugInstanceObject> tpyylb; 
		//同组用药
		private int tzyy;
		//同组用药间隔液
		private DrugInstanceObject tzyyjgy;
		//是否有其他注射剂
		private int qtzsj;
		private List<DrugInstanceObject> qtzsjlb;
		//配伍禁忌现象,是否
		private int pwjjxx;
		//配伍禁忌现象产生
		private int pwjjxxcs;
		//配伍禁忌现象,颜色
		private String pwjjxxcsys;
		//配伍禁忌现象,其他名称
		private String pwjjcsqtmc;
		//配伍禁忌药品列表
		private List<DrugInstanceObject> pwjjywlb;
		//进食易致敏物质
		private int jsyzawz;
		//进食易致敏物质列表
		private List<CheckBoxVo> jsyzawzlb;
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
		public int getTrqyys() {
			return trqyys;
		}
		public void setTrqyys(int trqyys) {
			this.trqyys = trqyys;
		}
		public int getBlfy() {
			return blfy;
		}
		public void setBlfy(int blfy) {
			this.blfy = blfy;
		}
		public String getBlfybx() {
			return blfybx;
		}
		public void setBlfybx(String blfybx) {
			this.blfybx = blfybx;
		}
		public String getPh() {
			return ph;
		}
		public void setPh(String ph) {
			this.ph = ph;
		}
		public String getYykssjd() {
			return yykssjd;
		}
		public void setYykssjd(String yykssjd) {
			this.yykssjd = yykssjd;
		}
		public String getYykssjh() {
			return yykssjh;
		}
		public void setYykssjh(String yykssjh) {
			this.yykssjh = yykssjh;
		}
		public String getYykssjm() {
			return yykssjm;
		}
		public void setYykssjm(String yykssjm) {
			this.yykssjm = yykssjm;
		}
		public String getYyjssjd() {
			return yyjssjd;
		}
		public void setYyjssjd(String yyjssjd) {
			this.yyjssjd = yyjssjd;
		}
		public String getYyjssjh() {
			return yyjssjh;
		}
		public void setYyjssjh(String yyjssjh) {
			this.yyjssjh = yyjssjh;
		}
		public String getYyjssjm() {
			return yyjssjm;
		}
		public void setYyjssjm(String yyjssjm) {
			this.yyjssjm = yyjssjm;
		}
		public int getRmlx() {
			return rmlx;
		}
		public void setRmlx(int rmlx) {
			this.rmlx = rmlx;
		}
		public String getRmqtmc() {
			return rmqtmc;
		}
		public void setRmqtmc(String rmqtmc) {
			this.rmqtmc = rmqtmc;
		}
		public String getRmqtbfb() {
			return rmqtbfb;
		}
		public void setRmqtbfb(String rmqtbfb) {
			this.rmqtbfb = rmqtbfb;
		}
		public String getPyzgysj() {
			return pyzgysj;
		}
		public void setPyzgysj(String pyzgysj) {
			this.pyzgysj = pyzgysj;
		}
		public int getPycs() {
			return pycs;
		}
		public void setPycs(int pycs) {
			this.pycs = pycs;
		}
		public int getGytj() {
			return gytj;
		}
		public void setGytj(int gytj) {
			this.gytj = gytj;
		}
		public String getJmdzsd() {
			return jmdzsd;
		}
		public void setJmdzsd(String jmdzsd) {
			this.jmdzsd = jmdzsd;
		}
		public String getJmdzsj() {
			return jmdzsj;
		}
		public void setJmdzsj(String jmdzsj) {
			this.jmdzsj = jmdzsj;
		}
		public String getJmbrsd() {
			return jmbrsd;
		}
		public void setJmbrsd(String jmbrsd) {
			this.jmbrsd = jmbrsd;
		}
		public String getQttjmc() {
			return qttjmc;
		}
		public void setQttjmc(String qttjmc) {
			this.qttjmc = qttjmc;
		}
		public String getQttjsd() {
			return qttjsd;
		}
		public void setQttjsd(String qttjsd) {
			this.qttjsd = qttjsd;
		}
		public String getQttjdw() {
			return qttjdw;
		}
		public void setQttjdw(String qttjdw) {
			this.qttjdw = qttjdw;
		}
		public int getTpyy() {
			return tpyy;
		}
		public void setTpyy(int tpyy) {
			this.tpyy = tpyy;
		}
		public List<DrugInstanceObject> getTpyylb() {
			return tpyylb;
		}
		public void setTpyylb(List<DrugInstanceObject> tpyylb) {
			this.tpyylb = tpyylb;
		}
		public int getTzyy() {
			return tzyy;
		}
		public void setTzyy(int tzyy) {
			this.tzyy = tzyy;
		}
		public DrugInstanceObject getTzyyjgy() {
			return tzyyjgy;
		}
		public void setTzyyjgy(DrugInstanceObject tzyyjgy) {
			this.tzyyjgy = tzyyjgy;
		}
		public int getQtzsj() {
			return qtzsj;
		}
		public void setQtzsj(int qtzsj) {
			this.qtzsj = qtzsj;
		}
		public List<DrugInstanceObject> getQtzsjlb() {
			return qtzsjlb;
		}
		public void setQtzsjlb(List<DrugInstanceObject> qtzsjlb) {
			this.qtzsjlb = qtzsjlb;
		}
		public int getPwjjxx() {
			return pwjjxx;
		}
		public void setPwjjxx(int pwjjxx) {
			this.pwjjxx = pwjjxx;
		}
		public int getPwjjxxcs() {
			return pwjjxxcs;
		}
		public void setPwjjxxcs(int pwjjxxcs) {
			this.pwjjxxcs = pwjjxxcs;
		}
		public String getPwjjxxcsys() {
			return pwjjxxcsys;
		}
		public void setPwjjxxcsys(String pwjjxxcsys) {
			this.pwjjxxcsys = pwjjxxcsys;
		}
		public String getPwjjcsqtmc() {
			return pwjjcsqtmc;
		}
		public void setPwjjcsqtmc(String pwjjcsqtmc) {
			this.pwjjcsqtmc = pwjjcsqtmc;
		}
		public List<DrugInstanceObject> getPwjjywlb() {
			return pwjjywlb;
		}
		public void setPwjjywlb(List<DrugInstanceObject> pwjjywlb) {
			this.pwjjywlb = pwjjywlb;
		}
		public int getJsyzawz() {
			return jsyzawz;
		}
		public void setJsyzawz(int jsyzawz) {
			this.jsyzawz = jsyzawz;
		}
		public List<CheckBoxVo> getJsyzawzlb() {
			return jsyzawzlb;
		}
		public void setJsyzawzlb(List<CheckBoxVo> jsyzawzlb) {
			this.jsyzawzlb = jsyzawzlb;
		}
		
		
}
