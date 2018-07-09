package com.pub.menber.model.facade;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.bpo.MenberShareBPO;
import com.pub.menber.model.dto.MenberShareDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberShareDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberShareFacade { 
	
	private MenberShareBPO myMenberShareBPO;

	public boolean save(MenberShareDTO dto) {
		return myMenberShareBPO.saveTX(dto);
	}

	public List list(LimitInfo limit) throws AppException {
		return myMenberShareBPO.list(limit);
	}

	
	public List listByMenId(String menId){
		return myMenberShareBPO.listByMenId(menId);
	}
	
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberShareBPO.listForPagination(params);
    }


	public MenberShareBPO getMyMenberShareBPO() {
		return myMenberShareBPO;
	}


	public void setMyMenberShareBPO(MenberShareBPO myMenberShareBPO) {
		this.myMenberShareBPO = myMenberShareBPO;
	}

	



}