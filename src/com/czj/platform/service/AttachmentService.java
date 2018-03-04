package com.czj.platform.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.czj.platform.entity.Attachment;

public interface AttachmentService {
	
	public int selectAttachmentCount(Attachment entity);
	
	public List<Attachment> selectAttachmentList(Attachment entity);
	
	public Attachment selectAttachment(Attachment entity);
	
	public int insertAttachment(Attachment entity);
	
	public int updateAttachment(Attachment entity);
	
	public int deleteAttachment(Attachment entity);
	
	/**
	 * 删除数据库中附件信息和附件本身
	 * @param entity
	 * @return
	 */
	public int deleteAttachmentAndFile(Attachment entity);
	
	/**
	 * 上传文件到指定目录，并保存附件信息到数据库中
	 * @param sourceFile
	 * @param targetFile
	 * @return
	 */
	public boolean uploadFileAndSaveAttachment(Attachment entity, MultipartFile sourceFile, File targetFile);
	
}
