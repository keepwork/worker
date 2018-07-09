package com.pub.menber.model.bpo;

import java.math.BigDecimal;
import java.util.List;

import com.pub.menber.model.dao.MenberCashDAO;
import com.pub.menber.model.dao.MenberDAO;
import com.pub.menber.model.dto.MenberCashDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberCashDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberCashBPO extends BpoSupport { 
	
	private MenberCashDAO myMenberCashDAO;
	private MenberDAO myMenberDAO;

	
	public boolean saveTX(MenberCashDTO dto) throws AppException {
		myMenberCashDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberCashDTO dto) throws AppException {
		myMenberCashDAO.update(dto);
	}
	
	public void auditTX(MenberCashDTO dto) throws AppException {
		myMenberCashDAO.update(dto);
		if(dto.getIsDown()==2){//已批准
			MenberDTO men = myMenberDAO.get(dto.getMenId());
			double bf = men.getBalanceFee().doubleValue() - dto.getPrice().doubleValue();
			men.setBalanceFee(new BigDecimal(bf));
			myMenberDAO.update(men);
		}
	}

	
	public void saveOrUpdateTX(MenberCashDTO dto) throws AppException {
		myMenberCashDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberCashDTO dto = this.get(id[i]);
			this.myMenberCashDAO.delete(dto);

		}
	}

	
	public MenberCashDTO get(java.lang.String id){
		return myMenberCashDAO.get(id);
	}
	
	public MenberCashDTO getByMenId(java.lang.String menId){
		return myMenberCashDAO.getByMenId(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberCashDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberCashDAO.listByIds(ids);
	}


	public MenberCashDAO getMyMenberCashDAO() {
		return myMenberCashDAO;
	}


	public void setMyMenberCashDAO(MenberCashDAO myMenberCashDAO) {
		this.myMenberCashDAO = myMenberCashDAO;
	}


	public MenberDAO getMyMenberDAO() {
		return myMenberDAO;
	}


	public void setMyMenberDAO(MenberDAO myMenberDAO) {
		this.myMenberDAO = myMenberDAO;
	}

	

}