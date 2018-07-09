package com.pub.menber.model.facade;

import java.util.List;

import com.pub.menber.model.bpo.MenberCashBPO;
import com.pub.menber.model.dto.MenberCashDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberCashDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberCashFacade { 
	
	private MenberCashBPO myMenberCashBPO;

	public boolean save(MenberCashDTO dto) throws AppException {
		return myMenberCashBPO.saveTX(dto);
	}

	
	public void update(MenberCashDTO dto) throws AppException {
		myMenberCashBPO.updateTX(dto);
	}
	
	public void audit(MenberCashDTO dto) throws AppException {
		myMenberCashBPO.auditTX(dto);
	}

	public void saveOrUpdate(MenberCashDTO dto) throws AppException {
		myMenberCashBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberCashBPO.deleteTX(ids);
	}

	
	public MenberCashDTO get(java.lang.String id) throws AppException {
		return myMenberCashBPO.get(id);
	}
	
	public MenberCashDTO getByMenId(java.lang.String menId) throws AppException {
		return myMenberCashBPO.getByMenId(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberCashBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberCashBPO.listByIds(ids);
	}


	public MenberCashBPO getMyMenberCashBPO() {
		return myMenberCashBPO;
	}


	public void setMyMenberCashBPO(MenberCashBPO myMenberCashBPO) {
		this.myMenberCashBPO = myMenberCashBPO;
	}

	



}