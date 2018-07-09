package com.shop.order.model.bpo;

import java.util.List;

import com.shop.order.model.dao.OrderItemDAO;
import com.shop.order.model.dto.OrderItemDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护OrderItemDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class OrderItemBPO extends BpoSupport { 
	
	private OrderItemDAO myOrderItemDAO;

	
	public boolean saveTX(OrderItemDTO dto) throws AppException {
		myOrderItemDAO.save(dto);
		return true;
	}

	
	public void updateTX(OrderItemDTO dto) throws AppException {
		myOrderItemDAO.update(dto);
	}

	
	public void saveOrUpdateTX(OrderItemDTO dto) throws AppException {
		myOrderItemDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			OrderItemDTO dto = this.get(id[i]);
			this.myOrderItemDAO.delete(dto);

		}
	}

	
	public OrderItemDTO get(java.lang.String id){
		return myOrderItemDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myOrderItemDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myOrderItemDAO.listByIds(ids);
	}
	
	public List<OrderItemDTO> listByOrderId(String id) throws AppException {
		return myOrderItemDAO.listByOrderId(id);
	}
	

	public OrderItemDAO getMyOrderItemDAO() {
		return myOrderItemDAO;
	}
	public void setMyOrderItemDAO(OrderItemDAO myOrderItemDAO) {
		this.myOrderItemDAO = myOrderItemDAO;
	}

	

}