package cn.com.ecrf.trq.model;

import java.util.Date;

/**
 * 包含痰热清注射液用药情况
 * @author user
 *
 */
public class DrugUseCase {
	//观察表编号
	private String blbh;
	//每次用药剂量
	private String exdose;
	//每次用药剂量单位
	private String exdoseu;
	//批号
	private String exlot;
	//治疗开始时间=用药开始时间
	private Date exstdtc;
	//治疗结束时间=用药结束时间
	private Date exendtc;
	
}
