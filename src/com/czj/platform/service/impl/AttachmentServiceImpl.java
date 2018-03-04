package com.czj.platform.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.czj.platform.dao.AttachmentDao;
import com.czj.platform.entity.Attachment;
import com.czj.platform.service.AttachmentService;

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	public AttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	@Override
	public Attachment selectAttachment(Attachment entity) {
		return this.attachmentDao.selectAttachment(entity);
	}

	@Override
	public int insertAttachment(Attachment entity) {
		return attachmentDao.insertAttachment(entity);
	}

	@Override
	public int deleteAttachment(Attachment entity) {
		return attachmentDao.deleteAttachment(entity);
	}

	@Override
	public int selectAttachmentCount(Attachment entity) {
		return attachmentDao.selectAttachmentCount(entity);
	}

	@Override
	public List<Attachment> selectAttachmentList(Attachment entity) {
		return attachmentDao.selectAttachmentList(entity);
	}

	@Override
	public int updateAttachment(Attachment entity) {
		return attachmentDao.updateAttachment(entity);
	}

	@Override
	public int deleteAttachmentAndFile(Attachment entity) {
		// 1、删除数据库中附件信息
		int result = this.attachmentDao.deleteAttachment(entity);

		// 2、删除文件夹中附件
		if (null != entity && StringUtils.isNotBlank(entity.getFile_path())) {
			File file = new File(entity.getFile_path());
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}

		return result;
	}

	@Override
	public boolean uploadFileAndSaveAttachment(Attachment entity, MultipartFile sourceFile, File targetFile) {
		try {
			// 判断当前文件是否存在，不存在即保存到数据库中
			if (!targetFile.exists()) {
				this.attachmentDao.insertAttachment(entity);
			}

			FileCopyUtils.copy(sourceFile.getBytes(), targetFile);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
