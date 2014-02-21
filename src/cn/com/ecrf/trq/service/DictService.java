package cn.com.ecrf.trq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;
import cn.com.ecrf.trq.repository.DictMapper;

@Service
public class DictService {
	@Autowired
	private DictMapper dictMapper;

	public List<DictSnapshot> getDictSnapshot() {
		// TODO Auto-generated method stub
		List<DictSnapshot> dictSnapshots = dictMapper.getUnDealDictList();
/*		if (dictSnapshots != null){
			for (DictSnapshot dictSnapshot : dictSnapshots){
				if (dictSnapshot.getUntreated() > 0)
					dictSnapshot.setName(dictSnapshot.getName()+"("+dictSnapshot.getUntreated()+")个未处理");
			}
		}*/
		return dictSnapshots;
	}

	public List<DictRow> getItemDict(int id) {
		// TODO Auto-generated method stub
		List<DictRow> list = dictMapper.getDictRowList(id);
		return list;
	}

	public List<DictRow> getBaseDict(String name) {
		// TODO Auto-generated method stub
		List<DictRow> list = dictMapper.getBasicList(name);
		return list;
	}

	public void addItemToBase(int selectedItemId, int baseItemId) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("baseItemId", baseItemId);
		condition.put("selectedItemId", selectedItemId);
		dictMapper.addItemToBase(condition);
	}

	
	

}
