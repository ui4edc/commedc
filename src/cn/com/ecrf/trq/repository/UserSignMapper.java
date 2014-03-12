package cn.com.ecrf.trq.repository;

import cn.com.ecrf.trq.model.CRFUserSign;

public interface UserSignMapper {

	public void insertUserSign(CRFUserSign userSign);
	public void updateUserSign(CRFUserSign userSign);
	public void updateUserSignDate(CRFUserSign userSign);
	public void deleteUserSign(String no);
	public CRFUserSign getUserSignByNo(String no);
}
