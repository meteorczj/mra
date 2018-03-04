package com.czj.platform.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czj.platform.dao.AttachmentDao;
import com.czj.platform.dao.CaseDao;
import com.czj.platform.entity.Attachment;
import com.czj.platform.entity.Case;
import com.czj.platform.service.CaseService;

@Service("caseService")
public class CaseServiceImpl implements CaseService {

	@Autowired
	private CaseDao caseDao;

	@Autowired
	private AttachmentDao attachmentDao;

	public CaseDao getCaseDao() {
		return caseDao;
	}

	public void setCaseDao(CaseDao caseDao) {
		this.caseDao = caseDao;
	}

	@Override
	public Case selectCase(Case entity) {
		return this.caseDao.selectCase(entity);
	}

	@Override
	public int insertCase(Case entity) {
		return caseDao.insertCase(entity);
	}

	@Override
	public int deleteCase(Case entity) {
		return caseDao.deleteCase(entity);
	}

	@Override
	public int selectCaseCount(Case entity) {
		return caseDao.selectCaseCount(entity);
	}

	@Override
	public List<Case> selectCaseList(Case entity) {
		return caseDao.selectCaseList(entity);
	}

	@Override
	public int updateCase(Case entity) {
		return caseDao.updateCase(entity);
	}

	@Override
	public int deleteCaseAndAttachments(Case entity) {
		// 删除病历信息
		int result = this.caseDao.deleteCase(entity);

		// 删除数据库附件表信息
		Attachment attach = new Attachment();
		attach.setCase_id(entity.getId());
		List<Attachment> attachList = this.attachmentDao.selectAttachmentList(attach);
		this.attachmentDao.deleteAttachment(attach);

		// 删除文件夹中附件
		if (null != attachList && attachList.size() > 0) {
			for (Attachment attachment : attachList) {
				File file = new File(attachment.getFile_path());
				if (file.isFile() && file.exists()) {
					file.delete();
				}
			}
		}

		return result;
	}

}
