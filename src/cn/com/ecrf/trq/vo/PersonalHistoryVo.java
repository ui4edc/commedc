package cn.com.ecrf.trq.vo;

import java.util.List;

public class PersonalHistoryVo {
	//id
	private int id;
	//观察表编号
	private String no;
	//吸烟史
	private int smoke;
	//饮酒史
	private int drink;
	//食物过敏史
	private int swgms;
	//食物疑似过敏物
	private String swysgmw;
	//食物过敏表现
	private List<CheckBoxVo> swgmbxlb;
	//药物过敏史
	private int ywgms;
	//抗生素类是否
	private int kssl;
	//抗生素类过敏物
	private String ksslgmw;
	//抗生素类过敏表现
	private List<CheckBoxVo> ksslgmbxlb;
	//中药注射剂
	private int zyzsj;
	//中药注射剂过敏物
	private String zyzsjgmw;
	//中药注射剂过敏表现
	private List<CheckBoxVo> zyzsjgmbxlb;
	//其他药物过敏物
	private int qtyw;
	//其他药物过敏物
	private String qtywgmw;
	//其他药物过敏表现
	private List<CheckBoxVo> qtywgmbxlb;
	//其他物质
	private int qtwz;
	//其他物质过敏物
	private String qtwzgmw;
	//其他物质过敏表现
	private List<CheckBoxVo> qtwzgmbxlb;
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
	public int getSmoke() {
		return smoke;
	}
	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}
	public int getDrink() {
		return drink;
	}
	public void setDrink(int drink) {
		this.drink = drink;
	}
	public int getSwgms() {
		return swgms;
	}
	public void setSwgms(int swgms) {
		this.swgms = swgms;
	}
	public String getSwysgmw() {
		return swysgmw;
	}
	public void setSwysgmw(String swysgmw) {
		this.swysgmw = swysgmw;
	}
	public List<CheckBoxVo> getSwgmbxlb() {
		return swgmbxlb;
	}
	public void setSwgmbxlb(List<CheckBoxVo> swgmbxlb) {
		this.swgmbxlb = swgmbxlb;
	}
	public int getYwgms() {
		return ywgms;
	}
	public void setYwgms(int ywgms) {
		this.ywgms = ywgms;
	}
	public int getKssl() {
		return kssl;
	}
	public void setKssl(int kssl) {
		this.kssl = kssl;
	}
	public String getKsslgmw() {
		return ksslgmw;
	}
	public void setKsslgmw(String ksslgmw) {
		this.ksslgmw = ksslgmw;
	}
	public List<CheckBoxVo> getKsslgmbxlb() {
		return ksslgmbxlb;
	}
	public void setKsslgmbxlb(List<CheckBoxVo> ksslgmbxlb) {
		this.ksslgmbxlb = ksslgmbxlb;
	}
	public int getZyzsj() {
		return zyzsj;
	}
	public void setZyzsj(int zyzsj) {
		this.zyzsj = zyzsj;
	}
	public String getZyzsjgmw() {
		return zyzsjgmw;
	}
	public void setZyzsjgmw(String zyzsjgmw) {
		this.zyzsjgmw = zyzsjgmw;
	}
	public List<CheckBoxVo> getZyzsjgmbxlb() {
		return zyzsjgmbxlb;
	}
	public void setZyzsjgmbxlb(List<CheckBoxVo> zyzsjgmbxlb) {
		this.zyzsjgmbxlb = zyzsjgmbxlb;
	}
	public int getQtyw() {
		return qtyw;
	}
	public void setQtyw(int qtyw) {
		this.qtyw = qtyw;
	}
	public String getQtywgmw() {
		return qtywgmw;
	}
	public void setQtywgmw(String qtywgmw) {
		this.qtywgmw = qtywgmw;
	}
	public List<CheckBoxVo> getQtywgmbxlb() {
		return qtywgmbxlb;
	}
	public void setQtywgmbxlb(List<CheckBoxVo> qtywgmbxlb) {
		this.qtywgmbxlb = qtywgmbxlb;
	}
	public int getQtwz() {
		return qtwz;
	}
	public void setQtwz(int qtwz) {
		this.qtwz = qtwz;
	}
	public String getQtwzgmw() {
		return qtwzgmw;
	}
	public void setQtwzgmw(String qtwzgmw) {
		this.qtwzgmw = qtwzgmw;
	}
	public List<CheckBoxVo> getQtwzgmbxlb() {
		return qtwzgmbxlb;
	}
	public void setQtwzgmbxlb(List<CheckBoxVo> qtwzgmbxlb) {
		this.qtwzgmbxlb = qtwzgmbxlb;
	}
	
	
}
