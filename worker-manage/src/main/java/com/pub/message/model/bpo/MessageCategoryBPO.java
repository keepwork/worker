package com.pub.message.model.bpo;

import java.util.List;

import com.pub.message.model.dao.MessageCategoryDAO;
import com.pub.message.model.dto.MessageCategoryDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MessageCategoryDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MessageCategoryBPO extends BpoSupport { 
	
	private MessageCategoryDAO myMessageCategoryDAO;

	
	public boolean saveTX(MessageCategoryDTO dto) throws AppException {
		myMessageCategoryDAO.save(dto);
		return true;
	}

	
	public void updateTX(MessageCategoryDTO dto) throws AppException {
		myMessageCategoryDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MessageCategoryDTO dto) throws AppException {
		myMessageCategoryDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MessageCategoryDTO dto = this.get(id[i]);
			this.myMessageCategoryDAO.delete(dto);

		}
	}

	
	public MessageCategoryDTO get(java.lang.String code) throws AppException {
		return myMessageCategoryDAO.get(code);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMessageCategoryDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMessageCategoryDAO.listByIds(ids);
	}
	
	public List listByParent(String parentCode) throws AppException {
		return myMessageCategoryDAO.listByParent(parentCode);
	}

	

	public MessageCategoryDAO getMyMessageCategoryDAO() {
		return myMessageCategoryDAO;
	}
	public void setMyMessageCategoryDAO(MessageCategoryDAO myMessageCategoryDAO) {
		this.myMessageCategoryDAO = myMessageCategoryDAO;
	}

	

}