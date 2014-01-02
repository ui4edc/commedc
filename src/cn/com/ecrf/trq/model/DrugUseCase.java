package cn.com.ecrf.trq.model;

import java.util.Date;
import java.util.List;

import cn.com.ecrf.trq.vo.SameBottleObject;
import cn.com.ecrf.trq.vo.SameGroupObject;

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
	//溶媒名称 = 治疗赋形剂， 葡萄糖注射液， 氯化钠注射液
	private String extrtv;
	//溶媒 剂量= 治疗赋形剂剂量 ， 500
	private String exvamt;
	//治疗赋形剂剂量单位, ml
	private String exvamtu;
	//治疗赋形剂百分比, 0.9, 5
	private String exsuppvamtpt;
	//配药场所
	private String exsuppsite;
	//配药至给药时间
	private int exsuppdur;
	//配药至给药时间单位，分
	private String exsuppduru;
	//给药途径：静脉滴注，静脉泵入， 其他途径名称
	private String exroute;
	//给药途径滴速
	private int exsupproutespd;
	//给药途径滴速单位
	private String exsupproutespdu;
	//给药途径持续时间
	private int exsupproutedur;
	//给药途径持续时间单位, 分
	private String exsupprouteduru;
	//是否同瓶用药
	private String exsuppbottomyn;
	//同瓶用药列表
	private List<SameBottleObject> exsuppbottomlb; 
	//是否同组用药
	private String exsuppgroupyn;
	//同组用药列表
	private List<SameGroupObject> exsuppgrouplb; 
	//是否有其他注射剂
	private String exsuppqtzsjyn;
	//其他注射剂列表
	private List<SameBottleObject> exsuppqtzsjlb; 
	//是否有配伍禁忌现象
	private String exsupppwjjxxyn;
	//配伍禁忌现象名称
	private String exsuppwjjxxterm;
	//配伍禁忌现象颜色
	private String exsuppwjjxxcolor;
	//配伍禁忌药品列表
	private List<SameBottleObject> exsupppwjjyplb;
	//静滴前后24小时内是否进食易致敏物质
	private String exsuppjsyzmwzyn;
	//静滴前后24小时内进食易致敏物质名称
	private List<String> exsuppjsyzmwzlb;
	
	
	
	
	
	
	
}
