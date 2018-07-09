package com.pub.menber.model.facade;

import java.util.List;

import com.pub.menber.model.bpo.MenberFamilyBPO;
import com.pub.menber.model.dto.MenberFamilyDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护FamilyDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberFamilyFacade { 
	
	private MenberFamilyBPO myMenberFamilyBPO;

	public boolean save(MenberFamilyDTO dto) throws AppException {
		return myMenberFamilyBPO.saveTX(dto);
	}

	
	public void update(MenberFamilyDTO dto) throws AppException {
		myMenberFamilyBPO.updateTX(dto);
	}

	public void saveOrUpdate(MenberFamilyDTO dto) throws AppException {
		myMenberFamilyBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberFamilyBPO.deleteTX(ids);
	}

	
	public MenberFamilyDTO get(java.lang.String id) throws AppException {
		return myMenberFamilyBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberFamilyBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberFamilyBPO.listByIds(ids);
	}
	
	public List listByMenId(String menId){
		return myMenberFamilyBPO.listByMenId(menId);
	}


	public MenberFamilyBPO getMyMenberFamilyBPO() {
		return myMenberFamilyBPO;
	}


	public void setMyMenberFamilyBPO(MenberFamilyBPO myMenberFamilyBPO) {
		this.myMenberFamilyBPO = myMenberFamilyBPO;
	}

	

}