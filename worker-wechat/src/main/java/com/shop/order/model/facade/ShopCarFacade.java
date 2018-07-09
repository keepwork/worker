package com.shop.order.model.facade;

import java.util.List;

import com.shop.order.model.bpo.ShopCarBPO;
import com.shop.order.model.dto.ShopCarDTO;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护ShopCarDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class ShopCarFacade { 
	
	private ShopCarBPO myShopCarBPO;

	public boolean save(ShopCarDTO dto){
		return myShopCarBPO.saveTX(dto);
	}

	
	public void update(ShopCarDTO dto){
		myShopCarBPO.updateTX(dto);
	}

	public void saveOrUpdate(ShopCarDTO dto) {
		myShopCarBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) {
		myShopCarBPO.deleteTX(ids);
	}
	
	
	public void delShopCarAll(String menId) {
		myShopCarBPO.deleteAllTX(menId);
	}

	
	public ShopCarDTO get(java.lang.String id) {
		return myShopCarBPO.get(id);
	}


	public List list(LimitInfo limit) {
		return myShopCarBPO.list(limit);
	}

	
	public List listByIds(String ids) {
		return myShopCarBPO.listByIds(ids);
	}
	
	public List<ShopCarDTO> listByOpenId(String openId) {
		return myShopCarBPO.listByOpenId(openId);
	}
	
	public List<ShopCarDTO> listByMenId(String menId) {
		return myShopCarBPO.listByMenId(menId);
	}
	
	public List<ShopCarDTO> listShopCar(String menId,String goodId){
		return myShopCarBPO.listShopCar(menId,goodId);
	}
	

	public ShopCarBPO getMyShopCarBPO() {
		return myShopCarBPO;
	}
	public void setMyShopCarBPO(ShopCarBPO myShopCarBPO) {
		this.myShopCarBPO = myShopCarBPO;
	}



}