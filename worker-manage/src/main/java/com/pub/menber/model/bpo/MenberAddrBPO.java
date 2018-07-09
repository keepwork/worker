package com.pub.menber.model.bpo;

import java.util.List;

import com.pub.menber.model.dao.MenberAddrDAO;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护AddrDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberAddrBPO extends BpoSupport { 
	
	private MenberAddrDAO myMenberAddrDAO;

	
	public boolean saveTX(MenberAddrDTO dto){
		myMenberAddrDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberAddrDTO dto){
		myMenberAddrDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MenberAddrDTO dto) throws AppException {
		myMenberAddrDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids){
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberAddrDTO dto = this.get(id[i]);
			this.myMenberAddrDAO.delete(dto);

		}
	}

	
	public MenberAddrDTO get(java.lang.String id){
		return myMenberAddrDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberAddrDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberAddrDAO.listByIds(ids);
	}
	
	
	public List listByMenId(String menId){
		return myMenberAddrDAO.listByMenId(menId);
	}


	public MenberAddrDAO getMyMenberAddrDAO() {
		return myMenberAddrDAO;
	}


	public void setMyMenberAddrDAO(MenberAddrDAO myMenberAddrDAO) {
		this.myMenberAddrDAO = myMenberAddrDAO;
	}
	

}