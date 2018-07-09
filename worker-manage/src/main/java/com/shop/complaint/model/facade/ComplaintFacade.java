package com.shop.complaint.model.facade;

import java.util.List;
import java.util.Map;

import com.shop.complaint.model.bpo.ComplaintBPO;
import com.shop.complaint.model.dto.ComplaintDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ComplaintDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class ComplaintFacade { 
	
	private ComplaintBPO myComplaintBPO;

	public boolean save(ComplaintDTO dto) throws AppException {
		return myComplaintBPO.saveTX(dto);
	}

	
	public void update(ComplaintDTO dto) throws AppException {
		myComplaintBPO.updateTX(dto);
	}

	public void saveOrUpdate(ComplaintDTO dto) throws AppException {
		myComplaintBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myComplaintBPO.deleteTX(ids);
	}

	
	public ComplaintDTO get(java.lang.String id) throws AppException {
		return myComplaintBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myComplaintBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myComplaintBPO.listByIds(ids);
	}

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myComplaintBPO.listForPagination(params);
    }
	
	public ComplaintBPO getMyComplaintBPO() {
		return myComplaintBPO;
	}
	public void setMyComplaintBPO(ComplaintBPO myComplaintBPO) {
		this.myComplaintBPO = myComplaintBPO;
	}



}