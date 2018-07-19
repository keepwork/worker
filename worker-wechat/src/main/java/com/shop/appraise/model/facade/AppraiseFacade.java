package com.shop.appraise.model.facade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.shop.appraise.model.bpo.AppraiseBPO;
import com.shop.appraise.model.dto.AppraiseDTO;
import com.shop.order.model.dto.OrderDTO;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护AppraiseDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author BruceKobe(javalc@163.com)
 * @date Dec 28, 2018 12:43:45 PM
 */
public class AppraiseFacade { 
	
	private AppraiseBPO myAppraiseBPO;

	public boolean saveAppraiseAanUpdateOrder(AppraiseDTO dto,OrderDTO orderDTO) throws AppException {
		return myAppraiseBPO.saveAppraiseAanUpdateOrderTX(dto,orderDTO);
	}

	public boolean save(AppraiseDTO dto) throws AppException {
		return myAppraiseBPO.saveTX(dto);
	}

	
	public void update(AppraiseDTO dto) throws AppException {
		myAppraiseBPO.updateTX(dto);
	}

	public void saveOrUpdate(AppraiseDTO dto) throws AppException {
		myAppraiseBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myAppraiseBPO.deleteTX(ids);
	}

	
	public AppraiseDTO get(java.lang.String id) throws AppException {
		return myAppraiseBPO.get(id);
	}


	public List list(LimitInfo limit) throws AppException {
		return myAppraiseBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myAppraiseBPO.listByIds(ids);
	}

	public Map<String,Object> listForPagination(Map<String,String> params){
		return myAppraiseBPO.listForPagination(params);
    }

	public int getWorkerAppraiseCount(String workerId,String appraiseState){
		return myAppraiseBPO.getWorkerAppraiseCount(workerId,appraiseState);
	}

	public AppraiseDTO getByOrderId(java.lang.String orderId){
		return myAppraiseBPO.getByOrderId(orderId);
	}

	public String getAppraiseRate(String workerId,String appraiseState){
		int totalNum = myAppraiseBPO.getWorkerAppraiseCount(workerId,"0");//当前师傅的所有评价数量
		int positiveNum = myAppraiseBPO.getWorkerAppraiseCount(workerId,appraiseState);
		String appraiseRate = "100";
		if(0 != totalNum){
			String r = ((float) positiveNum/totalNum)*100 + "";
			appraiseRate = new BigDecimal(r).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
		}
		return appraiseRate;
	}
	
	public AppraiseBPO getMyAppraiseBPO() {
		return myAppraiseBPO;
	}
	public void setMyAppraiseBPO(AppraiseBPO myAppraiseBPO) {
		this.myAppraiseBPO = myAppraiseBPO;
	}


	public static void main(String[] args) {
		int i = 8;
		int n = 1;
		String r = ((float) n/i)*100 + "";
		System.out.println(r);
		BigDecimal t = new BigDecimal(r).setScale(0, BigDecimal.ROUND_HALF_UP);
		System.out.println(t);
	}
}