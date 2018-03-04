package com.czj.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czj.platform.dao.DictTypeDao;
import com.czj.platform.entity.DictType;
import com.czj.platform.service.DictTypeService;

@Service("dictTypeService")
public class DictTypeServiceImpl implements DictTypeService{
	
	@Autowired
	private DictTypeDao dictTypeDao;
	
	public DictTypeDao getDictTypeDao() {
		return dictTypeDao;
	}

	public void setDictTypeDao(DictTypeDao dictTypeDao) {
		this.dictTypeDao = dictTypeDao;
	}

	@Override
	public DictType selectDictType(DictType entity) {
		return this.dictTypeDao.selectDictType(entity);
	}

	@Override
	public int insertDictType(DictType entity) {
		return  dictTypeDao.insertDictType(entity);
	}

	@Override
	public int deleteDictType(DictType entity) {
		return dictTypeDao.deleteDictType(entity);
	}
	
	@Override
	public int selectDictTypeCount(DictType entity) {
		return dictTypeDao.selectDictTypeCount(entity);
	}

	@Override
	public List<DictType> selectDictTypeList(DictType entity) {
		return dictTypeDao.selectDictTypeList(entity);
	}

	@Override
	public int updateDictType(DictType entity) {
		return dictTypeDao.updateDictType(entity);
	}

}
