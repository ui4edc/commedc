package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;

public interface DictMapper {

	List<DictSnapshot> getUnDealDictList();
	List<DictRow> getDictRowList(int id);
	List<DictRow> getBasicList(String name);
	void addItemToBase(Map<String, Object> condition);

}
