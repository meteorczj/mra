package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.DictData;

public interface DictDataDao {
	
	int selectDictDataCount(DictData entity);
	
	List<DictData> selectDictDataList(DictData entity);
	
	DictData selectDictData(DictData entity);
	
	int insertDictData(DictData entity);
	
	int updateDictData(DictData entity);
	
	int deleteDictData(DictData entity);
	
}
