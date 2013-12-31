package cn.com.ecrf.trq.model;

public class PastHistoryObject {
	/**
	 * 既往史类型:常见疾病，过敏性疾病史
	 */
	private String mhcat;
	//疾病名称
	private String mhterm;
	//疾病字典衍生名称
	private String mhdecode;
	//预设既往病史：Y,N
	private String mhpresp;
	//既往史是否发生：Y,N. 对应checkbox-true,false
	private String mhoccur;
	//既往史补充字段1，如冠心病，高血压
	private String mhsuppterm1;
	//既往史补充字段1,如X级，X型
	private String mhsuppterm2;
	
	
}
