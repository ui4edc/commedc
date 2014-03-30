package cn.com.ecrf.trq.repository;

import cn.com.ecrf.trq.model.UserBehaviorLog;

public interface AuditLogMapper {

	public void insertUserBehaviorLog(UserBehaviorLog log);

}
