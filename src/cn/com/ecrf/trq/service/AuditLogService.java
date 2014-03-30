package cn.com.ecrf.trq.service;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.ecrf.trq.model.UserBehaviorLog;
import cn.com.ecrf.trq.repository.AuditLogMapper;
import cn.com.ecrf.trq.repository.CRFMapper;

public class AuditLogService {
	@Autowired
	private AuditLogMapper auditLogMapper;
	
	public void auditUserBehavior(UserBehaviorLog log){
		auditLogMapper.insertUserBehaviorLog(log);
	}
}
