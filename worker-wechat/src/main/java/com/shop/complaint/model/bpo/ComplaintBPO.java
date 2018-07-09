package com.shop.complaint.model.bpo;

import java.util.List;
import java.util.Map;

import com.shop.complaint.model.dao.ComplaintDAO;
import com.shop.complaint.model.dto.ComplaintDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ComplaintDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class ComplaintBPO extends BpoSupport { 
	
	private ComplaintDAO myComplaintDAO;

	
	public boolean saveTX(ComplaintDTO dto) throws AppException {
		myComplaintDAO.save(dto);
		return true;
	}

	
	public void updateTX(ComplaintDTO dto) throws AppException {
		myComplaintDAO.update(dto);
	}

	
	public void saveOrUpdateTX(ComplaintDTO dto) throws AppException {
		myComplaintDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			ComplaintDTO dto = this.get(id[i]);
			this.myComplaintDAO.delete(dto);

		}
	}

	
	public ComplaintDTO get(java.lang.String id){
		return myComplaintDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myComplaintDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myComplaintDAO.listByIds(ids);
	}
	

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myComplaintDAO.listForPagination(params);
    }
    

	public ComplaintDAO getMyComplaintDAO() {
		return myComplaintDAO;
	}
	public void setMyComplaintDAO(ComplaintDAO myComplaintDAO) {
		this.myComplaintDAO = myComplaintDAO;
	}

	

}