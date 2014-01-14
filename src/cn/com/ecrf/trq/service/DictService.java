package cn.com.ecrf.trq.service;

import java.util.List;

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
		if (dictSnapshots != null){
			for (DictSnapshot dictSnapshot : dictSnapshots){
				if (dictSnapshot.getUntreated() > 0)
					dictSnapshot.setName(dictSnapshot.getName()+"("+dictSnapshot.getUntreated()+")个未处理");
			}
		}
		return null;
	}

	public List<DictRow> getItemDict(String dictName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DictRow> getBaseDict(String dictName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addItemToBase(int selectedItemId, int baseItemId) {
		// TODO Auto-generated method stub
		
	}

	
	

}
