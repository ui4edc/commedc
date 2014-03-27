package cn.com.ecrf.trq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;
import cn.com.ecrf.trq.repository.DictMapper;
import cn.com.ecrf.trq.utils.PinyinUtils;
import cn.com.ecrf.trq.utils.StringUtils;

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

	public List<DictRow> getBaseDict(String name, int id) {
		// TODO Auto-generated method stub
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		if (StringUtils.isNotBlank(name)){
			try {
				condition.put("keyword", PinyinUtils.getFirstHanyuPinyin(name)+"%");
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<DictRow> list = dictMapper.getBasicList(condition);
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
