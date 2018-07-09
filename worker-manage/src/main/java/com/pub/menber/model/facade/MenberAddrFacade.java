package com.pub.menber.model.facade;

import java.util.List;

import com.pub.menber.model.bpo.MenberAddrBPO;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护AddrDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberAddrFacade { 
	
	private MenberAddrBPO myMenberAddrBPO;

	public boolean save(MenberAddrDTO dto){
		return myMenberAddrBPO.saveTX(dto);
	}

	
	public void update(MenberAddrDTO dto){
		myMenberAddrBPO.updateTX(dto);
	}

	public void saveOrUpdate(MenberAddrDTO dto) throws AppException {
		myMenberAddrBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids){
		myMenberAddrBPO.deleteTX(ids);
	}

	
	public MenberAddrDTO get(java.lang.String id){
		return myMenberAddrBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberAddrBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberAddrBPO.listByIds(ids);
	}
	
	
	public List listByMenId(String menId){
		return myMenberAddrBPO.listByMenId(menId);
	}


	public MenberAddrBPO getMyMenberAddrBPO() {
		return myMenberAddrBPO;
	}


	public void setMyMenberAddrBPO(MenberAddrBPO myMenberAddrBPO) {
		this.myMenberAddrBPO = myMenberAddrBPO;
	}

	

}