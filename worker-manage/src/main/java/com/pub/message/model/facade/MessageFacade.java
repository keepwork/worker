package com.pub.message.model.facade;

import java.util.List;
import java.util.Map;
import com.pub.message.model.bpo.MessageBPO;
import com.pub.message.model.dto.MessageDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MessageDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MessageFacade { 
	
	private MessageBPO myMessageBPO;

	public boolean save(MessageDTO dto) throws AppException {
		return myMessageBPO.saveTX(dto);
	}

	
	public void update(MessageDTO dto) throws AppException {
		myMessageBPO.updateTX(dto);
	}

	public void saveOrUpdate(MessageDTO dto) throws AppException {
		myMessageBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMessageBPO.deleteTX(ids);
	}

	
	public MessageDTO get(java.lang.String id) throws AppException {
		return myMessageBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMessageBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMessageBPO.listByIds(ids);
	}
	
	public List listByCateCode(String cateCode) throws AppException {
		return myMessageBPO.listByCateCode(cateCode);
	}
	
	public long countByCateCode(String cateCode) throws AppException {
		return myMessageBPO.countByCateCode(cateCode);
	}
	
	public List listByCateCode(String cateCode,int num) throws AppException {
		return myMessageBPO.listByCateCode(cateCode,num);
	}
	
	public List listByCateCodes(String cateCodes,int num){
		return myMessageBPO.listByCateCodes(cateCodes,num);
	}

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMessageBPO.listForPagination(params);
    }

	public MessageBPO getMyMessageBPO() {
		return myMessageBPO;
	}
	public void setMyMessageBPO(MessageBPO myMessageBPO) {
		this.myMessageBPO = myMessageBPO;
	}



}