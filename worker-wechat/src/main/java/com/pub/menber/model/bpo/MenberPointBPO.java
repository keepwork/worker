package com.pub.menber.model.bpo;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.dao.MenberPointDAO;
import com.pub.menber.model.dto.MenberPointDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberPointDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberPointBPO extends BpoSupport { 
	
	private MenberPointDAO myMenberPointDAO;

	
	public boolean saveTX(MenberPointDTO dto) throws AppException {
		myMenberPointDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberPointDTO dto) throws AppException {
		myMenberPointDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MenberPointDTO dto) throws AppException {
		myMenberPointDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberPointDTO dto = this.get(id[i]);
			this.myMenberPointDAO.delete(dto);

		}
	}

	
	public MenberPointDTO get(java.lang.String id){
		return myMenberPointDAO.get(id);
	}
	
	
	public boolean checkSign(java.lang.String menId){
		return myMenberPointDAO.checkSign(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberPointDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberPointDAO.listByIds(ids);
	}
	
	
	public List listByMenId(String menId){
		return myMenberPointDAO.listByMenId(menId);
	}

	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberPointDAO.listForPagination(params);
    }
	
	
	
	
	

	public MenberPointDAO getMyMenberPointDAO() {
		return myMenberPointDAO;
	}


	public void setMyMenberPointDAO(MenberPointDAO myMenberPointDAO) {
		this.myMenberPointDAO = myMenberPointDAO;
	}

	

}