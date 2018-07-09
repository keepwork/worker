package com.pub.menber.model.facade;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.bpo.MenberCutBPO;
import com.pub.menber.model.dto.MenberCutDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberCutDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberCutFacade { 
	
	private MenberCutBPO myMenberCutBPO;

	public boolean save(MenberCutDTO dto) throws AppException {
		return myMenberCutBPO.saveTX(dto);
	}

	
	public void update(MenberCutDTO dto) throws AppException {
		myMenberCutBPO.updateTX(dto);
	}

	public void saveOrUpdate(MenberCutDTO dto) throws AppException {
		myMenberCutBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberCutBPO.deleteTX(ids);
	}

	
	public MenberCutDTO get(java.lang.String id) throws AppException {
		return myMenberCutBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberCutBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberCutBPO.listByIds(ids);
	}
	
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberCutBPO.listForPagination(params);
    }
	
	


	public MenberCutBPO getMyMenberCutBPO() {
		return myMenberCutBPO;
	}
	public void setMyMenberCutBPO(MenberCutBPO myMenberCutBPO) {
		this.myMenberCutBPO = myMenberCutBPO;
	}

}