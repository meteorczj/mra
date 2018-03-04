package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.DictType;

public interface DictTypeDao {
	
	int selectDictTypeCount(DictType entity);
	
	List<DictType> selectDictTypeList(DictType entity);
	
	DictType selectDictType(DictType entity);
	
	int insertDictType(DictType entity);
	
	int updateDictType(DictType entity);
	
	int deleteDictType(DictType entity);
	
}
