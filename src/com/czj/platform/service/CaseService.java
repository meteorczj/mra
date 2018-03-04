package com.czj.platform.service;

import java.util.List;

import com.czj.platform.entity.Case;

public interface CaseService {
	
	public int selectCaseCount(Case entity);
	
	public List<Case> selectCaseList(Case entity);
	
	public Case selectCase(Case entity);
	
	public int insertCase(Case entity);
	
	public int updateCase(Case entity);
	
	public int deleteCase(Case entity);

	/**
	 * 删除病历及其附件信息
	 * @param entity
	 */
	public int deleteCaseAndAttachments(Case entity);
	
}
