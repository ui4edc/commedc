package cn.com.ecrf.trq.model;

import java.util.Date;
/**
 * 包含患者基本情况和痰热清注射液用药史
 * @author user
 *
 */
public class PatientInfoCase {
	//研究单位
	private String yjdw;
	//研究单位编号
	private String yjdwcd;
	//患者病例号
	private String hzblh;
	//首次填表时间
	private String sctbsj;
	//观察表编号
	private String blbh;
	//审批状态
	private String lockStatus;
	//患者姓名
	private String name;
	//姓名缩写
	private String abbr;
	//出生年月
	private Date birthday;
	//年龄
	private String age;
	//体重
	private double weight;
	//体重单位:kg
	private String weightu;
	//性别
	private String sex;
	//怀孕史：妊娠期，怀孕期，无
	private String hys;
	//身高
	private int height;
	//身高单位:cm
	private int heightu;
	//用药科室
	private String yyks;
	//入院日期
	private Date ryrq;
	//出院日期
	private Date cyrq;
	//医疗费用方式
	private String ylfyfs;
	
	//痰热清注射用药史:是，否
	private String trqzsyysyn;
	//是否有不良反应:是，否
	private String blfyyn;
	//不良反应表现
	private String blfybx;
	
	
	
}
