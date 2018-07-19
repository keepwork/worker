package com.pub.advert.model.bpo;

import java.util.List;
import java.util.Map;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;
import com.pub.advert.model.dao.AdvertDAO;
import com.pub.advert.model.dto.AdvertDTO;

/**
 * 维护AdvertDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class AdvertBPO extends BpoSupport { 
	
	private AdvertDAO myAdvertDAO;

	
	public boolean saveTX(AdvertDTO dto) throws AppException {
		myAdvertDAO.save(dto);
		return true;
	}

	
	public void updateTX(AdvertDTO dto) throws AppException {
		myAdvertDAO.update(dto);
	}

	
	public void saveOrUpdateTX(AdvertDTO dto) throws AppException {
		myAdvertDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			AdvertDTO dto = this.get(id[i]);
			this.myAdvertDAO.delete(dto);

		}
	}

	
	public AdvertDTO get(java.lang.String id) throws AppException {
		return myAdvertDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myAdvertDAO.list(limit);
	}

	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myAdvertDAO.listForPagination(params);
    }

	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myAdvertDAO.listByIds(ids);
	}
	

	public AdvertDAO getMyAdvertDAO() {
		return myAdvertDAO;
	}
	public void setMyAdvertDAO(AdvertDAO myAdvertDAO) {
		this.myAdvertDAO = myAdvertDAO;
	}

	

}