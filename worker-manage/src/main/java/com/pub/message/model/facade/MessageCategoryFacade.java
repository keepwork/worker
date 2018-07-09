package com.pub.message.model.facade;

import java.util.List;

import com.pub.message.model.bpo.MessageCategoryBPO;
import com.pub.message.model.dto.MessageCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MessageCategoryDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MessageCategoryFacade { 
	
	private MessageCategoryBPO myMessageCategoryBPO;

	public boolean save(MessageCategoryDTO dto) throws AppException {
		return myMessageCategoryBPO.saveTX(dto);
	}

	
	public void update(MessageCategoryDTO dto) throws AppException {
		myMessageCategoryBPO.updateTX(dto);
	}

	public void saveOrUpdate(MessageCategoryDTO dto) throws AppException {
		myMessageCategoryBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMessageCategoryBPO.deleteTX(ids);
	}

	
	public MessageCategoryDTO get(java.lang.String code) throws AppException {
		return myMessageCategoryBPO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMessageCategoryBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMessageCategoryBPO.listByIds(ids);
	}
	
	public List listByParent(String parentCode) throws AppException {
		return myMessageCategoryBPO.listByParent(parentCode);
	}


	public MessageCategoryBPO getMyMessageCategoryBPO() {
		return myMessageCategoryBPO;
	}
	public void setMyMessageCategoryBPO(MessageCategoryBPO myMessageCategoryBPO) {
		this.myMessageCategoryBPO = myMessageCategoryBPO;
	}



}