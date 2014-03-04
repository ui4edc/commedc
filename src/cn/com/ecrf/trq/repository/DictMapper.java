package cn.com.ecrf.trq.repository;

import java.util.List;
import java.util.Map;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;
import cn.com.ecrf.trq.model.dict.StaticDict;

public interface DictMapper {

	List<DictSnapshot> getUnDealDictList();
	List<DictRow> getDictRowList(int id);
	List<DictRow> getBasicList(String name);
	void addItemToBase(Map<String, Object> condition);
	List<StaticDict> getStaticDict(StaticDict staticDict);
	void insertDictRow(DictRow dictRow);
	List<DictRow> getDictRow(DictRow dictRow);

}
