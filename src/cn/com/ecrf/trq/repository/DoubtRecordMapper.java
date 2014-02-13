package cn.com.ecrf.trq.repository;

import java.util.List;

import cn.com.ecrf.trq.model.DoubtRecord;
import cn.com.ecrf.trq.model.FieldDict;

public interface DoubtRecordMapper {

	void insertDoubtRecord(DoubtRecord doubtRecord);

	List<FieldDict> getCRFFieldDict(int menuId);

	List<DoubtRecord> getDoubtRecord(DoubtRecord doubtRecord);

	void saveDoubtRecord(DoubtRecord doubtRecord);

}
