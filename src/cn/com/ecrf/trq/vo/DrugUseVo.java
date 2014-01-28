package cn.com.ecrf.trq.vo;

import java.util.Date;
import java.util.List;

import cn.com.ecrf.trq.vo.crf.BanDrug;

/**
 * 用药情况
 * @author user
 *
 */
public class DrugUseVo {
		private int id;
		//观察表编号
		private String no;
		
		private String times;
		
		private int history;
		
		private int adr;
		
		private String adrtxt;
		
		private String banColor;
		
		private String bantxt;
		
		private List<BanDrug> banDrug;
		
		private String batchNumber;
		
		private List<BanDrug> bottle;
		
		private String dose;
		
		private String endDate;
		
		private String endH;
		
		private String endM;
		
		private String food;
		
		private String foodtxt;
		private String gSolvent2Dose;
		private int gpSolvent;
		private	String gpSolvent1Dose;
		private String	gpSolvent3Dose;
		private String	gpSolvent3Name;
		private String	gpSolvent3Percent;
		private int	hasBan;
		private int	hasFood;
		private int	hasInjection;
		private List<BanDrug> injection;
		private String prepareTime;
		private boolean prepareTimeUd;
		private int sameBottle;
		private int sameGroup;
		private int solvent;
		private String solventDose;
		private String solventName;
		private String solventPercent;
		private String startDate;
		private String startH;
		private String startM;
		private int way;
		private String way1Speed;
		private String way1Time;
		private String way2Speed;
		private String way3Name;
		private String way3Speed;
		private String way3Unit;
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
		public String getTimes() {
			return times;
		}
		public void setTimes(String times) {
			this.times = times;
		}
		public int getHistory() {
			return history;
		}
		public void setHistory(int history) {
			this.history = history;
		}
		public int getAdr() {
			return adr;
		}
		public void setAdr(int adr) {
			this.adr = adr;
		}

