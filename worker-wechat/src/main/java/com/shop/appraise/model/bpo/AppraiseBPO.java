package com.shop.appraise.model.bpo;

import java.util.List;
import java.util.Map;

import com.shop.appraise.model.dao.AppraiseDAO;
import com.shop.appraise.model.dto.AppraiseDTO;
import com.shop.order.model.dao.OrderDAO;
import com.shop.order.model.dto.OrderDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.model.bpo.BpoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护AppraiseDTO对象的BPO类, 使用单例模式，请勿设置实例化属性
 * 
 * @author BruceKobe(javalc@163.com)
 * @date Dec 28, 2015 12:13:59 PM
 */
public class AppraiseBPO extends BpoSupport { 
	
	private AppraiseDAO myAppraiseDAO;
	private OrderDAO myOrderDAO;

	
	public boolean saveAppraiseAanUpdateOrderTX(AppraiseDTO dto,OrderDTO orderDTO) throws AppException {
		AppraiseDTO appraiseDTO = myAppraiseDAO.save(dto);
		orderDTO.setAppraiseId(appraiseDTO.getId());
		myOrderDAO.update(orderDTO);
		return true;
	}

	public boolean saveTX(AppraiseDTO dto) throws AppException {
		myAppraiseDAO.save(dto);
		return true;
	}

	public void updateTX(AppraiseDTO dto) throws AppException {
		myAppraiseDAO.update(dto);
	}

	
	public void saveOrUpdateTX(AppraiseDTO dto) throws AppException {
		myAppraiseDAO.saveOrUpdate(dto);
	}

	
	public void deleteTX(String ids) throws AppException {
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			AppraiseDTO dto = this.get(id[i]);
			this.myAppraiseDAO.delete(dto);

		}
	}

	
	public AppraiseDTO get(java.lang.String id){
		return myAppraiseDAO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myAppraiseDAO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		ids = "'" + ids.replaceAll(",", "','") + "'";
		return myAppraiseDAO.listByIds(ids);
	}
	

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myAppraiseDAO.listForPagination(params);
    }

    public int getWorkerAppraiseCount(String workerId,String appraiseState){
		return myAppraiseDAO.getWorkerAppraiseCount(workerId,appraiseState);
    }

	public AppraiseDTO getByOrderId(java.lang.String orderId){
		return myAppraiseDAO.getByOrderId(orderId);
	}

	public AppraiseDAO getMyAppraiseDAO() {
		return myAppraiseDAO;
	}

	public void setMyAppraiseDAO(AppraiseDAO myAppraiseDAO) {
		this.myAppraiseDAO = myAppraiseDAO;
	}

	public OrderDAO getMyOrderDAO() {
		return myOrderDAO;
	}

	public void setMyOrderDAO(OrderDAO myOrderDAO) {
		this.myOrderDAO = myOrderDAO;
	}
}