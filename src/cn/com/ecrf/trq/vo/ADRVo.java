package cn.com.ecrf.trq.vo;

import java.util.List;

/**
 * ADR Vo
 * @author user
 *
 */
public class ADRVo {
	private int id;
	//编号
	private String no;
	//报告类型
	private int reportType;
	//该患者是否采血
	private int blood;
	//患者姓名
	private String name;
	//性别
	private int sex;
	//出生日期
	private String birthday;
	//民族
	private String ethic;
	//体重
	private String weight;
	//联系方式
	private String contact;
	//原患疾病
	private String yhjb;
	//病历号/门诊号
	private String patientId;
	//既往药品不良反应/事件
	private int jwypadr;
	//既往药品不良反应/事件的名称
	private String jwypadrmc;
	//家族药品不良反应/事件
	private int jzypadr;
	//家族药品不良反应/事件名称
	private String jzypadrmc;
	//家族关系
	private int jzgx;
	//相关重要信息
	private List<CheckBoxVo> xgzyxxlb;
	//怀疑药品列表
	private List<ADRDrugObject> hyyplb;
	//并用药品列表
	private List<ADRDrugObject> byyplb;
	//不良反应/事件名称
	private List<SubCheckBoxVo> adrlb;
	//不良反应/事件发生时间, 日期
	private String adrtimed;
	//不良反应/事件发生时间, 小时
	private String adrtimeh;
	//不良反应/事件发生时间, 分钟
	private String adrtimem;
	//不良反应/事件过程描述（包括症状、体征、临床检验等）
	private String adrProgressDesc;
	//不良反应/事件处理情况
	private int adrDeal;
	//减少剂量剂量
	private String jsjljl;
	//对症支持治疗其他名称
	private String dzzczlqtmc;
	//不良反应/事件处理情况其他名称
	private String adrDealqtmc;
	//不良反应/事件处理情况备注
	private String adrDealComment;
	//不良反应/事件的结果
	private int adrResult;
	//不良反应/事件的结果，描述：表现 或直接死因
	private String adrResultDesc;
	//死亡时间
	private String deathDate;
	//停药或减量后，反应/事件是否消失或减轻
	private int reduceResult;
	//再次使用可疑药品后是否再次出现同样反应/事件
	private int reuseResult;
	//关联性评价
	private int relativeEval;
	//报告人信息,职业
	private int reportCareer;
	//报告人信息,职业其他
	private String reportCareerOther;
	//报告人信息,电子邮箱
	private String reportEmail;
	//报告日期
	private String reportDate;
	//备注
	private String comment;
	
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
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEthic() {
		return ethic;
	}
	public void setEthic(String ethic) {
		this.ethic = ethic;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getYhjb() {
		return yhjb;
	}
	public void setYhjb(String yhjb) {
		this.yhjb = yhjb;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public int getJwypadr() {
		return jwypadr;
	}
	public void setJwypadr(int jwypadr) {
		this.jwypadr = jwypadr;
	}
	public String getJwypadrmc() {
		return jwypadrmc;
	}
	public void setJwypadrmc(String jwypadrmc) {
		this.jwypadrmc = jwypadrmc;
	}
	public int getJzypadr() {
		return jzypadr;
	}
	public void setJzypadr(int jzypadr) {
		this.jzypadr = jzypadr;
	}
	public String getJzypadrmc() {
		return jzypadrmc;
	}
	public void setJzypadrmc(String jzypadrmc) {
		this.jzypadrmc = jzypadrmc;
	}
	public int getJzgx() {
		return jzgx;
	}
	public void setJzgx(int jzgx) {
		this.jzgx = jzgx;
	}
	public List<CheckBoxVo> getXgzyxxlb() {
		return xgzyxxlb;
	}
	public void setXgzyxxlb(List<CheckBoxVo> xgzyxxlb) {
		this.xgzyxxlb = xgzyxxlb;
	}
	public List<ADRDrugObject> getHyyplb() {
		return hyyplb;
	}
	public void setHyyplb(List<ADRDrugObject> hyyplb) {
		this.hyyplb = hyyplb;
	}
	public List<ADRDrugObject> getByyplb() {
		return byyplb;
	}
	public void setByyplb(List<ADRDrugObject> byyplb) {
		this.byyplb = byyplb;
	}
	public List<SubCheckBoxVo> getAdrlb() {
		return adrlb;
	}
	public void setAdrlb(List<SubCheckBoxVo> adrlb) {
		this.adrlb = adrlb;
	}
	public String getAdrtimed() {
		return adrtimed;
	}
	public void setAdrtimed(String adrtimed) {
		this.adrtimed = adrtimed;
	}
	public String getAdrtimeh() {
		return adrtimeh;
	}
	public void setAdrtimeh(String adrtimeh) {
		this.adrtimeh = adrtimeh;
	}
	public String getAdrtimem() {
		return adrtimem;
	}
	public void setAdrtimem(String adrtimem) {
		this.adrtimem = adrtimem;
	}
	public String getAdrProgressDesc() {
		return adrProgressDesc;
	}
	public void setAdrProgressDesc(String adrProgressDesc) {
		this.adrProgressDesc = adrProgressDesc;
	}
	public int getAdrDeal() {
		return adrDeal;
	}
	public void setAdrDeal(int adrDeal) {
		this.adrDeal = adrDeal;
	}
	public String getJsjljl() {
		return jsjljl;
	}
	public void setJsjljl(String jsjljl) {
		this.jsjljl = jsjljl;
	}
	public String getDzzczlqtmc() {
		return dzzczlqtmc;
	}
	public void setDzzczlqtmc(String dzzczlqtmc) {
		this.dzzczlqtmc = dzzczlqtmc;
	}
	public String getAdrDealqtmc() {
		return adrDealqtmc;
	}
	public void setAdrDealqtmc(String adrDealqtmc) {
		this.adrDealqtmc = adrDealqtmc;
	}
	public String getAdrDealComment() {
		return adrDealComment;
	}
	public void setAdrDealComment(String adrDealComment) {
		this.adrDealComment = adrDealComment;
	}
	public int getAdrResult() {
		return adrResult;
	}
	public void setAdrResult(int adrResult) {
		this.adrResult = adrResult;
	}
	public String getAdrResultDesc() {
		return adrResultDesc;
	}
	public void setAdrResultDesc(String adrResultDesc) {
		this.adrResultDesc = adrResultDesc;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public int getReduceResult() {
		return reduceResult;
	}
	public void setReduceResult(int reduceResult) {
		this.reduceResult = reduceResult;
	}
	public int getReuseResult() {
		return reuseResult;
	}
	public void setReuseResult(int reuseResult) {
		this.reuseResult = reuseResult;
	}
	public int getRelativeEval() {
		return relativeEval;
	}
	public void setRelativeEval(int relativeEval) {
		this.relativeEval = relativeEval;
	}
	public int getReportCareer() {
		return reportCareer;
	}
	public void setReportCareer(int reportCareer) {
		this.reportCareer = reportCareer;
	}
	public String getReportCareerOther() {
		return reportCareerOther;
	}
	public void setReportCareerOther(String reportCareerOther) {
		this.reportCareerOther = reportCareerOther;
	}
	public String getReportEmail() {
		return reportEmail;
	}
	public void setReportEmail(String reportEmail) {
		this.reportEmail = reportEmail;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
	
	
}