		public String getAdrtxt() {
			return adrtxt;
		}
		public void setAdrtxt(String adrtxt) {
			this.adrtxt = adrtxt;
		}
		public String getBanColor() {
			return banColor;
		}
		public void setBanColor(String banColor) {
			this.banColor = banColor;
		}
		public String getBantxt() {
			return bantxt;
		}
		public void setBantxt(String bantxt) {
			this.bantxt = bantxt;
		}
		public List<BanDrug> getBanDrug() {
			return banDrug;
		}
		public void setBanDrug(List<BanDrug> banDrug) {
			this.banDrug = banDrug;
		}
		public String getBatchNumber() {
			return batchNumber;
		}
		public void setBatchNumber(String batchNumber) {
			this.batchNumber = batchNumber;
		}
		public List<BanDrug> getBottle() {
			return bottle;
		}
		public void setBottle(List<BanDrug> bottle) {
			this.bottle = bottle;
		}
		public String getDose() {
			return dose;
		}
		public void setDose(String dose) {
			this.dose = dose;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getEndH() {
			return endH;
		}
		public void setEndH(String endH) {
			this.endH = endH;
		}
		public String getEndM() {
			return endM;
		}
		public void setEndM(String endM) {
			this.endM = endM;
		}
		public String getFood() {
			return food;
		}
		public void setFood(String food) {
			this.food = food;
		}
		public String getFoodtxt() {
			return foodtxt;
		}
		public void setFoodtxt(String foodtxt) {
			this.foodtxt = foodtxt;
		}
		public String getgSolvent2Dose() {
			return gSolvent2Dose;
		}
		public void setgSolvent2Dose(String gSolvent2Dose) {
			this.gSolvent2Dose = gSolvent2Dose;
		}
		public int getGpSolvent() {
			return gpSolvent;
		}
		public void setGpSolvent(int gpSolvent) {
			this.gpSolvent = gpSolvent;
		}
		public String getGpSolvent1Dose() {
			return gpSolvent1Dose;
		}
		public void setGpSolvent1Dose(String gpSolvent1Dose) {
			this.gpSolvent1Dose = gpSolvent1Dose;
		}
		public String getGpSolvent3Dose() {
			return gpSolvent3Dose;
		}
		public void setGpSolvent3Dose(String gpSolvent3Dose) {
			this.gpSolvent3Dose = gpSolvent3Dose;
		}
		public String getGpSolvent3Name() {
			return gpSolvent3Name;
		}
		public void setGpSolvent3Name(String gpSolvent3Name) {
			this.gpSolvent3Name = gpSolvent3Name;
		}
		public String getGpSolvent3Percent() {
			return gpSolvent3Percent;
		}
		public void setGpSolvent3Percent(String gpSolvent3Percent) {
			this.gpSolvent3Percent = gpSolvent3Percent;
		}
		public int getHasBan() {
			return hasBan;
		}
		public void setHasBan(int hasBan) {
			this.hasBan = hasBan;
		}
		public int getHasFood() {
			return hasFood;
		}
		public void setHasFood(int hasFood) {
			this.hasFood = hasFood;
		}
		public int getHasInjection() {
			return hasInjection;
		}
		public void setHasInjection(int hasInjection) {
			this.hasInjection = hasInjection;
		}
		public List<BanDrug> getInjection() {
			return injection;
		}
		public void setInjection(List<BanDrug> injection) {
			this.injection = injection;
		}
		public String getPrepareTime() {
			return prepareTime;
		}
		public void setPrepareTime(String prepareTime) {
			this.prepareTime = prepareTime;
		}
		public boolean isPrepareTimeUd() {
			return prepareTimeUd;
		}
		public void setPrepareTimeUd(boolean prepareTimeUd) {
			this.prepareTimeUd = prepareTimeUd;
		}
		public int getSameBottle() {
			return sameBottle;
		}
		public void setSameBottle(int sameBottle) {
			this.sameBottle = sameBottle;
		}
		public int getSameGroup() {
			return sameGroup;
		}
		public void setSameGroup(int sameGroup) {
			this.sameGroup = sameGroup;
		}
		public int getSolvent() {
			return solvent;
		}
		public void setSolvent(int solvent) {
			this.solvent = solvent;
		}
		public String getSolventDose() {
			return solventDose;
		}
		public void setSolventDose(String solventDose) {
			this.solventDose = solventDose;
		}
		public String getSolventName() {
			return solventName;
		}
		public void setSolventName(String solventName) {
			this.solventName = solventName;
		}
		public String getSolventPercent() {
			return solventPercent;
		}
		public void setSolventPercent(String solventPercent) {
			this.solventPercent = solventPercent;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getStartH() {
			return startH;
		}
		public void setStartH(String startH) {
			this.startH = startH;
		}
		public String getStartM() {
			return startM;
		}
		public void setStartM(String startM) {
			this.startM = startM;
		}
		public int getWay() {
			return way;
		}
		public void setWay(int way) {
			this.way = way;
		}
		public String getWay1Speed() {
			return way1Speed;
		}
		public void setWay1Speed(String way1Speed) {
			this.way1Speed = way1Speed;
		}
		public String getWay1Time() {
			return way1Time;
		}
		public void setWay1Time(String way1Time) {
			this.way1Time = way1Time;
		}
		public String getWay2Speed() {
			return way2Speed;
		}
		public void setWay2Speed(String way2Speed) {
			this.way2Speed = way2Speed;
		}
		public String getWay3Name() {
			return way3Name;
		}
		public void setWay3Name(String way3Name) {
			this.way3Name = way3Name;
		}
		public String getWay3Speed() {
			return way3Speed;
		}
		public void setWay3Speed(String way3Speed) {
			this.way3Speed = way3Speed;
		}
		public String getWay3Unit() {
			return way3Unit;
		}
		public void setWay3Unit(String way3Unit) {
			this.way3Unit = way3Unit;
		}
		
		
		
		
		
}
