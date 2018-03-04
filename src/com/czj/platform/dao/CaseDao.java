package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.Case;

public interface CaseDao {
	
	int selectCaseCount(Case entity);
	
	List<Case> selectCaseList(Case entity);
	
	Case selectCase(Case entity);
	
	int insertCase(Case entity);
	
	int updateCase(Case entity);
	
	int deleteCase(Case entity);
	
}
