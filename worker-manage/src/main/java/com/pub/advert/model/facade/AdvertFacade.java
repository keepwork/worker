package com.pub.advert.model.facade;

import java.util.List;
import java.util.Map;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;
import com.pub.advert.model.bpo.AdvertBPO;
import com.pub.advert.model.dto.AdvertDTO;

/**
 * 维护AdvertDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class AdvertFacade { 
	
	private AdvertBPO myAdvertBPO;

	public boolean save(AdvertDTO dto) throws AppException {
		return myAdvertBPO.saveTX(dto);
	}

	
	public void update(AdvertDTO dto) throws AppException {
		myAdvertBPO.updateTX(dto);
	}

	public void saveOrUpdate(AdvertDTO dto) throws AppException {
		myAdvertBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myAdvertBPO.deleteTX(ids);
	}

	
	public AdvertDTO get(java.lang.String id) throws AppException {
		return myAdvertBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myAdvertBPO.list(limit);
	}

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myAdvertBPO.listForPagination(params);
    }
	
	public List listByIds(String ids) throws AppException {
		return myAdvertBPO.listByIds(ids);
	}
	

	public AdvertBPO getMyAdvertBPO() {
		return myAdvertBPO;
	}
	public void setMyAdvertBPO(AdvertBPO myAdvertBPO) {
		this.myAdvertBPO = myAdvertBPO;
	}



}