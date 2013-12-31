package cn.com.ecrf.trq.model;

import java.util.List;

/**
 * 包含既往史
 * @author user
 *
 */
public class PastHistoryCase {
	//常见疾病:是，否
	private String cjjbyn;
	//常见疾病列表
	private List<PastHistoryObject> cjjblb;
	//过敏性疾病史:是，否
	private String gmxjbsyn;
	//过敏性疾病史
	private List<PastHistoryObject> gmxjbslb;
	
}
