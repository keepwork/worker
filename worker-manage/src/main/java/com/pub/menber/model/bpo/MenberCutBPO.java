package com.pub.menber.model.bpo;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.dao.MenberCutDAO;
import com.pub.menber.model.dto.MenberCutDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberCutDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberCutBPO extends BpoSupport { 
	
	private MenberCutDAO myMenberCutDAO;

	
	public boolean saveTX(MenberCutDTO dto) throws AppException {
		myMenberCutDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberCutDTO dto) throws AppException {
		myMenberCutDAO.update(dto);
	}
	
	
	public void saveOrUpdateTX(MenberCutDTO dto) throws AppException {
		myMenberCutDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberCutDTO dto = this.get(id[i]);
			this.myMenberCutDAO.delete(dto);

		}
	}

	
	public MenberCutDTO get(java.lang.String id){
		return myMenberCutDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberCutDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberCutDAO.listByIds(ids);
	}

	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberCutDAO.listForPagination(params);
    }
	
	
	

	public MenberCutDAO getMyMenberCutDAO() {
		return myMenberCutDAO;
	}
	public void setMyMenberCutDAO(MenberCutDAO myMenberCutDAO) {
		this.myMenberCutDAO = myMenberCutDAO;
	}

}