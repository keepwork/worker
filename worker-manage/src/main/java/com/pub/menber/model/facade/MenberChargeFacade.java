package com.pub.menber.model.facade;

import java.util.List;
import java.util.Map;

import com.pub.menber.model.bpo.MenberChargeBPO;
import com.pub.menber.model.dto.MenberChargeDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护MenberChargeDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class MenberChargeFacade { 
	
	private MenberChargeBPO myMenberChargeBPO;

	public boolean save(MenberChargeDTO dto) throws AppException {
		return myMenberChargeBPO.saveTX(dto);
	}

	
	public void update(MenberChargeDTO dto) throws AppException {
		myMenberChargeBPO.updateTX(dto);
	}

	public void saveOrUpdate(MenberChargeDTO dto) throws AppException {
		myMenberChargeBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myMenberChargeBPO.deleteTX(ids);
	}

	
	public MenberChargeDTO get(java.lang.String id) throws AppException {
		return myMenberChargeBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myMenberChargeBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myMenberChargeBPO.listByIds(ids);
	}

	
	public boolean checkMenberCharge(String menId,String squeues){
		return myMenberChargeBPO.checkMenberCharge(menId,squeues);
	}
	
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myMenberChargeBPO.listForPagination(params);
    }
	
	
	

	public MenberChargeBPO getMyMenberChargeBPO() {
		return myMenberChargeBPO;
	}
	public void setMyMenberChargeBPO(MenberChargeBPO myMenberChargeBPO) {
		this.myMenberChargeBPO = myMenberChargeBPO;
	}

}