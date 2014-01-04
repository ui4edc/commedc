package cn.com.ecrf.trq.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.vo.ListConditionVo;
import cn.com.ecrf.trq.vo.ListNotifyVo;
import cn.com.ecrf.trq.vo.ListReturnVo;

@Service
public class CRFService {

	
	public ListNotifyVo getNotifyInfo() {
		// TODO Auto-generated method stub
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		
		return null;
	}

	public List<ListReturnVo> getCRFList(ListConditionVo condition) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
