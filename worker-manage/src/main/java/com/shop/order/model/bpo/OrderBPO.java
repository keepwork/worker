package com.shop.order.model.bpo;

import java.util.List;
import java.util.Map;

import com.shop.order.model.dao.OrderDAO;
import com.shop.order.model.dto.OrderDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护OrderDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class OrderBPO extends BpoSupport { 
	
	private OrderDAO myOrderDAO;

	
	public String saveTX(OrderDTO dto) throws AppException {
		myOrderDAO.save(dto);
		return dto.getOrderId();
	}

	
	public void updateTX(OrderDTO dto) {
		myOrderDAO.update(dto);
	}

	
	public void saveOrUpdateTX(OrderDTO dto) throws AppException {
		myOrderDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			OrderDTO dto = this.get(id[i]);
			this.myOrderDAO.delete(dto);

		}
	}

	
	public OrderDTO get(java.lang.String id){
		return myOrderDAO.get(id);
	}
	
	public OrderDTO getOrderBySN(java.lang.String sn){
		return myOrderDAO.getOrderBySN(sn);
	}


	public List list(LimitInfo limit) throws AppException {
		return myOrderDAO.list(limit);
	}

	public OrderDTO getStatistics(LimitInfo limit)throws AppException {
		return myOrderDAO.getStatistics(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myOrderDAO.listByIds(ids);
	}
	
	
	public List listByMenId(String merId) throws AppException {
		return myOrderDAO.listByMenId(merId);
	}
	
	public List listByWorkerId(String workerId) throws AppException {
		return myOrderDAO.listByWorkerId(workerId);
	}
	
	public List listByStatus(String status) throws AppException {
		return myOrderDAO.listByStatus(status);
	}
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myOrderDAO.listForPagination(params);
    }

	public int getWorkerOrderTotalNum(String workerId) throws AppException {
		return myOrderDAO.getWorkerOrderTotalNum(workerId);
	}

	public OrderDAO getMyOrderDAO() {
		return myOrderDAO;
	}
	public void setMyOrderDAO(OrderDAO myOrderDAO) {
		this.myOrderDAO = myOrderDAO;
	}

}