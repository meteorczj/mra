package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.Attachment;

public interface AttachmentDao {
	
	int selectAttachmentCount(Attachment entity);
	
	List<Attachment> selectAttachmentList(Attachment entity);
	
	Attachment selectAttachment(Attachment entity);
	
	int insertAttachment(Attachment entity);
	
	int updateAttachment(Attachment entity);
	
	int deleteAttachment(Attachment entity);
	
}
