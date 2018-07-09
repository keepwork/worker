package com.pub.menber.model.bpo;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pub.menber.model.dao.MenberDAO;
import com.pub.menber.model.dao.MenberExitDAO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberExitDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberExitDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class MenberExitBPO extends BpoSupport { 
	
	private MenberExitDAO myMenberExitDAO;
	private MenberDAO myMenberDAO;
	
	public boolean saveTX(MenberExitDTO dto) throws AppException {
		myMenberExitDAO.save(dto);
		return true;
	}

	
	public void updateTX(MenberExitDTO dto,HttpServletRequest request) throws AppException {
		myMenberExitDAO.update(dto);
		if(dto.getAuditResult()==2){//已批准
			MenberDTO men = myMenberDAO.get(dto.getMenId());
			men.setIsjoin(0);
			men.setJoinTime(null);
			men.setEffectTime(null);
			men.setBalanceFee(new BigDecimal(0));
			men.setRightFee(new BigDecimal(0));
			myMenberDAO.update(men);
			request.getSession().setAttribute("pcmenber", men);
			request.getSession().setAttribute("wxmenber", men);
		}
	}
	
	
	public void saveOrUpdateTX(MenberExitDTO dto) throws AppException {
		myMenberExitDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			MenberExitDTO dto = this.get(id[i]);
			this.myMenberExitDAO.delete(dto);

		}
	}

	
	public MenberExitDTO get(java.lang.String id){
		return myMenberExitDAO.get(id);
	}
	
	public List getByMenId(java.lang.String menId){
		return myMenberExitDAO.getByMenId(menId);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberExitDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myMenberExitDAO.listByIds(ids);
	}


	public MenberExitDAO getMyMenberExitDAO() {
		return myMenberExitDAO;
	}
	public void setMyMenberExitDAO(MenberExitDAO myMenberExitDAO) {
		this.myMenberExitDAO = myMenberExitDAO;
	}
	public MenberDAO getMyMenberDAO() {
		return myMenberDAO;
	}
	public void setMyMenberDAO(MenberDAO myMenberDAO) {
		this.myMenberDAO = myMenberDAO;
	}


}