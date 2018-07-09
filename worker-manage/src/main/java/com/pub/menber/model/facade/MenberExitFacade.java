package com.pub.menber.model.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pub.menber.model.bpo.MenberExitBPO;
import com.pub.menber.model.dto.MenberExitDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberExitDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberExitFacade { 
	
	private MenberExitBPO myMenberExitBPO;

	public boolean save(MenberExitDTO dto) throws AppException {
		return myMenberExitBPO.saveTX(dto);
	}
	
	public void update(MenberExitDTO dto,HttpServletRequest request) throws AppException {
		myMenberExitBPO.updateTX(dto,request);
	}

	public void saveOrUpdate(MenberExitDTO dto) throws AppException {
		myMenberExitBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberExitBPO.deleteTX(ids);
	}

	
	public MenberExitDTO get(java.lang.String id) throws AppException {
		return myMenberExitBPO.get(id);
	}
	
	public List getByMenId(java.lang.String menId) throws AppException {
		return myMenberExitBPO.getByMenId(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberExitBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberExitBPO.listByIds(ids);
	}


	public MenberExitBPO getMyMenberExitBPO() {
		return myMenberExitBPO;
	}
	public void setMyMenberExitBPO(MenberExitBPO myMenberExitBPO) {
		this.myMenberExitBPO = myMenberExitBPO;
	}

}