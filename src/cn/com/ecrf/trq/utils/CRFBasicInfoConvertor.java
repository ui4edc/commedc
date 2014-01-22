package cn.com.ecrf.trq.utils;

import java.text.SimpleDateFormat;

import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.vo.PatientInfoVo;

public class CRFBasicInfoConvertor implements IConvertor<PatientInfoCase, PatientInfoVo>{

	@Override
	public PatientInfoVo convertFromModelToView(PatientInfoCase model) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PatientInfoVo view = new PatientInfoVo();
		view.setAbbr(model.getAbbr());
		view.setAge(""+model.getAge());
		view.setBirthday(sdf.format(model.getBirthday()));
	
		//view.setEthic(ethic);
		return view;
	}

	@Override
	public PatientInfoCase convertFromViewToModel(PatientInfoVo view) {
		// TODO Auto-generated method stub
		PatientInfoCase model = new PatientInfoCase();
		return model;
	}

}
