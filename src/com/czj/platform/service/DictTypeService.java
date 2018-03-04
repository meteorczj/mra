package com.czj.platform.service;

import java.util.List;

import com.czj.platform.entity.DictType;

public interface DictTypeService {
	
	public int selectDictTypeCount(DictType entity);
	
	public List<DictType> selectDictTypeList(DictType entity);
	
	public DictType selectDictType(DictType entity);
	
	public int insertDictType(DictType entity);
	
	public int updateDictType(DictType entity);
	
	public int deleteDictType(DictType entity);
	
}
