package com.czj.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czj.platform.dao.DictDataDao;
import com.czj.platform.entity.DictData;
import com.czj.platform.service.DictDataService;

@Service("dictDataService")
public class DictDataServiceImpl implements DictDataService{
	
	@Autowired
	private DictDataDao dictDataDao;
	
	public DictDataDao getDictDataDao() {
		return dictDataDao;
	}

	public void setDictDataDao(DictDataDao dictDataDao) {
		this.dictDataDao = dictDataDao;
	}

	@Override
	public DictData selectDictData(DictData entity) {
		return this.dictDataDao.selectDictData(entity);
	}

	@Override
	public int insertDictData(DictData entity) {
		return  dictDataDao.insertDictData(entity);
	}

	@Override
	public int deleteDictData(DictData entity) {
		return dictDataDao.deleteDictData(entity);
	}
	
	@Override
	public int selectDictDataCount(DictData entity) {
		return dictDataDao.selectDictDataCount(entity);
	}

	@Override
	public List<DictData> selectDictDataList(DictData entity) {
		return dictDataDao.selectDictDataList(entity);
	}

	@Override
	public int updateDictData(DictData entity) {
		return dictDataDao.updateDictData(entity);
	}

}
