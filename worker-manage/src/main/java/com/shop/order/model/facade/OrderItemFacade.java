package com.shop.order.model.facade;

import java.util.List;

import com.shop.order.model.bpo.OrderItemBPO;
import com.shop.order.model.dto.OrderItemDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护OrderItemDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class OrderItemFacade { 
	
	private OrderItemBPO myOrderItemBPO;

	public boolean save(OrderItemDTO dto) throws AppException {
		return myOrderItemBPO.saveTX(dto);
	}

	
	public void update(OrderItemDTO dto) throws AppException {
		myOrderItemBPO.updateTX(dto);
	}

	public void saveOrUpdate(OrderItemDTO dto) throws AppException {
		myOrderItemBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myOrderItemBPO.deleteTX(ids);
	}

	
	public OrderItemDTO get(java.lang.String id) throws AppException {
		return myOrderItemBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myOrderItemBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myOrderItemBPO.listByIds(ids);
	}
	
	public List<OrderItemDTO> listByOrderId(String id) throws AppException {
		return myOrderItemBPO.listByOrderId(id);
	}
	

	public OrderItemBPO getMyOrderItemBPO() {
		return myOrderItemBPO;
	}
	public void setMyOrderItemBPO(OrderItemBPO myOrderItemBPO) {
		this.myOrderItemBPO = myOrderItemBPO;
	}



}