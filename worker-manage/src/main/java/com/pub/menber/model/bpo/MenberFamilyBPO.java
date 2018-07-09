package com.pub.menber.model.bpo;

import java.util.List;

import com.pub.menber.model.dao.MenberFamilyDAO;
import com.pub.menber.model.dto.MenberFamilyDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护FamilyDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberFamilyBPO extends BpoSupport { 
	
	private MenberFamilyDAO myMenberFamilyDAO;

	
	public boolean saveTX(MenberFamilyDTO dto) throws AppException {
		myMenberFamilyDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberFamilyDTO dto) throws AppException {
		myMenberFamilyDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MenberFamilyDTO dto) throws AppException {
		myMenberFamilyDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberFamilyDTO dto = this.get(id[i]);
			this.myMenberFamilyDAO.delete(dto);

		}
	}

	
	public MenberFamilyDTO get(java.lang.String id){
		return myMenberFamilyDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberFamilyDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberFamilyDAO.listByIds(ids);
	}
	
	
	public List listByMenId(String menId){
		return myMenberFamilyDAO.listByMenId(menId);
	}


	public MenberFamilyDAO getMyMenberFamilyDAO() {
		return myMenberFamilyDAO;
	}


	public void setMyMenberFamilyDAO(MenberFamilyDAO myMenberFamilyDAO) {
		this.myMenberFamilyDAO = myMenberFamilyDAO;
	}
	

	

	

}