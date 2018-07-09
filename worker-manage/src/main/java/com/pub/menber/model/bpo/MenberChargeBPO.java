package com.pub.menber.model.bpo;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.dao.MenberChargeDAO;
import com.pub.menber.model.dto.MenberChargeDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberChargeDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberChargeBPO extends BpoSupport { 
	
	private MenberChargeDAO myMenberChargeDAO;

	
	public boolean saveTX(MenberChargeDTO dto) throws AppException {
		myMenberChargeDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberChargeDTO dto) throws AppException {
		myMenberChargeDAO.update(dto);
	}

	
	public void saveOrUpdateTX(MenberChargeDTO dto) throws AppException {
		myMenberChargeDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberChargeDTO dto = this.get(id[i]);
			this.myMenberChargeDAO.delete(dto);

		}
	}

	
	public MenberChargeDTO get(java.lang.String id){
		return myMenberChargeDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberChargeDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberChargeDAO.listByIds(ids);
	}

	
	public boolean checkMenberCharge(String menId,String squeues){
		return myMenberChargeDAO.checkMenberCharge(menId,squeues);
	}
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberChargeDAO.listForPagination(params);
    }
	
	
	

	public MenberChargeDAO getMyMenberChargeDAO() {
		return myMenberChargeDAO;
	}
	public void setMyMenberChargeDAO(MenberChargeDAO myMenberChargeDAO) {
		this.myMenberChargeDAO = myMenberChargeDAO;
	}

	

}