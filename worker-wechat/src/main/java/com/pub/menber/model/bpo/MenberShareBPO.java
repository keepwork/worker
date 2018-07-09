package com.pub.menber.model.bpo;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.dao.MenberShareDAO;
import com.pub.menber.model.dto.MenberShareDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberShareDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberShareBPO extends BpoSupport { 
	
	private MenberShareDAO myMenberShareDAO;

	
	public boolean saveTX(MenberShareDTO dto){
		myMenberShareDAO.save(dto);
		return true;
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberShareDAO.list(limit);
	}

	
	public List listByMenId(String menId){
		return myMenberShareDAO.listByMenId(menId);
	}

	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberShareDAO.listForPagination(params);
    }
	

	public MenberShareDAO getMyMenberShareDAO() {
		return myMenberShareDAO;
	}


	public void setMyMenberShareDAO(MenberShareDAO myMenberShareDAO) {
		this.myMenberShareDAO = myMenberShareDAO;
	}

	

}