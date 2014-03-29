package cn.com.ecrf.trq.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.PastHistoryCase;
import cn.com.ecrf.trq.model.PatientInfoCase;
import cn.com.ecrf.trq.model.PersonAllergicHistoryCase;
import cn.com.ecrf.trq.repository.CRFMapper;
import cn.com.ecrf.trq.vo.data.ExportVo;

@Service
public class DataService {
	
	@Autowired
	private CRFMapper cRFMapper;

	public List<ExportVo> getExportDataByIds(String id) {
		// TODO Auto-generated method stub
		List<ExportVo> result = new ArrayList<ExportVo>();
		if (StringUtils.isNotBlank("id")){
			String[] idArray = id.split(",");
			for (String str : idArray){
				int key = Integer.parseInt(str);
				ExportVo exportVo = new ExportVo();
				PatientInfoCase patientInfoCase = cRFMapper.getBasicInfo(key);
				String no = patientInfoCase.getNo();
				PastHistoryCase pastHistoryCase = cRFMapper.getPastHistory(key);
				PersonAllergicHistoryCase personAllergicHistoryCase = cRFMapper.getPersonHistory(key);
				exportVo.setPastHistoryCase(pastHistoryCase);
				exportVo.setPatientInfoCase(patientInfoCase);
				exportVo.setPersonAllergicHistoryCase(personAllergicHistoryCase);
				result.add(exportVo);
			}
		}
		return result;
	}

}
