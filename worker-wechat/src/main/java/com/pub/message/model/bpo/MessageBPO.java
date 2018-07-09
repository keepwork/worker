package com.pub.message.model.bpo;

import java.util.List;
import java.util.Map;
import com.pub.message.model.dao.MessageCategoryDAO;
import com.pub.message.model.dao.MessageDAO;
import com.pub.message.model.dto.MessageDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 维护MessageDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MessageBPO extends BpoSupport { 
	
	private static ICache cache = CacheFactory.newCache();
	
	private MessageDAO myMessageDAO;
	private MessageCategoryDAO myMessageCategoryDAO;

	
	public boolean saveTX(MessageDTO dto) throws AppException {
		myMessageDAO.save(dto);
		return true;
	}

	
	public void updateTX(MessageDTO dto) throws AppException {
		myMessageDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MessageDTO dto) throws AppException {
		myMessageDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MessageDTO dto = this.get(id[i]);
			this.myMessageDAO.delete(dto);

		}
	}

	
	public MessageDTO get(java.lang.String id) throws AppException {
		return myMessageDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMessageDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMessageDAO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode) throws AppException {
		return myMessageDAO.listByCateCode(cateCode);
	}
	
	public long countByCateCode(String cateCode) throws AppException {
		return myMessageDAO.countByCateCode(cateCode);
	}
	
	public List listByCateCode(String cateCode,int num) throws AppException {
		return myMessageDAO.listByCateCode(cateCode,num);
	}
	
	public List listByCateCodes(String cateCodes,int num){
		return myMessageDAO.listByCateCodes(cateCodes,num);
	}
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMessageDAO.listForPagination(params);
    }


	public MessageDAO getMyMessageDAO() {
		return myMessageDAO;
	}
	public void setMyMessageDAO(MessageDAO myMessageDAO) {
		this.myMessageDAO = myMessageDAO;
	}
	public MessageCategoryDAO getMyMessageCategoryDAO() {
		return myMessageCategoryDAO;
	}
	public void setMyMessageCategoryDAO(MessageCategoryDAO myMessageCategoryDAO) {
		this.myMessageCategoryDAO = myMessageCategoryDAO;
	}

	

}