package com.pub.menber.model.facade;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.bpo.MenberPointBPO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberPointDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberPointFacade { 
	
	private MenberPointBPO myMenberPointBPO;

	public boolean save(MenberPointDTO dto) throws AppException {
		return myMenberPointBPO.saveTX(dto);
	}

	
	public void update(MenberPointDTO dto) throws AppException {
		myMenberPointBPO.updateTX(dto);
	}

	public void saveOrUpdate(MenberPointDTO dto) throws AppException {
		myMenberPointBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberPointBPO.deleteTX(ids);
	}

	
	public MenberPointDTO get(java.lang.String id) throws AppException {
		return myMenberPointBPO.get(id);
	}
	
	public boolean checkSign(java.lang.String menId) throws AppException {
		return myMenberPointBPO.checkSign(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberPointBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberPointBPO.listByIds(ids);
	}

	public List listByMenId(String menId){
		return myMenberPointBPO.listByMenId(menId);
	}
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberPointBPO.listForPagination(params);
    }
	
	
	
	

	public MenberPointBPO getMyMenberPointBPO() {
		return myMenberPointBPO;
	}


	public void setMyMenberPointBPO(MenberPointBPO myMenberPointBPO) {
		this.myMenberPointBPO = myMenberPointBPO;
	}

	



}