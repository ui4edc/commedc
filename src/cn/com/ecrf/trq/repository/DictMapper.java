package cn.com.ecrf.trq.repository;

import java.util.List;

import cn.com.ecrf.trq.model.dict.DictRow;
import cn.com.ecrf.trq.model.dict.DictSnapshot;

public interface DictMapper {

	List<DictSnapshot> getUnDealDictList();
	List<DictRow> getDictRowList(int id);

}
