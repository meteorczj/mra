package com.czj.platform.service;

import java.util.List;

import com.czj.platform.entity.DictData;

public interface DictDataService {
	
	public int selectDictDataCount(DictData entity);
	
	public List<DictData> selectDictDataList(DictData entity);
	
	public DictData selectDictData(DictData entity);
	
	public int insertDictData(DictData entity);
	
	public int updateDictData(DictData entity);
	
	public int deleteDictData(DictData entity);
	
}
