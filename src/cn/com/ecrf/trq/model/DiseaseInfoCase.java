package cn.com.ecrf.trq.model;

import java.util.Map;

/**
 * 病证情况
 * @author user
 *
 */
public class DiseaseInfoCase {
	/**
	 * 疾病诊断
	 */
	//观察表编号
	private String blbh;
	//内科疾病：是，否
	private String nkjbyn;
	//内科疾病名称:上呼吸道感染, 急性支气管炎
	private String nkjbmc;
	//内科疾病分级1:儿童，成人
	private String nkjbfj1;
	//内科疾病分级2:轻度，重度，轻、中症，重症
	private String nkjbfj2;
	//外科疾病:是，否
	private String wkjbyn;
	//外科疾病名称
	private String wkjbmc;
	//传染病:是，否
	private String crbyn;
	//其他疾病：是，否
	private String qtjbyn;
	//其他疾病名称
	private String qtjbmc;
	//第一诊断(编号)
	private String dyzdcd;
	//中医诊断名称
	private String zyzdmc;
	
	/**
	 * 个人史
	 */
	//吸烟史:是，否
	private String xysyn;
	//饮酒史:是，否
	private String yjsyn;
	
	/**
	 * 过敏史
	 */
	//食物过敏史:是，否
	private String swgmsyn;
	//食物过敏史列表:key-过敏物质，value-过敏表现
	private Map<String, String> swgmslb;
	//药物过敏史:是，否
	private String ywgmsyn;
	//抗生素类-药物过敏史
	private Map<String, String> ksslywgmslb;
	//中药注射剂-药物过敏史
	private Map<String, String> zyzsjywgmslb;
	//其他药物-药物过敏史
	private Map<String, String> qtywgmslb;
	//其他物质：是，否
	private String qtwzyn;
	//其他物质过敏史
	private Map<String, String> qtwzgmslb;
	
	
}
